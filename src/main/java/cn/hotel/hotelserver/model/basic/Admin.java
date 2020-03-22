package cn.hotel.hotelserver.model.basic;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 管理员模型
 *
 * @author Johnson
 * @date 2020/3/22
 */
public class Admin extends AbstractAdmin implements UserDetails {

    private String password;

    private Boolean enabled;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roleList = new ArrayList<>(this.roles.size());
        for (Role role : this.roles) {
            roleList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return roleList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "password='" + password + '\'' +
                ", enabled=" + enabled +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userImg='" + userImg + '\'' +
                '}';
    }
}