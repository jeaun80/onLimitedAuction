package com.example.onlimitedauction.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum bidItemType {

    BEFORE("BEFORE", "대기"),
    PROGRESS("PROGRESS", "경매 준비 중"),
    END("END", "경매 완료");

    private final String code;
    private final String name;
}
