package cn.hotel.mainserver.model.housing;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

@Data
public class HousingBill {

    private String serial;

    private Integer roomId;

    private Integer roomScheduleId;

    private Long startTime;

    private Long entTime;

    private Long intoTime;

    private Long outTime;

    private Long createTime;

    private String snap;

    /**
     * 创建账单号
     * @return
     */
    public HousingBill createSerial() {
        this.serial = "BL" + IdUtil.simpleUUID();
        return this;
    }

    public HousingBill setRoomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }

    public HousingBill setRoomScheduleId(Integer roomScheduleId) {
        this.roomScheduleId = roomScheduleId;
        return this;
    }

    public HousingBill setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public HousingBill setEntTime(Long entTime) {
        this.entTime = entTime;
        return this;
    }

    public String getSerial() {
        return serial;
    }
}