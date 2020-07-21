package cn.hotel.mainserver.mapper.room;


import cn.hotel.mainserver.model.room.Room;
import cn.hotel.mainserver.pageination.room.RoomPage;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface RoomMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    IPage<Room> table(RoomPage page);

    List<Room> selectRoomsByTypeId(Integer typeId);
}