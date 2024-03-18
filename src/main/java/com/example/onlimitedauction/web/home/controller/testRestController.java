package com.example.onlimitedauction.web.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testRestController {

    @GetMapping("/testrest")
    public ResponseEntity<?> getTest(){
        return ResponseEntity.ok("test sucesess");
    }

}
