package cn.hotel.mainserver.service.room;

import cn.hotel.mainserver.mapper.room.RoomSpecMapper;
import cn.hotel.mainserver.mapper.room.RoomTypeMapper;
import cn.hotel.mainserver.model.room.RoomSpec;
import cn.hotel.mainserver.model.room.RoomType;
import cn.hotel.mainserver.pageination.PaginationResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Autowired
    RoomSpecMapper roomSpecMapper;

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

    /**
     * 应用规格
     * @param typeId
     * @param specId
     */
    public void applySpec(Integer typeId, Integer specId) {
        RoomType roomType = this.find(typeId);
        RoomSpec roomSpec = roomSpecMapper.selectByPrimaryKey(specId);
        roomType.setSpecId(roomSpec.getId());
        roomTypeMapper.applySpec(typeId, specId);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id) {
        roomTypeMapper.deleteByPrimaryKey(id);
    }
}
