package com.example.onlimitedauction.web.item.controller;


import com.example.onlimitedauction.web.item.dto.*;
import com.example.onlimitedauction.web.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping()
    public ResponseEntity createItem( RequestCreateItemDto requestCreateItemDto){
        try{
            ResponseCreateItemDto dto = itemService.createItem(requestCreateItemDto);
            return new ResponseEntity(dto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity readItem(@PathVariable Long id){
        try{
            ResponseReadItemDto responseReadItemDto = itemService.readItem(id);
            return new ResponseEntity(responseReadItemDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity readAllItem(
            @RequestParam(value = "pageIndex") Integer pageIndex,
            @RequestParam(value = "topId", required = false, defaultValue = "0") Long topId,
            @RequestParam(value = "sizePerPage", required = false, defaultValue = "12") Integer sizePerPage){
        try{
            ResponseReadAllItemDto responseReadAllItemDto= itemService.readItemAll(pageIndex,topId,sizePerPage);
            return new ResponseEntity(responseReadAllItemDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }



    }

    @PostMapping("/update")
    public ResponseEntity updateItem( RequestUpdateItemDto requestUpdateItemDto){
        try{
            log.info("controller 실행됨 "+requestUpdateItemDto.getItemName());
            ResponseUpdateItemDto dto = itemService.updateItem(requestUpdateItemDto);
            log.info("service 실행끝남"+requestUpdateItemDto.getItemName());

            return new ResponseEntity(dto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        try{
            ResponseDeleteItemDto dto = itemService.deleteItem(id);
            return new ResponseEntity(dto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/select")
    public ResponseEntity setAuctionItemAll(@RequestBody RequestUpdateAuctionItemDto requestUpdateAuctionItemDto){
        try{
            itemService.updateAuctionItemAll(requestUpdateAuctionItemDto);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
