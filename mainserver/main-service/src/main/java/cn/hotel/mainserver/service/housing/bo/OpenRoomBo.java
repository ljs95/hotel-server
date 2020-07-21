package cn.hotel.mainserver.service.housing.bo;

import cn.hotel.mainserver.common.exception.CustomException;
import cn.hotel.mainserver.model.room.Room;
import cn.hotel.mainserver.model.room.RoomSpec;
import cn.hotel.mainserver.model.room.RoomType;
import cn.hotel.mainserver.service.housing.HousingTime;
import cn.hotel.mainserver.service.room.RoomService;
import cn.hotel.mainserver.service.room.RoomSpecService;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class OpenRoomBo {

    private final Integer time;

    private final RoomSpec.modeEnum mode;

    private Long startTime;

    private Long endTime;

    private Room room;

    private RoomSpec roomSpec;

    private OpenRoomBo(Builder builder) {
        this.time = builder.time;
        this.mode = builder.mode;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Integer getTime() {
        return time;
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

    public RoomSpec getRoomSpec() {
        return roomSpec;
    }

    public RoomSpec.modeEnum getMode() {
        return mode;
    }

    public static class Builder {

        private Long startTime;

        private Integer time = 1;

        private Integer roomId;

        /**
         * 入住方式 全日房/钟点房
         */
        private RoomSpec.modeEnum mode = RoomSpec.modeEnum.MODE_DAY;

        @Autowired
        RoomService roomService;

        @Autowired
        RoomSpecService roomSpecService;

        public Builder setStartTime(Long startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setTime(Integer time) {
            this.time = time;
            return this;
        }

        public Builder setRoomId(Integer roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder setMode(RoomSpec.modeEnum mode) {
            this.mode = mode;
            return this;
        }

        /**
         * 开始构建开房业务类
         * @return OpenRoomBo
         */
        public OpenRoomBo build() {
            if (this.time < 1) {
                throw new CustomException("开房天数/小时数不能小于1");
            }

            OpenRoomBo openRoomBo = new OpenRoomBo(this);
            initTime(openRoomBo);

            // 开房房间
            openRoomBo.room = roomService.selectByPrimaryKey(this.roomId);
            openRoomBo.roomSpec = roomSpecService.selectByPrimaryKey(openRoomBo.getRoomType().getSpecId());
            return openRoomBo;
        }

        /**
         * 初始化事件
         * @param openRoomBo
         */
        private void initTime(OpenRoomBo openRoomBo) {
            // 开始时间
            DateTime startTime;
            // 结束时间
            DateTime endTime;

            // 是否开全日房
            if (mode.equals(RoomSpec.modeEnum.MODE_DAY)) {
                startTime = HousingTime.normDayStartTime(this.startTime);
                endTime = DateUtil.offsetDay(startTime.toJdkDate(), this.time);
            } else {
                startTime = HousingTime.normHourStartTime(this.startTime);
                endTime = DateUtil.offsetHour(startTime.toJdkDate(), this.time);
            }

            openRoomBo.startTime = startTime.getTime();
            openRoomBo.endTime = endTime.getTime();
        }
    }
}
