package com.shisan.note.service.sys;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    /**
     * 文件上传
     * @param files 文件
     */
    List<String> upload(MultipartFile[] files);

    /**
     * 文件上传
     * @param file 文件
     * @return 文件地址
     */
    String upload(MultipartFile file);

    /**
     * 获取文件base64
     * @param file 文件地址
     * @return base64
     */
    String getBase64(String file);

    /**
     * 文件删除
     * @param file 文件地址
     */
    Boolean delete(String file);
}
