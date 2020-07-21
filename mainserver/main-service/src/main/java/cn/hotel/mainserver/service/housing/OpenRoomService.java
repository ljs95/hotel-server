package cn.hotel.mainserver.service.housing;

import cn.hotel.mainserver.common.bean.CustomBeanUtils;
import cn.hotel.mainserver.common.exception.CustomException;
import cn.hotel.mainserver.mapper.housing.HousingBillMapper;
import cn.hotel.mainserver.mapper.housing.HousingOperationMapper;
import cn.hotel.mainserver.mapper.housing.HousingPriceMapper;
import cn.hotel.mainserver.model.housing.HousingBill;
import cn.hotel.mainserver.model.housing.HousingOperation;
import cn.hotel.mainserver.model.housing.HousingPrice;
import cn.hotel.mainserver.model.housing.snap.HousingOperationSnap;
import cn.hotel.mainserver.model.room.RoomSchedule;
import cn.hotel.mainserver.model.room.extension.RoomSpecPrice;
import cn.hotel.mainserver.service.housing.allocator.RoomAllocator;
import cn.hotel.mainserver.service.housing.billing.BillingRoomPrice;
import cn.hotel.mainserver.service.housing.bo.OpenRoomBo;
import cn.hotel.mainserver.service.room.RoomScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;

/**
 * 开房业务
 */
@Service
public class OpenRoomService {

    private OpenRoomBo openRoomBo;

    private final RoomAllocator instance = RoomAllocator.getInstance();

    @Autowired
    HousingBillMapper housingBillMapper;

    @Autowired
    HousingOperationMapper operationMapper;

    @Autowired
    HousingPriceMapper priceMapper;

    @Autowired
    RoomScheduleService roomScheduleService;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    public HousingBill openRoom(OpenRoomBo.Builder builder) throws InterruptedException {
        openRoomBo = builder.build();

        HousingBill bill;
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            instance.lock(openRoomBo.getRoomId());

            // 开房
            bill = openRoom();

            // 提交事务
            dataSourceTransactionManager.commit(transaction);
        } catch (Exception e) {
            // 回滚事务
            dataSourceTransactionManager.rollback(transaction);
            throw e;
        } finally {
            instance.unlock(openRoomBo.getRoomId());
        }

        return bill;
    }

    /**
     * 开房
     * @return HousingBill
     */
    private HousingBill openRoom() {
        // 1.锁定房间排期
        Integer roomScheduleId = lockRoomSchedule();

        // 2.创建入住账单
        HousingBill bill = createBill(roomScheduleId);

        // 3.创建入住操作
        HousingOperation operation = addOperation(bill.getSerial());

        // 4.创建账单金额
        createPrice(bill.getSerial(), operation);
        return bill;
    }

    /**
     * 锁定房间排期
     * @return
     */
    private Integer lockRoomSchedule() {
        // 1.验证房间排期
        if (roomScheduleService.isConflict(openRoomBo.getRoomId(), openRoomBo.getStartTime(), openRoomBo.getEndTime())) {
            throw new CustomException("房间排期冲突");
        }

        // 2.创建房间排期
        RoomSchedule roomSchedule = new RoomSchedule();
        BeanUtils.copyProperties(openRoomBo, roomSchedule);
        return roomScheduleService.insert(roomSchedule);
    }

    /**
     * 创建入住账单
     * @return
     */
    private HousingBill createBill(Integer roomScheduleId) {
        HousingBill housingBill = CustomBeanUtils.setProperties(openRoomBo, HousingBill::new)
                .createSerial()
                .setRoomScheduleId(roomScheduleId);

        housingBillMapper.insertSelective(housingBill);
        return housingBill;
    }

    /**
     * 添加入住操作
     */
    private HousingOperation addOperation(String billSerial) {
        RoomSpecPrice specPrice = openRoomBo.getRoomSpec().getPrice();
        HousingOperationSnap snap = (new HousingOperationSnap())
                .setPrice(specPrice.getDay(), specPrice.getDayContinueHour(), specPrice.getHour());

        HousingOperation housingOperation = CustomBeanUtils.setProperties(openRoomBo, HousingOperation::new)
                .setBillSerial(billSerial)
                .setType(HousingOperation.typeEnum.TYPE_OPEN)
                .setSnap(snap)
                .setCreateTime();

        operationMapper.insertSelective(housingOperation);

        return housingOperation;
    }

    /**
     * 创建入住账单金额
     * @param billSerial
     * @param housingOperation
     */
    private void createPrice(String billSerial, HousingOperation housingOperation) {
        // 计算入住房间时长价格
        BillingRoomPrice billingRoomPrice = (new BillingRoomPrice(housingOperation))
                .run();

        BigDecimal deposit = openRoomBo.getRoomSpec()
                .getPrice()
                .getDeposit();
        HousingPrice housingPrice = (new HousingPrice())
                .setBillSerial(billSerial)
                .setDeposit(deposit)
                .setRoomPrice(billingRoomPrice.getRoomPrice());
        priceMapper.insert(housingPrice);
    }
}
