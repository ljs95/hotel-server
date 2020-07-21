package cn.hotel.mainserver.config.security.handler;

import cn.hotel.mainserver.util.response.ResponseJson;
import cn.hotel.mainserver.util.response.ResponseVo;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 *
 * @author Johnson
 * @date 2020/4/1
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String msg;
        if (exception instanceof LockedException) {
            msg = "账户被锁定，请联系管理员";
        } else if (exception instanceof DisabledException) {
            msg = "账户被禁用，请联系管理员";
        } else if (exception instanceof AccountExpiredException) {
            msg = "账户过期，请联系管理员";
        } else if (exception instanceof BadCredentialsException) {
            msg = "用户或密码输入错误，请重新输入";
        } else {
            msg = exception.getMessage();
        }

        ResponseVo error = ResponseVo.error(msg);
        ResponseJson.responseJson(response, error);
    }
}
