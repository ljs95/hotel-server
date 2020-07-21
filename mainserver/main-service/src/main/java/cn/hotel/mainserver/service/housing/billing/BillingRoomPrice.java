package cn.hotel.mainserver.service.housing.billing;

import cn.hotel.mainserver.model.housing.HousingOperation;
import cn.hotel.mainserver.model.housing.snap.HousingOperationSnap;
import cn.hotel.mainserver.model.room.RoomSpec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算入住房间的费用
 */
public class BillingRoomPrice {
    private final List<HousingOperation> operationList = new ArrayList<>();

    private BigDecimal roomPrice;

    public BillingRoomPrice(HousingOperation operation) {
        this.operationList.add(operation);
    }

    public BillingRoomPrice(List<HousingOperation> operationList) {
        this.operationList.addAll(operationList);
    }

    public BillingRoomPrice run() {
        BigDecimal roomPrice = BigDecimal.ZERO;
        for (HousingOperation housingOperation : operationList) {
            HousingOperationSnap snap = housingOperation.getSnap();
            if (housingOperation.getMode().equals(RoomSpec.modeEnum.MODE_DAY.getMode())) {
                roomPrice = roomPrice.add(snap.getDayPrice());
            } else {
                roomPrice = roomPrice.add(snap.getHourPrice());
            }
        }

        this.roomPrice = roomPrice;
        return this;
    }

    public BigDecimal getRoomPrice() {
        return this.roomPrice;
    }
}
