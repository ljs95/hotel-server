package cn.hotel.hotelserver.config.security;

import cn.hotel.hotelserver.model.basic.Permission;
import cn.hotel.hotelserver.model.basic.Role;
import cn.hotel.hotelserver.service.basic.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 权限访问类
 * 根据用户请求地址，分析出请求所需要的角色
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        Permission permission = permissionService.getPermissionWithRolesByUrl(requestUrl);

        // url没匹配上
        if (permission == null) {
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        List<Role> roles = permission.getRoles();
        String[] strings;
        // 只有超级管理员角色能访问
        if (roles.size() == 0) {
            strings = new String[]{"root"};
        } else {
            strings = new String[roles.size()];
            for (int i = 0; i < roles.size(); i++) {
                strings[i] = roles.get(i).getName();
            }
        }

        /* 返回该请求路径所需要的角色 **/
        return SecurityConfig.createList(strings);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
