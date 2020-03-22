package cn.hotel.hotelserver.controller.basic;

import cn.hotel.hotelserver.model.basic.Admin;
import cn.hotel.hotelserver.util.ResponseVo;
import cn.hotel.hotelserver.util.SecuritySessionUtil;
import cn.hotel.hotelserver.vo.basic.AdminVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Johnson
 * @date 2020/03/22/ 10:42:04
 */
@RestController
@RequestMapping("/basic/admin")
public class AdminController {

    @GetMapping("/current")
    public ResponseVo current() {
        Admin admin = SecuritySessionUtil.getSessionAdmin();
        AdminVo adminVo = new AdminVo();
        BeanUtils.copyProperties(admin, adminVo);
        return ResponseVo.successData(adminVo);
    }
}
