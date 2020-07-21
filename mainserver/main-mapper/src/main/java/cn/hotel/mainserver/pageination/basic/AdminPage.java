package cn.hotel.mainserver.pageination.basic;

import cn.hotel.mainserver.model.basic.Admin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author Johnson
 * @date 2020/03/31/ 15:17:08
 */
public class AdminPage extends Page<Admin> {

    private String username;

    private String nickname;

    private Boolean enabled;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
