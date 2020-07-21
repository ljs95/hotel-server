package cn.hotel.mainserver.mapper.room;


import cn.hotel.mainserver.model.room.RoomSpec;
import cn.hotel.mainserver.pageination.room.RoomSpecPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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