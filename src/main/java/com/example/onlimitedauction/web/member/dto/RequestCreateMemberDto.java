package com.example.onlimitedauction.web.member.dto;

import com.example.onlimitedauction.global.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateMemberDto {

    private String name;

    private String email;

    private String password;

    private UserType userType;
}
