package com.example.onlimitedauction.web.home.controller;

import com.example.onlimitedauction.global.Image.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Log4j2
@RestController
@RequiredArgsConstructor
public class testRestController {
    private final ImageService imageService;

    @GetMapping("/testrest")
    public ResponseEntity<?> getTest(){
        return ResponseEntity.ok("test sucesess");
    }


    @PostMapping("/images/upload")
    public ResponseEntity saveImage(MultipartFile file){
        try{
            imageService.saveImage(file);
        }catch (Exception e){
            e.getStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/images/read")
    public ResponseEntity readImage(@RequestParam("fileName") String fileName){
        try{
            log.info("filename = "+ fileName);
            Resource result = imageService.readImage(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(result);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    @PatchMapping("/images/delete")
    public ResponseEntity deleteImage(@RequestBody String fileName){
        try{
            imageService.deleteImage(fileName);
            return ResponseEntity.ok().body(null);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
