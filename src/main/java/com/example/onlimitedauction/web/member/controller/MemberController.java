package com.example.onlimitedauction.web.member.controller;


import com.example.onlimitedauction.web.member.dto.RequestUpdateMemberDto;
import com.example.onlimitedauction.web.member.dto.RequestCreateMemberDto;
import com.example.onlimitedauction.web.member.dto.ResponseMemberDto;
import com.example.onlimitedauction.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/")
    public ResponseEntity saveMember(@RequestBody RequestCreateMemberDto memberDto){
        try {
            memberService.create(memberDto);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity readMember(@PathVariable Long id){
        try{
            ResponseMemberDto member = memberService.read(id);
            return new ResponseEntity(member,HttpStatus.OK);
        }catch(Exception e){
            log.info(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/")
    public ResponseEntity updateMember(@RequestBody RequestUpdateMemberDto memberDto){
        try{
            memberService.update(memberDto);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id){
        try{
            memberService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
