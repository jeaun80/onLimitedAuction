package com.example.onlimitedauction.web.auction.dto;

import com.example.onlimitedauction.global.common.KeyGenerator;
import com.example.onlimitedauction.global.type.auctionType;
import com.example.onlimitedauction.web.auction.entity.Auction;
import com.example.onlimitedauction.web.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateAuctionDto {


    private String title;

    private String discription;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime startTime;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime endTime;

    private Long memberId;

    public Auction toEntity(Member member){

        return Auction.builder()
                .title(title)
                .discription(discription)
                .bidStatus(auctionType.BEFORE)
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .streamKey(KeyGenerator.streamKeyGenerator())
                .build();
    }
}
