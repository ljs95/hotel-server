package cn.hotel.hotelserver.controller.basic;

import cn.hotel.hotelserver.model.basic.Menu;
import cn.hotel.hotelserver.service.MenuService;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.util.SecuritySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 *
 * @author Johnson
 * @date 2020/03/22/ 15:33:05
 */
@RestController
@RequestMapping("/basic/role")
public class RoleController {

    @Autowired
    MenuService menuService;

    /**
     * 获取角色菜单路由
     */
    @GetMapping("/menu")
    public ResponseVo getMenus() {
        Integer adminId = SecuritySessionUtil.getSessionAdmin().getId();
        List<Menu> menusByRoleId = menuService.getMenusByAdminId(adminId);
        return ResponseVo.successData(menusByRoleId);
    }
}
