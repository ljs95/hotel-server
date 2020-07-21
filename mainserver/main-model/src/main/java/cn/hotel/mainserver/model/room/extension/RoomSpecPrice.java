package cn.hotel.mainserver.model.room.extension;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RoomSpecPrice {

    /**
     * 押金
     */
    @NotNull(message = "押金不能为空")
    private BigDecimal deposit;

    /**
     * 全日房价格
     */
    @NotNull(message = "全日房价格不能为空")
    private BigDecimal day;

    /**
     * 全日房续时价格
     */
    @NotNull(message = "全日房续时价格不能为空")
    private BigDecimal dayContinueHour;

    /**
     * 钟点房价格
     */
    @NotNull(message = "钟点房价格不能为空")
    private BigDecimal hour;

    public BigDecimal getDeposit() {
        return deposit;
    }

    public BigDecimal getDay() {
        return day;
    }

    public BigDecimal getDayContinueHour() {
        return dayContinueHour;
    }

    public BigDecimal getHour() {
        return hour;
    }

    /**
     * 元转分
     */
    public void YuanToFen() {
        BigDecimal multiplyNumber = new BigDecimal(100);
        this.deposit = this.deposit.multiply(multiplyNumber);
        this.day = this.day.multiply(multiplyNumber);
        this.dayContinueHour = this.dayContinueHour.multiply(multiplyNumber);
        this.hour = this.hour.multiply(multiplyNumber);
    }
}
