package com.itstep.hello_spring.services.helpers.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Storage {
    private static StorageService storageService;
    @Autowired
    public void setStorageService(StorageService storageService) {
        Storage.storageService = storageService;
    }

    public static FileUploadServiceInterface to(StorageTypes type) {
        return storageService.to(type);
    }

    public static void uploadFile(String bucketName, MultipartFile uploadFile) {
        storageService.uploadFile(bucketName, uploadFile);
    }
}
