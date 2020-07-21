package cn.hotel.mainserver.service.room;

import cn.hotel.mainserver.mapper.room.RoomMapper;
import cn.hotel.mainserver.model.room.Room;
import cn.hotel.mainserver.pageination.PaginationResult;
import cn.hotel.mainserver.pageination.room.RoomPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Room> selectRoomsByTypeId(Integer typeId) {
        return roomMapper.selectRoomsByTypeId(typeId);
    }
}
