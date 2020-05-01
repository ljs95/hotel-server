package cn.hotel.hotelserver.model.room.abstracts;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 房间状态枚举类
 */
public enum RoomStatusEnum {
    STATUS_FREE("空闲", 1),
    STATUS_USE("使用中", 2),
    STATUS_CLEAN("打扫中", 3);

    private String name;
    private Integer status;
    private final static Map<Integer, String> map;

    static {
        map = new LinkedHashMap<>();
        for (RoomStatusEnum statusEnum : RoomStatusEnum.values()) {
            map.put(statusEnum.getStatus(), statusEnum.getName());
        }
    }

    RoomStatusEnum(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public static Map<Integer, String> getStatusList() {
        return map;
    }

    public static String getName(Integer status) {
        for (RoomStatusEnum statusEnum : RoomStatusEnum.values()) {
            if (statusEnum.getStatus().equals(status)) {
                return statusEnum.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }
}
