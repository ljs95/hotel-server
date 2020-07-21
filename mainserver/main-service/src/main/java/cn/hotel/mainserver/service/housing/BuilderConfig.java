package cn.hotel.mainserver.service.housing;

import cn.hotel.mainserver.service.housing.bo.OpenRoomBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Johnson
 * @date 2020/07/21/ 15:15:15
 */
@Configuration
public class BuilderConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public OpenRoomBo.Builder openRoomBoBuilder() {
        return new OpenRoomBo.Builder();
    }
}
