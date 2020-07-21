package cn.hotel.mainserver.service.basic;

import cn.hotel.mainserver.mapper.basic.AdminMapper;
import cn.hotel.mainserver.mapper.basic.RoleMapper;
import cn.hotel.mainserver.model.basic.Admin;
import cn.hotel.mainserver.model.basic.Role;
import cn.hotel.mainserver.pageination.PaginationResult;
import cn.hotel.mainserver.pageination.basic.AdminPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PaginationResult table(AdminPage pagination) {
        IPage<Admin> table = adminMapper.table(pagination);
        return new PaginationResult(table.getTotal(), table.getRecords());
    }

    /**
     * 创建管理员
     * @param admin
     * @param roleIds
     */
    public void create(Admin admin, List<Integer> roleIds) {
        // 密码加密
        String newPassword = encodePassword(admin.getPassword());
        admin.setPassword(newPassword);

        //添加管理员
        adminMapper.insert(admin);

        // 添加管理员角色
        adminMapper.insertAdminRole(admin.getId(), roleIds);
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

    /**
     * 更新管理员信息和角色
     * @param admin
     * @param roleIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateAndRole(Admin admin, List<Integer> roleIds) {
        // 更新用户信息
        update(admin);

        // 删除用户角色
        adminMapper.deleteAdminRole(admin.getId());

        // 添加用户角色
        if (!roleIds.isEmpty()) {
            adminMapper.insertAdminRole(admin.getId(), roleIds);
        }
    }

    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    public Admin findById(Integer id) {
        Admin admin = adminMapper.findById(id);
        List<Role> roles = roleMapper.getRolesByAdminId(admin.getId());
        admin.setRoles(roles);
        return admin;
    }

    /**
     * 删除管理员
     * @param id
     */
    public void delete(Integer id) {
        adminMapper.delete(id);
    }

    /**
     * 密码加密
     * @param password
     * @return
     */
    public String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
