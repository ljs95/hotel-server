package cn.hotel.hotelserver.service.housing;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class HousingTime {
    // 全日房退房钟点
    public static final Integer dayLeaveTime = 12;

    /**
     * 获取零点时间戳
     * @return
     */
    public static DateTime getZeroTime(Long time) {
        return DateUtil.date(time)
                .setField(DateField.HOUR_OF_DAY, 0)
                .setField(DateField.MINUTE, 0)
                .setField(DateField.SECOND, 0)
                .setField(DateField.MILLISECOND, 0);
    }

    /**
     * 规范化开始时间
     * @return
     */
    public static DateTime normDayStartTime(Long startTime) {
        DateTime zeroTime = getZeroTime(startTime);
        zeroTime.offset(DateField.HOUR_OF_DAY, dayLeaveTime);
        return zeroTime;
    }

}
