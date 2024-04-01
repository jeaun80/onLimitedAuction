package com.example.onlimitedauction.web.bid.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReadAllBidDto {

    List<ResponseReadBidDto> bidDtoList;
    private Pageable pageable;
    private boolean isList;

}
