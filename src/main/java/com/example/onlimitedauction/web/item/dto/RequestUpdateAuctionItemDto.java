package com.example.onlimitedauction.web.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateAuctionItemDto {


    private List<Long> auctionList = new LinkedList<>();

    private Long auctionId;
}
