package com.example.onlimitedauction.web.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateStatusAuctionDto {

    private String streamKey;
    private String status;
}
