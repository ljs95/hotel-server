package cn.hotel.hotelserver.mapper.basic;

import cn.hotel.hotelserver.model.basic.Role;

import java.util.List;

public interface RoleMapper {

    void insert(Role role);

    List<Role> getRolesByAdminId(Integer adminId);
}
