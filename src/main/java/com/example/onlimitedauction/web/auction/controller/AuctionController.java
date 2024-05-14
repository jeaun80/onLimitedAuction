package com.example.onlimitedauction.web.auction.controller;


import com.example.onlimitedauction.web.auction.dto.*;
import com.example.onlimitedauction.web.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api/Auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService AuctionService;


    @GetMapping()
    public ResponseEntity readAllAuction(
            @RequestParam(value = "pageIndex") Integer pageIndex,
            @RequestParam(value = "topId", required = false, defaultValue = "0") Long topId,
            @RequestParam(value = "sizePerPage", required = false, defaultValue = "12") Integer sizePerPage){

        try{
            ResponseReadAllAuctionDto responseDto = AuctionService.readAllAuction(pageIndex,sizePerPage,topId);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity readAuction(@PathVariable Long id){
        try{
            ResponseReadAuctionDto responsedto = AuctionService.readAuction(id);
log.info(responsedto.getId());
            return new ResponseEntity(responsedto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity createAuction(@RequestBody RequestCreateAuctionDto requestCreateAuctionDto){
        try{
            ResponseCreateAuctionDto responseDto = AuctionService.createAuction(requestCreateAuctionDto);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuction(@PathVariable Long id){
        try{
            ResponseDeleteAuctionDto responseDto = AuctionService.deleteAuction(id);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity AuctionVideoUpload( RequestUploadAuctionDto requestUploadAuctionDto){
        try{
            ResponseUploadAuctionDto responseDto = AuctionService.VideoUpload(requestUploadAuctionDto);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/status")
    public ResponseEntity updateAuctionStatus(@RequestBody RequestUpdateStatusAuctionDto requestDto){
        try{
            Long id = AuctionService.updateStatus(requestDto);

            return new ResponseEntity(id,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/streamkey/{id}")
    public ResponseEntity getStreamKey(@PathVariable Long id){
        try {
            String key = AuctionService.getStreamKey(id);

            return new ResponseEntity(key, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
//
//    @GetMapping("/socket")
//    public ResponseEntity createWebSocket(Long id){
//        try{
//        }catch (Exception e){
//
//        }
//
//    }

}
