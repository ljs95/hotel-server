package cn.hotel.hotelserver.mapper;

import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.model.basic.Role;

import java.util.List;

public interface AdminMapper {

    void insert(Admin admin);

    Admin loadUserByUsername(String username);

}
