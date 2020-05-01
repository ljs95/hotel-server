package cn.hotel.hotelserver.service.room;

import cn.hotel.hotelserver.mapper.room.RoomTypeMapper;
import cn.hotel.hotelserver.model.room.RoomType;
import cn.hotel.hotelserver.vo.PaginationResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    RoomTypeMapper roomTypeMapper;

    /**
     * 分页查询
     * @author Johnson
     */
    public PaginationResult table(Page<RoomType> roomTypePage) {
        IPage<RoomType> table = roomTypeMapper.table(roomTypePage);
        return new PaginationResult(table.getTotal(), table.getRecords());
    }

    /**
     *
     * @param id 房型id
     * @return RoomType
     */
    public RoomType find(Integer id) {
        return roomTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 创建房型
     * @param roomType 房型
     */
    public void create(RoomType roomType) {
        roomTypeMapper.insertSelective(roomType);
    }

    /**
     * 更新
     * @param roomType 房型
     */
    public void update(RoomType roomType) {
        roomTypeMapper.updateByPrimaryKeySelective(roomType);
    }

    /**
     * 查询全部
     * @return
     */
    public List<RoomType> select() {
        return roomTypeMapper.select();
    }
}
