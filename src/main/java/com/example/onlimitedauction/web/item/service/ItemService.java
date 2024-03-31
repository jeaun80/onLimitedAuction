package com.example.onlimitedauction.web.item.service;

import com.example.onlimitedauction.web.item.dto.*;


public interface ItemService {

    ResponseCreateItemDto createItem(RequestCreateItemDto requestCreateItemDto);

    ResponseReadItemDto readItem(Long id);

    ResponseReadAllItemDto readItemAll(Integer pageIndex, Long topId, Integer sizePerPage);

    ResponseUpdateItemDto updateItem(RequestUpdateItemDto requestUpdateItemDto);

    ResponseDeleteItemDto deleteItem(Long id);


}
