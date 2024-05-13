package com.example.onlimitedauction.web.member.controller;

import com.example.onlimitedauction.web.member.dto.RequestLoginDto;
import com.example.onlimitedauction.web.member.dto.ResponseLoginDto;
import com.example.onlimitedauction.web.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity getMemberProfile(@RequestBody RequestLoginDto dto){
        try{
            ResponseLoginDto token = authService.login(dto);
            return new ResponseEntity(token, HttpStatus.OK);
        }catch (Exception e){
            log.info("login실패");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
