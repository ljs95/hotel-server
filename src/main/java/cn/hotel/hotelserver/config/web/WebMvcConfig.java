package cn.hotel.hotelserver.config.web;

import cn.hotel.hotelserver.util.upload.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Johnson
 * @date 2019/12/16/ 14:26:14
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    UploadProperties properties;

    /**
     * @author: Johnson
     * 文件路径映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = properties.getLocal().getPath() + "/";
        String serverPath = properties.getLocal().getServerPath() + "/**";
        registry.addResourceHandler(serverPath).addResourceLocations("file:" + path);
    }
}
