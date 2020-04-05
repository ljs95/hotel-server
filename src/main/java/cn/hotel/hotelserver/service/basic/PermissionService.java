package cn.hotel.hotelserver.service.basic;

import cn.hotel.hotelserver.mapper.basic.PermissionMapper;
import cn.hotel.hotelserver.model.basic.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    public List<Permission> treeData(Boolean enabled) {
        List<Permission> permissions = permissionMapper.all(enabled);
        return generateTreeData(0, permissions);
    }

    private List<Permission> generateTreeData(Integer id, List<Permission> permissions) {
        List<Permission> permissionList = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission.getPid().equals(id)) {
                permission.setChildren(generateTreeData(permission.getId(), permissions));
                permissionList.add(permission);
            }
        }

        return permissionList;
    }

    public void create(Permission permission) {
        permissionMapper.insert(permission);
    }

    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKey(permission);
    }

    public void delete(Integer id) {
        permissionMapper.deleteByPrimaryKey(id);
    }
}
