package cn.hotel.hotelserver;

import cn.hotel.hotelserver.mapper.basic.AdminMapper;
import cn.hotel.hotelserver.mapper.basic.MenuMapper;
import cn.hotel.hotelserver.service.basic.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelServerApplicationTests {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    PermissionService permissionService;

    @Test
    void contextLoads() {
    }

}
