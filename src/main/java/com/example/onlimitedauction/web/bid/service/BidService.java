package com.example.onlimitedauction.web.bid.service;


import com.example.onlimitedauction.web.bid.dto.*;
import com.example.onlimitedauction.web.bid.entity.Bid;


public interface BidService {

    ResponseCreateBidDto createBid(RequestCreateBidDto requestCreateBidDto);

    ResponseReadBidDto readBid(Long id);

    ResponseReadAllBidDto readAllBid(int pageIndex,int sizePerPage, Long topId);

    ResponseDeleteBidDto deleteBid(Long id);

    Bid getCurrentBid(Long id);

    ResponseUploadBidDto VideoUpload(RequestUploadBidDto requestUploadBidDto);




}
