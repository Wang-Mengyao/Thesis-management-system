package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    /**
     * 文件上传接口
     * @param file 前端传过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        // 存储到磁盘
        File uploadFileDir = new File(fileUploadPath);
        if(!uploadFileDir.exists()) {
            uploadFileDir.mkdirs();
        }
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        File uploadFile = new File(fileUploadPath+uuid+".pdf");
        file.transferTo(uploadFile);
        return "";
    }
}
