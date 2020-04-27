package cn.hotel.hotelserver.controller.basic;

import cn.hotel.hotelserver.model.basic.Menu;
import cn.hotel.hotelserver.model.basic.Permission;
import cn.hotel.hotelserver.model.basic.Role;
import cn.hotel.hotelserver.service.basic.MenuService;
import cn.hotel.hotelserver.service.basic.PermissionService;
import cn.hotel.hotelserver.service.basic.RoleService;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.util.SecuritySessionUtil;
import cn.hotel.hotelserver.util.bean.ColaBeanUtils;
import cn.hotel.hotelserver.validates.ICreateValid;
import cn.hotel.hotelserver.validates.IUpdateValid;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.basic.RolePage;
import cn.hotel.hotelserver.vo.basic.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    /**
     * 获取角色菜单路由
     */
    @GetMapping("/menu")
    public ResponseVo getMenus() {
        Integer adminId = SecuritySessionUtil.getSessionAdmin().getId();
        List<Menu> menusByRoleId = menuService.getMenusByAdminId(adminId);
        return ResponseVo.successData(menusByRoleId);
    }

    @GetMapping("/all")
    public ResponseVo all() {
        List<Role> roles = roleService.all();
        return ResponseVo.successData(roles);
    }

    @PostMapping("/table")
    public ResponseVo table(@RequestBody @Valid RolePage pagination) {
        PaginationResult result = roleService.table(pagination);
        return ResponseVo.successData(result);
    }

    @GetMapping("/find/{id}")
    public ResponseVo find(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return ResponseVo.successData(role);
    }

    @PostMapping("/update")
    public ResponseVo update(@RequestBody @Validated(value = {IUpdateValid.class}) RoleVo vo) {
        Role role = new Role();
        ColaBeanUtils.copyProperties(vo, role);
        roleService.update(role);
        return ResponseVo.success("更新成功");
    }

    @PutMapping("/create")
    public ResponseVo create(@RequestBody @Validated(value = {ICreateValid.class}) RoleVo vo) {
        Role role = new Role();
        ColaBeanUtils.copyProperties(vo, role);
        roleService.create(role);
        return ResponseVo.success("创建成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseVo.success("删除成功");
    }

    @GetMapping("/permissions/{id}")
    public ResponseVo permissions(@PathVariable Integer id) {
        List<Permission> permissions = roleService.permissions(id);
        return ResponseVo.successData(permissions);
    }

    @PostMapping("/editPermission")
    public ResponseVo editPermission(@RequestBody Map<String, Object> param) {
        Integer id = (Integer)param.get("id");
        List<Integer> permissionIds = (List<Integer>) param.get("permissionIds");
        roleService.updatePermission(id, permissionIds);
        return ResponseVo.success("更新成功");
    }

    @GetMapping("/permissionTree")
    public ResponseVo treeData() {
        List<Permission> permissionList = permissionService.treeData(true);
        return ResponseVo.successData(permissionList);
    }
}
