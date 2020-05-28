package cn.hotel.hotelserver.service.room;

import cn.hotel.hotelserver.mapper.room.RoomScheduleMapper;
import cn.hotel.hotelserver.model.room.RoomSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomScheduleService {

    @Autowired
    RoomScheduleMapper scheduleMapper;

    public int insert(RoomSchedule roomSchedule) {
        return scheduleMapper.insert(roomSchedule);
    }

    /**
     * 判断排期是否有冲突
     * @return
     */
    public boolean isConflict(Integer roomId, Long startTime, Long endTime) {
        RoomSchedule roomSchedule = (new RoomSchedule())
                .setRoomId(roomId)
                .setStartTime(startTime)
                .setEndTime(endTime);
        Long count = scheduleMapper.countByRoomTime(roomSchedule);
        return count > 0;
    }
}
