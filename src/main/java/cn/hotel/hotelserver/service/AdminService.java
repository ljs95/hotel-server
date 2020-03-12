package cn.hotel.hotelserver.service;

import cn.hotel.hotelserver.mapper.AdminMapper;
import cn.hotel.hotelserver.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.loadUserByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return admin;
    }
}
