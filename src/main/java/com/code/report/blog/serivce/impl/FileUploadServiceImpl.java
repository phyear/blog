package com.code.report.blog.serivce.impl;

import com.code.report.blog.infra.util.MinioClientUtil;
import com.code.report.blog.serivce.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaotianxin
 * @date 2021-04-19 17:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    MinioClientUtil minioClientUtil;

    @Override
    public String uploadFile(MultipartFile multipartFile){
        try {
            return minioClientUtil.uploadFile(multipartFile, "blog");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
