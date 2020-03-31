package cn.hotel.hotelserver.model.basic;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Johnson
 * @date 2020/03/22/ 10:57:40
 */
public abstract class AbstractAdmin {

    protected Integer id;

    @Size(min = 6, max = 16, message = "用户名长度为6-16位")
    @NotNull
    protected String username;

    @NotNull
    @Max(value = 20, message = "昵称最大长度为20")
    protected String password;

    @NotNull
    protected String nickname;

    protected String userImg;

    protected List<Role> roles;

    @NotNull
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
