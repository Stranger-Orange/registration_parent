package com.orange.registration.oss.controller;

import com.orange.registration.common.result.Result;
import com.orange.registration.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Orange
 * @create 2021-06-07 19:01
 */
@RestController
@RequestMapping("/api/oss/file")
public class FileApiController {

    @Autowired
    FileService fileService;

    /**
     * 上传文件到阿里云oss
     * @param file
     * @return
     */
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) {
        //获取上传文件
        String url = fileService.upload(file);
        return Result.ok(url);
    }
}
