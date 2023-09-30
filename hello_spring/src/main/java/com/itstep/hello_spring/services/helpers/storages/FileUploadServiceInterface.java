package com.itstep.hello_spring.services.helpers.storages;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadServiceInterface {
    public void uploadFile(String bucketName,  MultipartFile uploadFile);
}
