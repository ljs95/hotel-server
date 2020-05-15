package cn.hotel.hotelserver.model.room;

import cn.hotel.hotelserver.model.room.extension.RoomSpecPrice;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoomSpec {

    private Integer id;

    @NotBlank(message = "规格名称不能为空")
    private String name;

    @NotNull(message = "房型不能为空")
    private Integer typeId;

    @NotNull(message = "价格选项不能为空")
    @Valid
    private RoomSpecPrice price;

    private RoomType roomType;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    /**
     * 价格元转分
     */
    public void priceYuanToFen() {
        this.price.YuanToFen();
    }
}