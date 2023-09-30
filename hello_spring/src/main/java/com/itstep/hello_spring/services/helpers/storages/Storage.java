//package com.itstep.hello_spring.services.storages;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//@Component
//public class Storage implements FileUploadServiceInterface{
//
//    private final LocalFileService localFileService;
//    private final MinioFileService minioFileService;
//    protected static Storage instance;
//
//    public Storage(MinioFileService minioFileService, LocalFileService localFileService){
//        this.minioFileService=minioFileService;
//        this.localFileService=localFileService;
//    }
//    public static Storage getInstance(){
//        if(instance==null){
//            instance=new Storage();
//            return instance;
//        }
//    }
//    @Override
//    public static void uploadFile(String bucketname, MultipartFile uploadFile){
//
//    }
//}
