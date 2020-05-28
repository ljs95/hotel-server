package cn.hotel.hotelserver.vo.housing;

import cn.hotel.hotelserver.exception.CustomException;
import cn.hotel.hotelserver.model.room.RoomSpec;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;


@Setter
public class OpenRoomVo {
    private Long startTime;

    private Integer time;

    private Integer roomId;

    /**
     * 入住方式 全日房/钟点房
     */
    private Integer mode;

    public void setMode(Integer mode) {
        Map<Integer, String> modeList = RoomSpec.modeEnum.getModeList();
        if(StringUtils.isEmpty(modeList.get(mode))) {
            throw new CustomException("入住方式有误");
        }
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

    public Integer getMode() {
        return mode;
    }

    public boolean isOpenDayRoom() {
        return this.getMode().equals(RoomSpec.MODE_DAY);
    }
}
