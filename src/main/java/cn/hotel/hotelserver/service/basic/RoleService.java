package cn.hotel.hotelserver.service.basic;

import cn.hotel.hotelserver.mapper.basic.PermissionMapper;
import cn.hotel.hotelserver.mapper.basic.RoleMapper;
import cn.hotel.hotelserver.model.basic.Permission;
import cn.hotel.hotelserver.model.basic.Role;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.basic.RolePagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Johnson
 * @date 2020/04/01/ 09:28:17
 */
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    public PaginationResult table(RolePagination pagination) {
        List<Role> roles = roleMapper.table(pagination);
        Integer count = roleMapper.tableCount(pagination);
        return new PaginationResult(count, roles);
    }

    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    public void update(Role role) {
        roleMapper.update(role);
    }

    public List<Role> all() {
        return roleMapper.all();
    }

    public void create(Role role) {
        roleMapper.insert(role);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        // 删除该角色的管理员
        roleMapper.deleteAdminRoleByRoleId(id);

        // 删除角色
        roleMapper.delete(id);
    }

    public List<Permission> permissions(Integer id) {
        return permissionMapper.selectByRoleId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePermission(Integer id, List<Integer> permissionIds) {
        roleMapper.deletePermission(id);
        roleMapper.insertPermission(id, permissionIds);
    }
}
