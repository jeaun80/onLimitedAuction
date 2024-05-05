package com.example.onlimitedauction.web.bid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateStatusBidDto {

    private String streamKey;
    private String status;
}
