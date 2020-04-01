package cn.hotel.hotelserver.controller.basic;

import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.validates.ICreateValid;
import cn.hotel.hotelserver.service.AdminService;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.util.SecuritySessionUtil;
import cn.hotel.hotelserver.util.bean.ColaBeanUtils;
import cn.hotel.hotelserver.validates.IUpdateValid;
import cn.hotel.hotelserver.vo.PaginationResult;
import cn.hotel.hotelserver.vo.basic.AdminPagination;
import cn.hotel.hotelserver.vo.basic.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/current")
    public ResponseVo current() {
        Admin admin = SecuritySessionUtil.getSessionAdmin();
        return ResponseVo.successData(admin);
    }

    @PostMapping("/table")
    public ResponseVo table(@RequestBody AdminPagination limit) {
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
        ColaBeanUtils.copyProperties(admin, adminVo);

        List<Integer> roleIds = new ArrayList<>(admin.getRoles().size());
        admin.getRoles().forEach((role -> {
            roleIds.add(role.getId());
        }));

        adminVo.setRoleIds(roleIds);
        return ResponseVo.successData(adminVo);
    }

    @PutMapping("/create")
    public ResponseVo create(@RequestBody @Validated(value = {ICreateValid.class}) AdminVo adminVo) {
        adminService.create(adminVo);
        return ResponseVo.success("添加成功");
    }

    @PostMapping(value = "/update")
    public ResponseVo update(@RequestBody @Validated(value = {IUpdateValid.class}) AdminVo adminVo) {
        adminService.updateAndRole(adminVo);
        return ResponseVo.success("更新成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        adminService.delete(id);
        return ResponseVo.success("删除成功");
    }
}
