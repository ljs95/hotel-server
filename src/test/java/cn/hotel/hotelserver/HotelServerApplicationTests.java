package cn.hotel.hotelserver;

import cn.hotel.hotelserver.mapper.basic.AdminMapper;
import cn.hotel.hotelserver.mapper.basic.MenuMapper;
import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.model.basic.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelServerApplicationTests {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    MenuMapper menuMapper;

    @Test
    void contextLoads() {
        Admin admin = new Admin();
        admin.setUsername("johnson");
        admin.setPassword("123456");
        admin.setNickname("甩锅");
        admin.setEnabled(true);

        adminMapper.insert(admin);
    }

}
