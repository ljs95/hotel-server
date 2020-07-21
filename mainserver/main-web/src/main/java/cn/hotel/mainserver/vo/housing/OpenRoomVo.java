package cn.hotel.mainserver.vo.housing;

import cn.hotel.mainserver.model.room.RoomSpec;
import lombok.Setter;

@Setter
public class OpenRoomVo {
    private Long startTime;

    private Integer time;

    private Integer roomId;

    /**
     * 入住方式 全日房/钟点房
     */
    private RoomSpec.modeEnum mode;

    public void setMode(RoomSpec.modeEnum mode) {
        this.mode = mode;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public RoomSpec.modeEnum getMode() {
        return mode;
    }

    /**
     * 是否开全日房
     * @return
     */
    public boolean isOpenDayRoom() {
        return this.getMode().equals(RoomSpec.modeEnum.MODE_DAY);
    }
}
