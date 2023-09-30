package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.services.helpers.storages.MinioFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    final MinioFileService minioFileService;
    //final LocalFileService localFileService;
    //final StorageService storageService;

    public FileUploadController (MinioFileService minioFileService){

            //LocalFileService localFileService
            //StorageService storageService

        this.minioFileService = minioFileService;
        //this.localFileService = localFileService;
        // Этот сервис является оболочкой для других
        // такоим образом - мне не важно будет в коде вообще количество хранилищ
        //this.storageService = storageService;
    }
    private  static final String UPLOAD_DIR="F:\\tmp\\upload";
    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile uploadFile) throws IOException {
        if(uploadFile.isEmpty())
            return "No File";


        // Версия загрузки в MinIO
       minioFileService.uploadFile("avatar", uploadFile);
//        storageService.to(StorageTypes.minio).uploadFile("avatar",uploadFile);
//        Storage.to(StorageTypes.minio).uploadFile("avatar",uploadFile);

        //Проверяем - есть ли каталог для сохранения файла
        Path uploadDir=Path.of(UPLOAD_DIR);
        Files.createDirectories(uploadDir);

        //Сохраняем файл в нужной директории
        //Настроим полный путь к месту хранения файла
        Path filePath=uploadDir.resolve(uploadFile.getOriginalFilename());

        Files.copy(uploadFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);




        return "Ok";
    }
}
