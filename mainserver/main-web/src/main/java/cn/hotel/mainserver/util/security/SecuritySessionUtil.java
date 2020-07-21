package cn.hotel.mainserver.util.security;

import cn.hotel.mainserver.model.basic.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
* Session工具类
* @author Johnson
* @date  2020/3/22
*/
public class SecuritySessionUtil {

    /**
    * @author Johnson
    */
    public static Admin getSessionAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
