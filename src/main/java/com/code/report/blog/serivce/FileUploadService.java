package com.code.report.blog.serivce;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhaotianxin
 * @date 2021-04-19 17:41
 */
public interface FileUploadService {
    String uploadFile(MultipartFile multipartFile) ;
}
