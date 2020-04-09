package cn.hotel.hotelserver.mapper.basic;

import cn.hotel.hotelserver.model.basic.Permission;

import java.util.List;

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