package cn.hotel.mainserver.pageination.room;

import cn.hotel.mainserver.model.room.RoomSpec;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class RoomSpecPage extends Page<RoomSpec> {

    private Integer typeId;
}
