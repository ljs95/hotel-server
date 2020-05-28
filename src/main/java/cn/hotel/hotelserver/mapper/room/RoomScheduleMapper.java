package cn.hotel.hotelserver.mapper.room;


import cn.hotel.hotelserver.model.room.RoomSchedule;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomSchedule record);

    RoomSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomSchedule record);

    int updateByPrimaryKey(RoomSchedule record);

    /**
     * 根据房间id和时间统计数据量
     * @param roomSchedule
     * @return
     */
    Long countByRoomTime(RoomSchedule roomSchedule);
}