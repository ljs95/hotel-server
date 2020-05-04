package cn.hotel.hotelserver.mapper.room;


import cn.hotel.hotelserver.model.room.RoomSpec;
import cn.hotel.hotelserver.vo.room.RoomSpecPage;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface RoomSpecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomSpec record);

    RoomSpec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoomSpec record);

    int updateByPrimaryKeyWithBLOBs(RoomSpec record);

    int updateByPrimaryKey(RoomSpec roomSpec);

    IPage<RoomSpec> table(RoomSpecPage roomSpecPage);

    List<RoomSpec> select();
}