package cn.hotel.hotelserver.mapper.basic;

import cn.hotel.hotelserver.model.basic.Menu;

import java.util.List;

/**
 * @author Johnson
 * @date 2020/03/22/ 14:32:33
 */
public interface MenuMapper {

    void insert(Menu menu);

    List<Menu> getMenusByAdminId(Integer adminId);
}
