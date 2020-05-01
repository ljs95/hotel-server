package cn.hotel.hotelserver.model.room.abstracts;

import lombok.Data;

@Data
public abstract class AbstractRoomType {
    protected Integer id;

    protected String name;

    protected Integer number;

    protected Integer specId;
}
