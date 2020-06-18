package cn.hotel.hotelserver.model.housing.snap;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 入住操作快照
 */
@Data
public class HousingOperationSnap {
    /**
     * 全日房价格
     */
    private BigDecimal dayPrice;

    /**
     * 全日房续时价格
     */
    private BigDecimal dayContinueHourPrice;

    /**
     * 钟点房价格
     */
    private BigDecimal hourPrice;

    public HousingOperationSnap setPrice(BigDecimal dayPrice, BigDecimal dayContinueHourPrice, BigDecimal hourPrice) {
        this.dayPrice = dayPrice;
        this.dayContinueHourPrice = dayContinueHourPrice;
        this.hourPrice = hourPrice;
        return this;
    }

    public BigDecimal getDayPrice() {
        return dayPrice;
    }

    public BigDecimal getDayContinueHourPrice() {
        return dayContinueHourPrice;
    }

    public BigDecimal getHourPrice() {
        return hourPrice;
    }

}
