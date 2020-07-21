package cn.hotel.mainserver.controller;

import cn.hotel.mainserver.upload.IUpload;
import cn.hotel.mainserver.upload.LocalFile;
import cn.hotel.mainserver.upload.UploadProperties;
import cn.hotel.mainserver.util.response.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Johnson
 * @date 2020/03/31/ 14:37:06
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadProperties uploadProperties;

    /**
     * 单文件上传
     * @return ResponseVo
     */
    @PostMapping("/upload")
    public ResponseVo upload(HttpServletRequest request, MultipartFile file) {
        IUpload upload = new LocalFile(request, uploadProperties.getLocal());
        String url = upload.upload(file);
        return ResponseVo.success("success", url);
    }
}
