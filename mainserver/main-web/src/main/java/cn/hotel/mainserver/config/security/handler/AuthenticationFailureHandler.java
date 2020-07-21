package cn.hotel.mainserver.config.security.handler;

import cn.hotel.mainserver.util.response.ResponseJson;
import cn.hotel.mainserver.util.response.ResponseVo;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 * @author Johnson
 * @date 2020/01/13/ 17:38:27
 */
@Component
public class AuthenticationFailureHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String msg;
        if (authException instanceof InsufficientAuthenticationException) {
            msg = "请登录后访问";
        } else {
            msg = "请求失败, 请联系管理员";
        }
        ResponseVo error = ResponseVo.error(msg);
        ResponseJson.responseJson(response, error);
    }
}
