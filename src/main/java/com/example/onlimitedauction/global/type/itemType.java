package com.example.onlimitedauction.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum itemType {

    UPPER("UPPER", "상"),
    MIDDLE("MIDDLE", "중"),
    LOWER("LOWER", "하");

    private final String code;
    private final String name;
}
