package com.example.onlimitedauction.web.item.controller;


import com.example.onlimitedauction.web.item.dto.*;
import com.example.onlimitedauction.web.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping()
    public ResponseEntity createItem(@RequestBody RequestCreateItemDto requestCreateItemDto){
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

    @PatchMapping()
    public ResponseEntity updateItem(@RequestBody RequestUpdateItemDto requestUpdateItemDto){
        try{
            ResponseUpdateItemDto dto = itemService.updateItem(requestUpdateItemDto);
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

}
