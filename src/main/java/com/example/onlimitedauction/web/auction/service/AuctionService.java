package com.example.onlimitedauction.web.auction.service;


import com.example.onlimitedauction.web.auction.dto.*;
import com.example.onlimitedauction.web.auction.entity.Auction;


public interface AuctionService {

    ResponseCreateAuctionDto createAuction(RequestCreateAuctionDto requestCreateAuctionDto);

    ResponseReadAuctionDto readAuction(Long id);

    ResponseReadAllAuctionDto readAllAuction(int pageIndex, int sizePerPage, Long topId);

    ResponseDeleteAuctionDto deleteAuction(Long id);

    Auction getCurrentAuction(Long id);

    ResponseUploadAuctionDto VideoUpload(RequestUploadAuctionDto requestUploadAuctionDto);

    Long updateStatus(RequestUpdateStatusAuctionDto requestDto);

    String getStreamKey(Long id);


}
