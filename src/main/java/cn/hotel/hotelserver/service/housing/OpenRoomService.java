package cn.hotel.hotelserver.service.housing;

import cn.hotel.hotelserver.exception.CustomException;
import cn.hotel.hotelserver.mapper.housing.HousingBillMapper;
import cn.hotel.hotelserver.model.housing.HousingBill;
import cn.hotel.hotelserver.model.room.Room;
import cn.hotel.hotelserver.model.room.RoomSchedule;
import cn.hotel.hotelserver.model.room.RoomSpec;
import cn.hotel.hotelserver.service.housing.allocator.RoomAllocator;
import cn.hotel.hotelserver.service.housing.bo.OpenRoomBo;
import cn.hotel.hotelserver.service.room.RoomScheduleService;
import cn.hotel.hotelserver.service.room.RoomService;
import cn.hotel.hotelserver.vo.housing.OpenRoomVo;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * 开房业务
 */
@Service
public class OpenRoomService {

    private OpenRoomBo openRoomBo = new OpenRoomBo();

    @Autowired
    HousingBillMapper housingBillMapper;

    @Autowired
    RoomScheduleService roomScheduleService;

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
    }

    /**
     * 初始化时间
     *
     * @param vo
     */
    private void initTime(OpenRoomVo vo) {
        // 开始时间
        DateTime startTime = HousingTime.normDayStartTime(vo.getStartTime());
        openRoomBo.setStartTime(startTime.getTime());

        // 结束时间
        DateTime endTime = null;
        if (vo.getMode().equals(RoomSpec.MODE_DAY)) {
            endTime = DateUtil.offsetDay(startTime.toJdkDate(), vo.getTime());
        } else {
            endTime = DateUtil.offsetHour(startTime.toJdkDate(), vo.getTime());
        }
        openRoomBo.setEndTime(endTime.getTime());
    }

    public HousingBill openRoom(OpenRoomVo vo) throws InterruptedException {
        HousingBill bill = null;
        if (vo.isOpenDayRoom()) {
            return openDayRoom(vo);
        }

        return bill;
    }

    public HousingBill openDayRoom(OpenRoomVo vo) throws InterruptedException {
        init(vo);
        Integer roomId = openRoomBo.getRoom().getId();
        Long startTime = openRoomBo.getStartTime();
        Long endTime = openRoomBo.getEndTime();

        // 开启事务
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        RoomAllocator instance = RoomAllocator.getInstance();
        try {
            // 1.创建入住账单
            HousingBill bill = (new HousingBill())
                    .createSerial()
                    .setStartTime(startTime)
                    .setEntTime(endTime)
                    .setRoomId(roomId);
            housingBillMapper.insertSelective(bill);

            instance.lock(roomId);
            // 2.验证房间排期
            if (roomScheduleService.isConflict(roomId, startTime, endTime)) {
                throw new CustomException("房间排期冲突");
            }

            // 3.创建房间排期
            RoomSchedule roomSchedule = (new RoomSchedule())
                    .setRoomId(roomId)
                    .setStartTime(startTime)
                    .setEndTime(endTime);
            roomScheduleService.insert(roomSchedule);

            // 提交事务
            dataSourceTransactionManager.commit(transaction);
            return bill;
        }catch (Exception e) {
            // 回滚事务
            dataSourceTransactionManager.rollback(transaction);
            throw e;
        } finally {
            instance.unlock(roomId);
        }
    }
}
