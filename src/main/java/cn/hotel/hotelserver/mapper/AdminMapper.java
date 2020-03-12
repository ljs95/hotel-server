package cn.hotel.hotelserver.mapper;

import cn.hotel.hotelserver.model.Admin;

public interface AdminMapper {

    void insert(Admin admin);

    Admin loadUserByUsername(String username);
}
