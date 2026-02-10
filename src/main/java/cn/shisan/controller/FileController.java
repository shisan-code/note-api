package com.shisan.note.controller;

import cn.shisan.common.domain.common.JResult;
import com.shisan.note.service.sys.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "本地文件上传API")
@RestController
@RequestMapping("/api/local/file")
@RequiredArgsConstructor
public class FileController extends BaseController {


    private final FileUploadService fileUploadService;
    private final ResourceLoader resourceLoader;

    @ApiOperation("多文件上传")
    @PostMapping("/uploads")
    public JResult<List<String>> uploads(@RequestParam("files") MultipartFile[] files) {
        final List<String> fileNames = fileUploadService.upload(files);
        return success(fileNames);
    }

    @ApiOperation("单文件上传")
    @PostMapping("/upload")
    public JResult<String> upload(@RequestParam("file") MultipartFile file) {
        final String upload = fileUploadService.upload(file);
        return success(upload);
    }

    @ApiOperation("获取图片base64")
    @PostMapping("/getBase64")
    public JResult<String> getBase64(@RequestParam("file") String file) {
        final String upload = fileUploadService.getBase64(file);
        return success(upload);
    }

    @ApiOperation("删除文件")
    @PostMapping("/delete")
    public JResult<Boolean> delete(@RequestParam("file") String file) {
        final Boolean upload = fileUploadService.delete(file);
        return success(upload);
    }

    @ApiOperation("图片访问")
    @GetMapping("/img/{file}")
    public ResponseEntity<Resource> img(@PathVariable String file) {
        String property = System.getProperty("user.dir");
        Resource resource = resourceLoader.getResource("file:"+property + "/uploads/" + file);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }

}
