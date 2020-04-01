package cn.hotel.hotelserver.model.basic.abstracts;

import cn.hotel.hotelserver.validates.ICreateValid;
import cn.hotel.hotelserver.validates.IUpdateValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Johnson
 * @date 2020/04/01/ 10:59:06
 */
public abstract class AbstractRole {

    @NotNull(groups = {IUpdateValid.class})
    protected Integer id;

    @NotBlank(groups = {ICreateValid.class, IUpdateValid.class}, message = "名称不能为空")
    @Size(groups = {ICreateValid.class, IUpdateValid.class}, min = 2, max = 16, message = "名称长度范围[2,16]")
    protected String name;

    @NotBlank(groups = {ICreateValid.class, IUpdateValid.class}, message = "别名不能为空")
    @Size(groups = {ICreateValid.class, IUpdateValid.class}, min = 2, max = 20, message = "别名长度范围[2,20]")
    protected String alias;

    @Size(groups = {ICreateValid.class, IUpdateValid.class}, max = 100, message = "备注最大长度为100")
    protected String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
