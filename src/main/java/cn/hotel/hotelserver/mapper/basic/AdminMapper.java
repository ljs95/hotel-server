package cn.hotel.hotelserver.mapper.basic;

import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.vo.basic.AdminPagination;

import java.util.List;

public interface AdminMapper {

    void insert(Admin admin);

    Admin loadUserByUsername(String username);

    List<Admin> table(AdminPagination pagination);

    Integer tableCount(AdminPagination pagination);

    void update(Admin admin);

    Admin find(Integer id);

    void delete(Integer id);
}
