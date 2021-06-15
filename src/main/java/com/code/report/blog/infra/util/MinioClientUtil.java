package com.code.report.blog.infra.util;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhaotianxin
 * @date 2021-04-19 17:19
 */
@Component
public class MinioClientUtil implements InitializingBean {
    @Value("${minio.endPoint}")
    private String endPoint;
    @Value("${minio.user}")
    private String user;
    @Value("${minio.pass}")
    private String pass;

    private MinioClient minioClient;

    public MinioClientUtil() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        minioClient = MinioClient.builder().endpoint(endPoint).credentials(user, pass).build();
    }

    public boolean bucketExists(String bucketName){
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket("bucketName").build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createBucketName(String bucketName){
        if(!bucketExists(bucketName)){
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String uploadFile(MultipartFile file, String bucketName) throws Exception {

        //判断存储桶是否存在  不存在则创建
        createBucketName(bucketName);
        //文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名 = 存储桶文件名_时间戳.后缀名
        String fileName = bucketName + "_" +
                System.currentTimeMillis() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //开始上传
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(),file.getSize(), -1)
                .build();
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);
        String url = "/" + bucketName + "/" + fileName;
        return url;
    }

}
