package com.itstep.hello_spring.services.helpers.storages;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService implements FileUploadServiceInterface
{
    private final MinioFileService minioFileService;
    private final LocalFileService localFileService;
    private final FileUploadServiceInterface defaultStorage;

    /**
     * Этот сервис содержит в себе список всех сервисов для работы с
     * файловыми хранилищами
     * @param minioFileService
     * @param localFileService
     */
    public StorageService(
            MinioFileService minioFileService,
            LocalFileService localFileService) {
        this.minioFileService = minioFileService;
        this.localFileService = localFileService;
        // По умолчанию - я сохроняю файлы локально
        this.defaultStorage = localFileService;
    }

    /**
     * Этим методом мы загружаем файл в хранилище по умолчанию
     * @param bucketName
     * @param uploadFile
     */
    @Override
    public void uploadFile(String bucketName, MultipartFile uploadFile) {
        this.defaultStorage.uploadFile(bucketName, uploadFile);
    }

    /**
     * В этом случае загрузка будет производиться в указанное хранилище
     * @param type
     * @return
     */
    public FileUploadServiceInterface to(StorageTypes type){
        return switch (type) {
            case local -> localFileService;
            case minio -> minioFileService;
        };
    }
}