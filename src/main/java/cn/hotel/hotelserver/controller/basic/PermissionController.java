package cn.hotel.hotelserver.controller.basic;

import cn.hotel.hotelserver.model.basic.Permission;
import cn.hotel.hotelserver.service.basic.PermissionService;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.util.bean.ColaBeanUtils;
import cn.hotel.hotelserver.vo.basic.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Johnson
 * @date 2020/04/5/ 13:42:04
 */
@RestController
@RequestMapping("/basic/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/tree")
    public ResponseVo treeData() {
        List<Permission> permissionList = permissionService.treeData(null);
        return ResponseVo.successData(permissionList);
    }

    @PutMapping("/create")
    public ResponseVo create(@RequestBody PermissionVo permissionVo) {
        Permission permission = new Permission();
        ColaBeanUtils.copyProperties(permissionVo, permission);
        permissionService.create(permission);
        return ResponseVo.success("创建成功");
    }

    @PostMapping("/update")
    public ResponseVo update(@RequestBody PermissionVo permissionVo) {
        Permission permission = new Permission();
        ColaBeanUtils.copyProperties(permissionVo, permission);
        permissionService.update(permission);
        return ResponseVo.success("更新成功");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        permissionService.delete(id);
        return ResponseVo.success("删除成功");
    }
}
