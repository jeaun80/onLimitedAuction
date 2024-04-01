package com.example.onlimitedauction.web.bid.dto;

import com.example.onlimitedauction.global.type.bidType;
import com.example.onlimitedauction.web.bid.entity.Bid;
import com.example.onlimitedauction.web.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateBidDto {


    private String title;

    private String discription;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime startTime;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime endTime;

    private Long memberId;

    public Bid toEntity(Member member){

        return Bid.builder()
                .title(title)
                .discription(discription)
                .bidStatus(bidType.BEFORE)
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .build();
    }
}
