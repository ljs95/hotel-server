package cn.hotel.hotelserver.util.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Johnson
 * @date 2019/12/14/ 17:03:59
 * 文件上传接口
 */
public interface IUpload {

    /**
     * @author: Johnson
     * 文件上传
     */
    String upload(MultipartFile file);

    /**
     * @author: Johnson
     * 删除文件
     */
    boolean delete(String filename);
}
