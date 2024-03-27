package com.example.onlimitedauction.web.member.dto;


import com.example.onlimitedauction.web.member.entity.Member;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateMemberDto {

    private Long id;

    private String name;

    private String email;

    private String password;

}
