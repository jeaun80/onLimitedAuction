package com.example.onlimitedauction.global.Image;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
@Log4j2
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    @Override
    public String saveImage(MultipartFile imageFile) {
        if(!validateFile(imageFile)){
            throw new RuntimeException();
        }

        String fileName = FileGeneratorUtil.generateFileName(imageFile.getOriginalFilename()); // 2번
        Path filePath = Paths.get(fileName);

        try {
            imageFile.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public Resource readImage(String fileName) {

        Resource resource = new FileSystemResource(fileName);

        if (!resource.exists() || !resource.isReadable()) {
            log.info("can not read image");
            throw new RuntimeException();
        }
        return resource;
    }

    @Override
    public String updateImage(String currentFileName,MultipartFile imageFile) {

        if(!validateFile(imageFile)){
            throw new RuntimeException();
        }

        try{
            String updateImage = saveImage(imageFile);
            deleteImage(currentFileName);
            return updateImage;
        }catch (Exception e){
            log.info("can not update image");
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void deleteImage(String fileName) {

        File file = new File(fileName);
        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException();
        }
        file.delete();
    }

    @Override
    public boolean validateFile(MultipartFile file){
        if(file.isEmpty() || file.getSize()==0){
            return false;
        }
        return  true;
    }
}
