package com.example.onlimitedauction.web.auction.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReadAllAuctionDto {

    List<ResponseReadAuctionDto> bidDtoList;
    private Pageable pageable;
    private boolean isList;

}
