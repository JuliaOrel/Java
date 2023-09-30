package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.services.helpers.storages.LocalFileService;
import com.itstep.hello_spring.services.helpers.storages.MinioFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;


@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    final MinioFileService minioFileService;
    final LocalFileService localFileService;


    public FileUploadController (MinioFileService minioFileService, LocalFileService localFileService){

        this.minioFileService = minioFileService;
        this.localFileService = localFileService;
        // Этот сервис является оболочкой для других
        // такоим образом - мне не важно будет в коде вообще количество хранилищ
        //this.storageService = storageService;
    }
    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    @GetMapping("/get/{filename}")
    public ResponseEntity<Resource> getBodyByFileName(@PathVariable String filename) throws IOException {
        Path filePath = Path.of(UPLOAD_DIR, filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename); // Это указывает браузеру на скачивание файла

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            // В случае, если файл не существует или не может быть прочитан, возвращаем 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile uploadFile) throws IOException {
        if(uploadFile.isEmpty())
            return "No File";


        // Версия загрузки в MinIO
       minioFileService.uploadFile("avatar", uploadFile);
//        storageService.to(StorageTypes.minio).uploadFile("avatar",uploadFile);
//        Storage.to(StorageTypes.minio).uploadFile("avatar",uploadFile);

        //Local uploading
       localFileService.uploadFile("avatar", uploadFile);

        // SOLID

//        FileUploadServiceInterface service = minioFileService;
//        service.uploadFile("avatar", uploadFile);
//
//        service = localFileService;
//        service.uploadFile("avatar", uploadFile);

        return "Ok";
    }
}
