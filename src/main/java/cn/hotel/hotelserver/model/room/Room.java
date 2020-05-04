package cn.hotel.hotelserver.model.room;

import cn.hotel.hotelserver.model.room.enums.RoomStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Room {

    private Integer id;

    @NotBlank(message = "房间名称不能为空")
    @Size(min = 2, max = 16, message = "房间名称长度范围[2,16]")
    private String name;

    @NotNull(message = "楼层不能为空")
    private Integer floor;

    @NotNull(message = "房型不能为空")
    private Integer typeId;

    @NotNull(message = "状态不能为空")
    private Integer status;

    private RoomType roomType;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusDesc() {
        return RoomStatusEnum.getName(this.status);
    }
}