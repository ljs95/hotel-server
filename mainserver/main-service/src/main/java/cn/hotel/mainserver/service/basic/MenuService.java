package cn.hotel.mainserver.service.basic;

import cn.hotel.mainserver.mapper.basic.MenuMapper;
import cn.hotel.mainserver.model.basic.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Johnson
 * @date 2020/03/22/ 14:49:30
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenusByAdminId(Integer adminId) {
        List<Menu> menus;
        menus = menuMapper.getMenusByAdminId(adminId);
        return menus;
    }
}
