package com.itstep.hello_spring.services.helpers.storages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileService implements FileUploadServiceInterface {

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    public void uploadFile(String bucketName,  MultipartFile uploadFile) {
        try {
            // Создаю папки
            Path uploadDir = Path.of(UPLOAD_DIR);
            Files.createDirectories(uploadDir);

            Path fileUploadDir = Path.of(UPLOAD_DIR, bucketName);
            Files.createDirectories(fileUploadDir);

            // Копирую файл
            Path filePath = fileUploadDir.resolve(uploadFile.getOriginalFilename());
            Files.copy(uploadFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}