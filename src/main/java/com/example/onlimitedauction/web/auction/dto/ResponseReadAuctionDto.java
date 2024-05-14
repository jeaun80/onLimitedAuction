package com.example.onlimitedauction.web.auction.dto;

import com.example.onlimitedauction.global.type.auctionType;
import com.example.onlimitedauction.web.auction.entity.Auction;
import com.example.onlimitedauction.web.item.dto.ResponseReadItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseReadAuctionDto {

    private Long id;

    private String title;

    private String discription;

    private auctionType bidStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long memberid;

    private String videoFile;

    private String streamKey;

    private List<ResponseReadItemDto> itemList;

    public ResponseReadAuctionDto(Auction auction){
        this.id = auction.getId();
        this.title = auction.getTitle();
        this.discription = auction.getDiscription();
        this.bidStatus = auction.getBidStatus();
        this.startTime = auction.getStartTime();
        this.endTime = auction.getEndTime();
        this.memberid = auction.getMember().getId();
        this.itemList = auction.getItems().stream().map(ResponseReadItemDto::new).toList();
        this.streamKey = auction.getStreamKey();
        if(auction.getVideoPath()!=null){
            this.videoFile = "http://localhost:8083"+ auction.getVideoPath();
        }
    }

}
