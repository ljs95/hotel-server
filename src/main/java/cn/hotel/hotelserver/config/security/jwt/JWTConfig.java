package cn.hotel.hotelserver.config.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT配置类
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {

    /**
     * 密钥KEY
     */
    private static String secret;
    /**
     * TokenKey
     */
    private static String tokenHeader;
    /**
     * Token前缀字符
     */
    private static String tokenPrefix;
    /**
     * 过期时间
     */
    private static Integer expiration;

    public void setSecret(String secret) {
        JWTConfig.secret = secret;
    }

    public void setTokenHeader(String tokenHeader) {
        JWTConfig.tokenHeader = tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix) {
        JWTConfig.tokenPrefix = tokenPrefix;
    }

    public void setExpiration(Integer expiration) {
        JWTConfig.expiration = expiration * 1000;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getTokenHeader() {
        return tokenHeader;
    }

    public static String getTokenPrefix() {
        return tokenPrefix;
    }

    public static Integer getExpiration() {
        return expiration;
    }
}