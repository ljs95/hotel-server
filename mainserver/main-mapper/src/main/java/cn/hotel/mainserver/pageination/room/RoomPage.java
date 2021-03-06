package cn.hotel.mainserver.pageination.room;

import cn.hotel.mainserver.model.room.Room;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class RoomPage extends Page<Room> {

    /**
     * 房型ID
     */
    public Integer typeId;

    /**
     * 房间状态
     */
    public Integer status;
}
