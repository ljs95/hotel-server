package cn.hotel.hotelserver.vo.room;

import cn.hotel.hotelserver.model.room.RoomSpec;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class RoomSpecPage extends Page<RoomSpec> {

    private Integer typeId;
}
