package cn.hotel.mainserver.controller.basic;

import cn.hotel.mainserver.common.bean.CustomBeanUtils;
import cn.hotel.mainserver.common.validates.ICreateValid;
import cn.hotel.mainserver.common.validates.IUpdateValid;
import cn.hotel.mainserver.model.basic.Admin;
import cn.hotel.mainserver.model.basic.Role;
import cn.hotel.mainserver.pageination.PaginationResult;
import cn.hotel.mainserver.pageination.basic.AdminPage;
import cn.hotel.mainserver.service.basic.AdminService;
import cn.hotel.mainserver.service.basic.RoleService;
import cn.hotel.mainserver.util.response.ResponseVo;
import cn.hotel.mainserver.util.security.SecuritySessionUtil;
import cn.hotel.mainserver.vo.basic.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Johnson
 * @date 2020/03/22/ 10:42:04
 */
@RestController
@RequestMapping("/basic/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    @GetMapping("/current")
    public ResponseVo current() {
        Admin admin = SecuritySessionUtil.getSessionAdmin();
        Map<String, Object> map = new HashMap<>();
        map.put("username", admin.getUsername());
        map.put("nickname", admin.getNickname());
        map.put("userImg", admin.getUserImg());

        List<String> roleNames = new ArrayList<>(admin.getRoles().size());
        List<Integer> roleIds = new ArrayList<>(admin.getRoles().size());
        for (Role role : admin.getRoles()) {

            roleIds.add(role.getId());
            roleNames.add(role.getName());
        }
        map.put("roles", roleNames);
        List<String> permissions = roleService.selectPermissionUrlByIds(roleIds);
        map.put("permissions", permissions);
        return ResponseVo.successData(map);
    }

    @PostMapping("/table")
    public ResponseVo table(@RequestBody AdminPage limit) {
        PaginationResult pagination = adminService.table(limit);
        return ResponseVo.successData(pagination);
    }

    @PostMapping("/enabled")
    public ResponseVo enabled(@RequestBody Map<String, String> map) {
        Admin admin = new Admin();
        admin.setId(Integer.valueOf(map.get("id")));
        admin.setEnabled(Boolean.valueOf(map.get("enabled")));
        adminService.update(admin);

        return ResponseVo.success("更新成功");
    }

    @GetMapping("/find/{id}")
    public ResponseVo find(@PathVariable Integer id) {
        Admin admin = adminService.findById(id);
        AdminVo adminVo = new AdminVo();
        CustomBeanUtils.copyProperties(admin, adminVo);

        List<Integer> roleIds = new ArrayList<>(admin.getRoles().size());
        admin.getRoles().forEach((role -> {
            roleIds.add(role.getId());
        }));

        adminVo.setRoleIds(roleIds);
        return ResponseVo.successData(adminVo);
    }

    @PutMapping("/create")
    public ResponseVo create(@RequestBody @Validated(value = {ICreateValid.class}) AdminVo adminVo) {
        Admin admin = new Admin();
        CustomBeanUtils.copyProperties(adminVo, admin);
        adminService.create(admin, adminVo.getRoleIds());
        return ResponseVo.success("添加成功");
    }

    @PostMapping(value = "/update")
    public ResponseVo update(@RequestBody @Validated(value = {IUpdateValid.class}) AdminVo adminVo) {
        Admin admin = new Admin();
        CustomBeanUtils.copyProperties(adminVo, admin);
        adminService.updateAndRole(admin, adminVo.getRoleIds());
        return ResponseVo.success("更新成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        adminService.delete(id);
        return ResponseVo.success("删除成功");
    }
}
