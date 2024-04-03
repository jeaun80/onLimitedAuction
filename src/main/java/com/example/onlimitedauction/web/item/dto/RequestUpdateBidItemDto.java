package com.example.onlimitedauction.web.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateBidItemDto {


    private List<Long> bidList = new LinkedList<>();

    private Long bidId;
}
