package com.example.onlimitedauction.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum auctionType {

    BEFORE("BEFORE", "시작 전"),
    PROGRESS("PROGRESS", "진행 중"),
    END("END", "완료");

    private final String code;
    private final String name;
}
