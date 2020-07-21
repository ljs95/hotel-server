package cn.hotel.mainserver.config.security.jwt;

import cn.hotel.mainserver.util.response.ResponseJson;
import cn.hotel.mainserver.util.response.ResponseVo;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Johnson
 * @date 2020/01/13/ 17:41:40
 */
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JWTConfig.getTokenHeader());
        if (tokenHeader == null || !tokenHeader.startsWith(JWTConfig.getTokenPrefix())) {
            ResponseVo error = ResponseVo.error(1105, "没有token令牌, 请不要瞎搞");
            ResponseJson.responseJson(response, error);
            return;
        }

        // 截取JWT前缀
        String token = tokenHeader.replace(JWTConfig.getTokenPrefix(), "");

        try {
            // 解析JWT
            Claims claims = Jwts.parser()
                    .setSigningKey(JWTConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();

            // 获取用户名和id
            String username = claims.getSubject();
            String userId = claims.getId();
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(userId)) {
                ResponseVo error = ResponseVo.error(1105,"非法令牌！拉你进黑名单啦！");
                ResponseJson.responseJson(response, error);
                return;
            }
        } catch (ExpiredJwtException exception) {
            ResponseVo error = ResponseVo.error(1105,"token访问令牌已过期，请重新登录");
            ResponseJson.responseJson(response, error);
            return;
        } catch (MalformedJwtException | SignatureException exception) {
            ResponseVo error = ResponseVo.error(1105,"非法令牌！拉你进黑名单啦！");
            ResponseJson.responseJson(response, error);
            return;
        }

        chain.doFilter(request, response);
    }
}
