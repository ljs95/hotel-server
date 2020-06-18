package cn.hotel.hotelserver.service.housing;

import cn.hotel.hotelserver.exception.CustomException;
import cn.hotel.hotelserver.mapper.housing.HousingBillMapper;
import cn.hotel.hotelserver.mapper.housing.HousingOperationMapper;
import cn.hotel.hotelserver.mapper.housing.HousingPriceMapper;
import cn.hotel.hotelserver.model.housing.HousingBill;
import cn.hotel.hotelserver.model.housing.HousingOperation;
import cn.hotel.hotelserver.model.housing.HousingPrice;
import cn.hotel.hotelserver.model.housing.snap.HousingOperationSnap;
import cn.hotel.hotelserver.model.room.Room;
import cn.hotel.hotelserver.model.room.RoomSchedule;
import cn.hotel.hotelserver.model.room.RoomSpec;
import cn.hotel.hotelserver.model.room.extension.RoomSpecPrice;
import cn.hotel.hotelserver.service.housing.allocator.RoomAllocator;
import cn.hotel.hotelserver.service.housing.billing.BillingRoomPrice;
import cn.hotel.hotelserver.service.housing.bo.OpenRoomBo;
import cn.hotel.hotelserver.service.room.RoomScheduleService;
import cn.hotel.hotelserver.service.room.RoomService;
import cn.hotel.hotelserver.service.room.RoomSpecService;
import cn.hotel.hotelserver.vo.housing.OpenRoomVo;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
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

    private OpenRoomBo openRoomBo = new OpenRoomBo();

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
    RoomSpecService roomSpecService;

    @Autowired
    RoomService roomService;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    public void init(OpenRoomVo vo) {
        initTime(vo);
        // 开房房间
        Room room = roomService.selectByPrimaryKey(vo.getRoomId());
        openRoomBo.setRoom(room);
        openRoomBo.setMode(vo.getMode());
        RoomSpec roomSpec = roomSpecService.selectByPrimaryKey(openRoomBo.getRoomType().getSpecId());
        openRoomBo.setRoomSpec(roomSpec);
    }

    /**
     * 初始化时间
     * @param vo
     */
    private void initTime(OpenRoomVo vo) {
        // 开始时间
        DateTime startTime;
        // 结束时间
        DateTime endTime;

        // 是否开全日房
        if (vo.isOpenDayRoom()) {
            startTime = HousingTime.normDayStartTime(vo.getStartTime());
            endTime = DateUtil.offsetDay(startTime.toJdkDate(), vo.getTime());
        } else {
            startTime = HousingTime.normHourStartTime(vo.getStartTime());
            endTime = DateUtil.offsetHour(startTime.toJdkDate(), vo.getTime());
        }

        openRoomBo.setStartTime(startTime.getTime());
        openRoomBo.setEndTime(endTime.getTime());
        openRoomBo.setTime(vo.getTime());
    }

    public HousingBill openRoom(OpenRoomVo vo) throws InterruptedException {
        init(vo);
        HousingBill bill;
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            instance.lock(vo.getRoomId());
            if (vo.isOpenDayRoom()) {
                bill = openDayRoom();
            } else {
                bill = openHourRoom();
            }
            // 提交事务
            dataSourceTransactionManager.commit(transaction);
        } catch (Exception e) {
            // 回滚事务
            dataSourceTransactionManager.rollback(transaction);
            throw e;
        } finally {
            instance.unlock(vo.getRoomId());
        }

        return bill;
    }

    /**
     * 开房
     * @return HousingBill
     */
    private HousingBill openDayRoom() {
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
     * 开房
     * @return HousingBill
     */
    private HousingBill openHourRoom()  {
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
        RoomSchedule roomSchedule = (new RoomSchedule())
                .setRoomId(openRoomBo.getRoomId())
                .setStartTime(openRoomBo.getStartTime())
                .setEndTime(openRoomBo.getEndTime());
        return roomScheduleService.insert(roomSchedule);
    }

    /**
     * 创建入住账单
     * @return
     */
    private HousingBill createBill(Integer roomScheduleId) {
        HousingBill bill = (new HousingBill())
                .createSerial()
                .setStartTime(openRoomBo.getStartTime())
                .setEntTime(openRoomBo.getEndTime())
                .setRoomId(openRoomBo.getRoomId())
                .setRoomScheduleId(roomScheduleId);
        housingBillMapper.insertSelective(bill);
        return bill;
    }

    /**
     * 添加入住操作
     */
    private HousingOperation addOperation(String billSerial) {
        RoomSpecPrice specPrice = openRoomBo.getRoomSpec().getPrice();
        HousingOperationSnap snap = (new HousingOperationSnap())
                .setPrice(specPrice.getDay(), specPrice.getDayContinueHour(), specPrice.getHour());

        HousingOperation housingOperation = (new HousingOperation())
                .setBillSerial(billSerial)
                .setType(HousingOperation.typeEnum.TYPE_OPEN)
                .setMode(openRoomBo.getMode())
                .setTime(openRoomBo.getTime())
                .setSnap(snap)
                .setStartTime(openRoomBo.getStartTime())
                .setEndTime(openRoomBo.getEndTime())
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

        BigDecimal deposit = openRoomBo.getRoomSpec().getPrice().getDeposit();
        HousingPrice housingPrice = (new HousingPrice())
                .setBillSerial(billSerial)
                .setDeposit(deposit)
                .setRoomPrice(billingRoomPrice.getRoomPrice());
        priceMapper.insert(housingPrice);
    }
}
