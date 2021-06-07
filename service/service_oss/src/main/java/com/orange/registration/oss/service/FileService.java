package com.orange.registration.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Orange
 * @create 2021-06-07 19:03
 */
public interface FileService {
    /**
     * 上传文件到阿里云oss
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
