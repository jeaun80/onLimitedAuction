package com.example.onlimitedauction.web.bid.controller;


import com.example.onlimitedauction.web.bid.dto.*;
import com.example.onlimitedauction.web.bid.entity.Bid;
import com.example.onlimitedauction.web.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api/bid")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;


    @GetMapping()
    public ResponseEntity readAllBid(
            @RequestParam(value = "pageIndex") Integer pageIndex,
            @RequestParam(value = "topId", required = false, defaultValue = "0") Long topId,
            @RequestParam(value = "sizePerPage", required = false, defaultValue = "12") Integer sizePerPage){

        try{
            ResponseReadAllBidDto responseDto = bidService.readAllBid(pageIndex,sizePerPage,topId);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity readBid(@PathVariable Long id){
        try{
            ResponseReadBidDto responsedto = bidService.readBid(id);
log.info(responsedto.getId());
            return new ResponseEntity(responsedto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity createBid(@RequestBody RequestCreateBidDto requestCreateBidDto){
        try{
            ResponseCreateBidDto responseDto = bidService.createBid(requestCreateBidDto);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBid(@PathVariable Long id){
        try{
            ResponseDeleteBidDto responseDto = bidService.deleteBid(id);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity bidVideoUpload( RequestUploadBidDto requestUploadBidDto){
        try{
            ResponseUploadBidDto responseDto = bidService.VideoUpload(requestUploadBidDto);

            return new ResponseEntity(responseDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/status")
    public ResponseEntity updateBidStatus(@RequestBody RequestUpdateStatusBidDto requestDto){
        try{
            Long id = bidService.updateStatus(requestDto);

            return new ResponseEntity(id,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/streamkey/{id}")
    public ResponseEntity getStreamKey(@PathVariable Long id){
        try {
            String key = bidService.getStreamKey(id);

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
