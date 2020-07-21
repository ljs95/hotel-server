package cn.hotel.mainserver.model.housing;

import cn.hotel.mainserver.model.housing.snap.HousingOperationSnap;
import cn.hotel.mainserver.model.room.RoomSpec;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class HousingOperation {
    private Integer id;

    /**
     * 入住账单号
     */
    private String billSerial;

    /**
     * 入住类型，开房、续住
     */
    private Integer type;

    /***
     * 入住模式：全日房、钟点房
     * @see RoomSpec.modeEnum
     */
    private Integer mode;

    /**
     * 入住时间 天/小时
     */
    private Integer time;

    private Long createTime;

    private Long startTime;

    private Long endTime;

    private HousingOperationSnap snap;

    public HousingOperation setBillSerial(String billSerial) {
        this.billSerial = billSerial;
        return this;
    }

    public HousingOperation setType(typeEnum type) {
        this.type = type.getType();
        return this;
    }

    public HousingOperation setMode(RoomSpec.modeEnum mode) {
        this.mode = mode.getMode();
        return this;
    }

    public HousingOperation setTime(Integer time) {
        this.time = time;
        return this;
    }

    public HousingOperation setCreateTime() {
        this.createTime = DateUtil.date().getTime();
        return this;
    }

    public HousingOperation setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public HousingOperation setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public HousingOperation setSnap(HousingOperationSnap snap) {
        this.snap = snap;
        return this;
    }

    public Integer getMode() {
        return mode;
    }

    public HousingOperationSnap getSnap() {
        return snap;
    }

    public enum typeEnum {
        TYPE_OPEN("开房", 1),
        TYPE_STAY("续房", 2);

        private String name;
        private Integer type;
        private final static Map<Integer, String> MAP = new LinkedHashMap<>();
        static {
            for (typeEnum typeEnum : typeEnum.values()) {
                MAP.put(typeEnum.type, typeEnum.name);
            }
        }

        typeEnum(String name, Integer type) {
            this.name = name;
            this.type = type;
        }

        public static Map<Integer, String> getModeList() {
            return MAP;
        }

        public Integer getType() {
            return type;
        }
    }
}