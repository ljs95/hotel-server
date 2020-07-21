package cn.hotel.mainserver.model.basic.abstracts;

import cn.hotel.mainserver.common.validates.ICreateValid;
import cn.hotel.mainserver.common.validates.IUpdateValid;
import cn.hotel.mainserver.model.basic.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Johnson
 * @date 2020/03/22/ 10:57:40
 */
public abstract class AbstractAdmin {

    @NotNull(groups = {IUpdateValid.class}, message = "id不能为空")
    protected Integer id;

    @Size(groups = {ICreateValid.class, IUpdateValid.class}, min = 4, max = 16, message = "用户名长度为4-16位")
    @NotBlank(groups = {ICreateValid.class, IUpdateValid.class})
    protected String username;

    @NotBlank(groups = {ICreateValid.class, IUpdateValid.class}, message = "昵称不能为空")
    protected String nickname;

    protected String userImg;

    protected List<Role> roles;

    @NotNull(groups = {ICreateValid.class, IUpdateValid.class})
    protected Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
