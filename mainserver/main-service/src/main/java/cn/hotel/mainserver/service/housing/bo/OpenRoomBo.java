package cn.hotel.mainserver.service.housing.bo;

import cn.hotel.mainserver.model.housing.HousingBill;
import cn.hotel.mainserver.model.housing.HousingOperation;
import cn.hotel.mainserver.model.room.Room;
import cn.hotel.mainserver.model.room.RoomSpec;
import cn.hotel.mainserver.model.room.RoomType;

public class OpenRoomBo {
    private Long startTime;

    private Long endTime;

    private Integer time;

    private HousingBill bill;

    private Room room;

    private RoomSpec roomSpec;

    private RoomSpec.modeEnum mode;

    private HousingOperation.typeEnum type;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public HousingBill getBill() {
        return bill;
    }

    public void setBill(HousingBill bill) {
        this.bill = bill;
    }

    public Room getRoom() {
        return room;
    }

    public RoomType getRoomType() {
        return room.getRoomType();
    }

    public Integer getRoomId() {
        return this.getRoom().getId();
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomSpec getRoomSpec() {
        return roomSpec;
    }

    public void setRoomSpec(RoomSpec roomSpec) {
        this.roomSpec = roomSpec;
    }

    public RoomSpec.modeEnum getMode() {
        return mode;
    }

    public void setMode(RoomSpec.modeEnum mode) {
        this.mode = mode;
    }

    public HousingOperation.typeEnum getType() {
        return type;
    }

    public void setType(HousingOperation.typeEnum type) {
        this.type = type;
    }
}
