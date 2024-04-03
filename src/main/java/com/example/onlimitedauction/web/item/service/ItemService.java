package com.example.onlimitedauction.web.item.service;

import com.example.onlimitedauction.web.item.dto.*;
import com.example.onlimitedauction.web.item.entity.Item;

import java.util.List;


public interface ItemService {

    ResponseCreateItemDto createItem(RequestCreateItemDto requestCreateItemDto);

    ResponseReadItemDto readItem(Long id);

    ResponseReadAllItemDto readItemAll(Integer pageIndex, Long topId, Integer sizePerPage);

    ResponseUpdateItemDto updateItem(RequestUpdateItemDto requestUpdateItemDto);

    ResponseDeleteItemDto deleteItem(Long id);

    void updateBidItemAll(RequestUpdateBidItemDto requestUpdateBidItemDto);

}
