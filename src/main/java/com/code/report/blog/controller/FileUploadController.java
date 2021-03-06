package com.code.report.blog.controller;

import com.code.report.blog.serivce.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaotianxin
 * @date 2021-04-19 17:40
 */
@RestController
@RequestMapping("/v1/upload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<String> upload(MultipartFile avatar) {
        return ResponseEntity.ok(fileUploadService.uploadFile(avatar));
    }
}
