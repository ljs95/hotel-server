package cn.hotel.hotelserver.model.housing;

import java.math.BigDecimal;

public class HousingPrice {
    private Integer id;

    private String billSerial;

    private BigDecimal deposit;

    private BigDecimal roomPrice;

    private String otherPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillSerial() {
        return billSerial;
    }

    public HousingPrice setBillSerial(String billSerial) {
        this.billSerial = billSerial == null ? null : billSerial.trim();
        return this;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public HousingPrice setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
        return this;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public HousingPrice setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
        return this;
    }

    public String getOtherPrice() {
        return otherPrice;
    }

    public HousingPrice setOtherPrice(String otherPrice) {
        this.otherPrice = otherPrice == null ? null : otherPrice.trim();
        return this;
    }
}