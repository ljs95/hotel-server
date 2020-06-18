package cn.hotel.hotelserver.service.room;

import cn.hotel.hotelserver.exception.CustomException;
import cn.hotel.hotelserver.mapper.room.RoomSpecMapper;
import cn.hotel.hotelserver.model.room.RoomSpec;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.room.RoomSpecPage;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomSpecService {

    @Autowired
    RoomSpecMapper roomSpecMapper;

    public PaginationResult table(RoomSpecPage roomSpecPage) {
        IPage<RoomSpec> table = roomSpecMapper.table(roomSpecPage);
        return new PaginationResult(table.getTotal(), table.getRecords());
    }

    public void insert(RoomSpec roomSpec) {
        roomSpecMapper.insert(roomSpec);
    }

    public void updateByPrimaryKey(RoomSpec roomSpec) {
        roomSpecMapper.updateByPrimaryKey(roomSpec);
    }

    public void deleteByPrimaryKey(Integer id) {
        roomSpecMapper.deleteByPrimaryKey(id);
    }

    public RoomSpec selectByPrimaryKey(Integer id) {
        RoomSpec roomSpec = roomSpecMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(roomSpec)) {
            throw new CustomException("房间规格不存在");
        }
        return roomSpec;
    }

    public List<RoomSpec> select() {
        return roomSpecMapper.select();
    }
}
