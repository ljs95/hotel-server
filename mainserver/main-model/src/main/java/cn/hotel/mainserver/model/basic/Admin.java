package cn.hotel.mainserver.model.basic;

import cn.hotel.mainserver.model.basic.abstracts.AbstractAdmin;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roleList = new ArrayList<>(this.roles.size());
        for (Role role : this.roles) {
            roleList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return roleList;
    }
}