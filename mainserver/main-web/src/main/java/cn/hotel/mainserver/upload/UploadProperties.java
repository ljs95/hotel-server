package cn.hotel.mainserver.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Johnson
 * @date 2019/12/16/ 09:35:36
 * 文件配置类
 */
@Component
@ConfigurationProperties(prefix = "file")
public class UploadProperties {

    private Local local = new Local();

    public Local getLocal() {
        return local;
    }

    /**
     * @author: Johnson
     * @Date: 2019/12/16
     * 本地上传配置
     */
    public class Local {
        /**
         * 实际保存路径
         */
        private String path;

        /**
         * 服务器映射路径
         */
        private String serverPath;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getServerPath() {
            return serverPath;
        }

        public void setServerPath(String serverPath) {
            this.serverPath = serverPath;
        }
    }
}
