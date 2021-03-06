package cn.hotel.mainserver.config.security.handler;

import cn.hotel.mainserver.config.security.jwt.JWTTokenUtil;
import cn.hotel.mainserver.model.basic.Admin;
import cn.hotel.mainserver.util.response.ResponseJson;
import cn.hotel.mainserver.util.response.ResponseVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 *
 * @author Johnson
 * @date 2020/4/1
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Admin admin = (Admin) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(admin);

        ResponseVo success = ResponseVo.success("登录成功", token);
        ResponseJson.responseJson(response, success);
    }
}
