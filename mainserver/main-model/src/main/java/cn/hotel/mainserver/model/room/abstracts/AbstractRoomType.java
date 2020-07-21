package cn.hotel.mainserver.model.room.abstracts;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public abstract class AbstractRoomType {
    protected Integer id;

    @NotBlank(message = "房型名称不能为空")
    protected String name;

    @NotNull(message = "床位不能为空")
    protected Integer number;

    @NotNull(message = "房型不能为空")
    protected Integer specId;

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getSpecId() {
        return specId;
    }
}
