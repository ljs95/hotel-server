package cn.hotel.mainserver.upload;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Johnson
 * @date 2019/12/14/ 17:05:20
 * 上传文件到本地
 */
public class LocalFile implements IUpload {

    private UploadProperties.Local properties;

    private HttpServletRequest request;

    public LocalFile(HttpServletRequest request, UploadProperties.Local properties) {
        this.request = request;
        this.properties = properties;
    }

    /**
    * @author: Johnson
    */
    private String pathPrefix(){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + properties.getServerPath();
    }

    /**
     * @author: Johnson
     */
    @Override
    public String upload(MultipartFile file) {
        //格式化当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());

        String filePath = properties.getPath() + format;
        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs(); // 注意是mkdirs 不是mkdir,需要递归生成目录
        }

        String originalFilename = file.getOriginalFilename();
        // 文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newName = UUID.randomUUID().toString() + suffix;

        try {
            file.transferTo(new File(folder, newName));
            return pathPrefix() + format + newName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    @Override
    public boolean delete(String filename) {
        String s = filename.split(pathPrefix())[1];
        String filePath = properties.getPath() + s;
        File file = new File(filePath);
        return file.delete();
    }
}
