package com.example.onlimitedauction.global.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestSendBidDto {

    private Long id;
    private int price;
}
