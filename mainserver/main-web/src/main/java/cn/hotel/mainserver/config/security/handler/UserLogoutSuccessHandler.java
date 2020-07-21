package cn.hotel.mainserver.config.security.handler;

import cn.hotel.mainserver.util.response.ResponseJson;
import cn.hotel.mainserver.util.response.ResponseVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销成功处理器
 * @author Johnson
 * @date 2020/01/10/ 18:19:29
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseVo error = ResponseVo.success("注销成功");
        ResponseJson.responseJson(response, error);
    }
}
