package com.example.poemapp.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadServiceInterface {
    public String uploadFile(String bucketName, MultipartFile uploadFile);
}
