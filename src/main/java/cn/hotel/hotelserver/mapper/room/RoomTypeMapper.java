package cn.hotel.hotelserver.mapper.room;

import cn.hotel.hotelserver.model.room.RoomType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface RoomTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    RoomType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);

    IPage<RoomType> table(Page<RoomType> page);

    List<RoomType> select();

    int applySpec(Integer id, Integer specId);
}