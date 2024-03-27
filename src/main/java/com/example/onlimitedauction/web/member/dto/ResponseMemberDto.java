package com.example.onlimitedauction.web.member.dto;

import com.example.onlimitedauction.web.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMemberDto {

    private Long id;

    private String name;

    private String email;

    public ResponseMemberDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
