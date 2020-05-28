package cn.hotel.hotelserver.model.room;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.Map;

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
        return statusEnum.getStatusList().get(this.status);
    }

    public Integer getId() {
        return id;
    }

    /**
     * 房间状态枚举类
     */
    public enum statusEnum {
        // 空闲状态
        STATUS_FREE("空闲", 1),
        // 使用中状态
        STATUS_USE("使用中", 2),
        // 打扫中状态
        STATUS_CLEAN("打扫中", 3);

        private final String name;
        private final Integer status;
        private final static Map<Integer, String> MAP = new LinkedHashMap<>();
        static {
            for (statusEnum statusEnum : statusEnum.values()) {
                MAP.put(statusEnum.status, statusEnum.name);
            }
        }

        statusEnum(String name, Integer status) {
            this.name = name;
            this.status = status;
        }

        public static Map<Integer, String> getStatusList() {
            return MAP;
        }
    }
}