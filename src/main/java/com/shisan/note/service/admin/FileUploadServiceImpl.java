package com.shisan.note.service.admin;

import cn.shisan.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    // 本地文件存储路径 在项目根路径下
    private static final String FILE_PATH = "/uploads/";
    // base64图片固定前缀
    private static final String BASE_PREFIX = "data:image/png;base64,";

    @Override
    public List<String> upload(MultipartFile[] files) {
        final List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            final String fileName = upload(file);
            fileNames.add(fileName);
        }
        return fileNames;
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new BusinessException("File does not exist");
        }
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // uuid 生成文件名
            String uuid = String.valueOf(UUID.randomUUID());
            String basePath = System.getProperty("user.dir") + FILE_PATH;
            String fileName = uuid + fileSuffix;
            File fileExist = new File(basePath);
            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }
            File file = new File(basePath, fileName);
            multipartFile.transferTo(file);
            return fileName;
        } catch (Exception e) {
            log.info("保存文件失败:", e);
            throw new BusinessException("Failed to save file");
        }
    }

    @Override
    public String getBase64(String file) {
        try {
            String basePath = System.getProperty("user.dir") + FILE_PATH;
            byte[] b = Files.readAllBytes(Paths.get(basePath + file));
            return BASE_PREFIX + Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            log.info("查询文件失败:", e);
            throw new BusinessException("File does not exist");
        }
    }

    @Override
    public Boolean delete(String file) {
        String basePath = System.getProperty("user.dir") + FILE_PATH;
        Path path = Paths.get(basePath + file);
        return path.toFile().delete();
    }

}
