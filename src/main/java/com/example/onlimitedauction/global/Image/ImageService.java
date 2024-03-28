package com.example.onlimitedauction.global.Image;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;


public interface ImageService {


    String saveImage(MultipartFile imageFile);
    void deleteImage(String fileName);
    String updateImage(String currentFileName,MultipartFile imageFile);
    Resource readImage(String fileNmae);
    boolean validateFile(MultipartFile file);
}
