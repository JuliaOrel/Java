package com.example.poemapp.services;

import com.example.poemapp.services.interfaces.FileUploadServiceInterface;
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
    @Override
    public String uploadFile(String bucketName, MultipartFile uploadFile) {
        Path filePath;
        try{
            // Создаю папки
            Path uploadDir = Path.of(UPLOAD_DIR);
            Files.createDirectories(uploadDir);

            Path fileUploadDir = Path.of(UPLOAD_DIR, bucketName);
            Files.createDirectories(fileUploadDir);

            // Копирую файл
            filePath = fileUploadDir.resolve(uploadFile.getOriginalFilename());
            Files.copy(uploadFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            // return filePath.toString();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
        return filePath.toString();
    }
}
