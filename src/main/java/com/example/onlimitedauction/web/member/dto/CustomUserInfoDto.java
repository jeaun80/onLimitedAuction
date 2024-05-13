package com.example.onlimitedauction.web.member.dto;

import com.example.onlimitedauction.global.type.UserType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserInfoDto {
    private Long memberId;

    private String email;

    private String name;

    private String password;

    private UserType userType;
}
