package cn.hotel.hotelserver.mapper.basic;

import cn.hotel.hotelserver.model.basic.Admin;

public interface AdminMapper {

    void insert(Admin admin);

    Admin loadUserByUsername(String username);
}
