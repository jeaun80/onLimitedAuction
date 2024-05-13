package com.example.onlimitedauction.web.member.service;

import com.example.onlimitedauction.global.type.UserType;
import com.example.onlimitedauction.web.member.dto.RequestUpdateMemberDto;
import com.example.onlimitedauction.web.member.dto.RequestCreateMemberDto;
import com.example.onlimitedauction.web.member.dto.ResponseMemberDto;
import com.example.onlimitedauction.web.member.entity.Member;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface MemberService {

    //create
    void create(RequestCreateMemberDto memberDto);

    //read
    ResponseMemberDto read(Long id);

    //update
    void update(RequestUpdateMemberDto memberDto);

    //delete
    void delete(Long id);

    Member getCurrentMember(Long id);

    default Member toEntity(RequestCreateMemberDto memberDto){
        return Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .userType(UserType.COMMON)
                .build();
    }
}
