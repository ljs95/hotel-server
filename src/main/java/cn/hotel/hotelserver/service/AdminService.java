package cn.hotel.hotelserver.service;

import cn.hotel.hotelserver.mapper.basic.AdminMapper;
import cn.hotel.hotelserver.mapper.basic.RoleMapper;
import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.model.basic.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Johnson
* @date 2020/3/22
*/
@Service
public class AdminService implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.loadUserByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Role> roles = roleMapper.getRolesByAdminId(admin.getId());
        admin.setRoles(roles);
        return admin;
    }
}
