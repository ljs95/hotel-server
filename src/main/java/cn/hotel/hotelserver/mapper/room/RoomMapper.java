package cn.hotel.hotelserver.mapper.room;


import cn.hotel.hotelserver.model.room.Room;
import cn.hotel.hotelserver.vo.room.RoomPage;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface RoomMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    IPage<Room> table(RoomPage page);
}