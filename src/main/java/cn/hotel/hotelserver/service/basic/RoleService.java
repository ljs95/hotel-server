package cn.hotel.hotelserver.service.basic;

import cn.hotel.hotelserver.mapper.basic.PermissionMapper;
import cn.hotel.hotelserver.mapper.basic.RoleMapper;
import cn.hotel.hotelserver.model.basic.Permission;
import cn.hotel.hotelserver.model.basic.Role;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.basic.RolePage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public PaginationResult table(RolePage rolePage) {
        IPage<Role> table = roleMapper.table(rolePage);

        return new PaginationResult(table.getTotal(), table.getRecords());
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
        if (!permissionIds.isEmpty()) {
            roleMapper.insertPermission(id, permissionIds);
        }
    }

    public List<String> selectPermissionUrlByIds(List<Integer> roleIds) {
        List<Permission> permissionList = permissionMapper.selectPermissionByRoleIds(roleIds);
        List<String> urls = new ArrayList<>(permissionList.size());
        permissionList.forEach((permission -> {
            urls.add(permission.getUrl());
        }));
        return urls;
    }
}
