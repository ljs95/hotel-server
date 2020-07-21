package cn.hotel.mainserver.vo.basic;

import cn.hotel.mainserver.common.validates.ICreateValid;
import cn.hotel.mainserver.common.validates.IUpdateValid;
import cn.hotel.mainserver.model.basic.abstracts.AbstractAdmin;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Johnson
 * @date 2020/03/22/ 10:56:12
 */
public class AdminVo extends AbstractAdmin {
    @NotBlank(groups = {ICreateValid.class}, message = "密码不能为空")
    @Size(groups = {ICreateValid.class, IUpdateValid.class}, min = 6, max = 20, message = "密码长度范围[6,20]")
    protected String password;

    @NotEmpty(groups = {ICreateValid.class, IUpdateValid.class}, message = "角色列表选项不能为空")
    private List<Integer> roleIds;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
