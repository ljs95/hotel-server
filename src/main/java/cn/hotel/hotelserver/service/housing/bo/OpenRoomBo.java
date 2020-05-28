package cn.hotel.hotelserver.service.housing.bo;

import cn.hotel.hotelserver.model.housing.HousingBill;
import cn.hotel.hotelserver.model.room.Room;

public class OpenRoomBo {
    private Long startTime;

    private Long endTime;

    private HousingBill bill;

    private Room room;

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

    public HousingBill getBill() {
        return bill;
    }

    public void setBill(HousingBill bill) {
        this.bill = bill;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
