package cn.hotel.hotelserver.service.room;

import cn.hotel.hotelserver.mapper.room.RoomMapper;
import cn.hotel.hotelserver.model.room.Room;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.room.RoomPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    RoomMapper roomMapper;

    public PaginationResult table(RoomPage roomPage) {
        IPage<Room> table = roomMapper.table(roomPage);
        return new PaginationResult(table.getTotal(), table.getRecords());
    }

    public Room selectByPrimaryKey(Integer id) {
        return roomMapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKey(Room room) {
        roomMapper.updateByPrimaryKey(room);
    }

    public void insert(Room room) {
        roomMapper.insert(room);
    }

    public void deleteByPrimaryKey(Integer id) {
        roomMapper.deleteByPrimaryKey(id);
    }
}
