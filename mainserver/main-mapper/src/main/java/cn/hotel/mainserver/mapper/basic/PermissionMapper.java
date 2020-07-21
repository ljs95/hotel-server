package cn.hotel.mainserver.mapper.basic;

import cn.hotel.mainserver.model.basic.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> all(Boolean enabled);

    List<Permission> selectByRoleId(Integer roleId);

    List<Permission> selectPermissionByRoleIds(List<Integer> roleIds);

    Permission getPermissionWithRolesByUrl(String url);
}