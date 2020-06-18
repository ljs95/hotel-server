package cn.hotel.hotelserver.model.room;

import cn.hotel.hotelserver.model.room.extension.RoomSpecPrice;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public RoomSpecPrice getPrice() {
        return price;
    }

    /**
     * 价格元转分
     */
    public void priceYuanToFen() {
        this.price.YuanToFen();
    }

    public enum modeEnum {
        // 全日房
        MODE_DAY("全日房", 1),
        // 钟点房
        MODE_HOUR("钟点房", 2);

        private final String name;
        private final Integer mode;
        private final static Map<Integer, String> MAP = new LinkedHashMap<>();
        static {
            for (modeEnum modeEnum : modeEnum.values()) {
                MAP.put(modeEnum.mode, modeEnum.name);
            }
        }

        modeEnum(String name, Integer mode) {
            this.name = name;
            this.mode = mode;
        }

        public static Map<Integer, String> getModeList() {
            return MAP;
        }

        public Integer getMode() {
            return mode;
        }
    }
}