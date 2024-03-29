package com.example.onlimitedauction.web.member.service;

import com.example.onlimitedauction.web.member.dto.RequestUpdateMemberDto;
import com.example.onlimitedauction.web.member.dto.RequestCreateMemberDto;
import com.example.onlimitedauction.web.member.dto.ResponseMemberDto;
import com.example.onlimitedauction.web.member.entity.Member;

public interface MemberService {

    //create
    void create(RequestCreateMemberDto memberDto);

    //read
    ResponseMemberDto read(Long id);

    //update
    void update(RequestUpdateMemberDto memberDto);

    //delete
    void delete(Long id);

    default Member toEntity(RequestCreateMemberDto memberDto){
        return Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .build();
    }
}
