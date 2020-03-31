package cn.hotel.hotelserver.service;

import cn.hotel.hotelserver.mapper.basic.AdminMapper;
import cn.hotel.hotelserver.mapper.basic.RoleMapper;
import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.model.basic.Role;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.basic.AdminPagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    /**
     * 分页查询
     * @author Johnson
     */
    public PaginationResult table(AdminPagination pagination) {
        List<Admin> table = adminMapper.table(pagination);
        Integer count = adminMapper.tableCount(pagination);

        return new PaginationResult(count, table);
    }

    /**
     * 创建管理员
     * @param admin
     */
    public void create(Admin admin) {
        String newPassword = encodePassword(admin.getPassword());
        admin.setPassword(newPassword);
        adminMapper.insert(admin);
    }

    /**
     * 更新管理员
     * @param admin
     */
    public void update(Admin admin) {
        if (!StringUtils.isEmpty(admin.getPassword())) {
            String newPassword = encodePassword(admin.getPassword());
            admin.setPassword(newPassword);
        }

        adminMapper.update(admin);
    }

    public Admin find(Integer id) {
        return adminMapper.find(id);
    }

    public void delete(Integer id) {
        adminMapper.delete(id);
    }

    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
