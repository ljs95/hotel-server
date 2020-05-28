package cn.hotel.hotelserver.model.room;

import lombok.Data;

/**
 * 房间排期
 */
@Data
public class RoomSchedule {
    private Integer id;

    private Integer roomId;

    private Long startTime;

    private Long endTime;

    public RoomSchedule setRoomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }

    public RoomSchedule setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public RoomSchedule setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }
}