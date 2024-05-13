package com.example.onlimitedauction.global.type;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {


    COMMON("COMMON", "일반 사용자"),
    MIDDLE("MIDDLE", "중");

    private final String code;
    private final String name;
}
