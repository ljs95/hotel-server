package cn.hotel.hotelserver.vo.basic;

import cn.hotel.hotelserver.vo.Pagination;

/**
 * @author Johnson
 * @date 2020/03/31/ 15:17:08
 */
public class AdminPagination extends Pagination {

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
