package cn.hotel.hotelserver.config.security.jwt;

import cn.hotel.hotelserver.model.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT工具类
 *
 * @author Johnson
 * @date 2020/01/10/ 16:37:40
 */
public class JWTTokenUtil {

    /**
     * 私有化构造器
     */
    private JWTTokenUtil(){}

    /**
     * 生成Token
     * @author Johnson
     */
    public static String createAccessToken(Admin admin) {
        String token = Jwts.builder()
                // 放入用户ID
                .setId(admin.getId() + "")
                // 主题
                .setSubject(admin.getUsername())
                // 生效时间
                .setIssuedAt(new Date())
                // 签发着
                .setIssuer("Johnson")
                // 自定义属性 这里放入用户的权限
                .claim("authorities", admin.getAuthorities())
                // token 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.getExpiration()))
                // 签名算法 和 秘钥
                .signWith(SignatureAlgorithm.HS512, JWTConfig.getSecret())
                .compact();
        
        return JWTConfig.getTokenPrefix() + token;
    }
}
