package com.example.onlimitedauction.web.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseReadAllItemDto {

    private List<ResponseReadItemDto> itemDtoList;
    private Pageable pageable;
    private boolean isList;



}
