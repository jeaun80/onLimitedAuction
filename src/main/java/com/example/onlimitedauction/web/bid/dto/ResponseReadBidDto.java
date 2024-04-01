package com.example.onlimitedauction.web.bid.dto;

import com.example.onlimitedauction.global.type.bidType;
import com.example.onlimitedauction.web.bid.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseReadBidDto {

    private Long id;

    private String title;

    private String discription;

    private bidType bidStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long memberid;

    public ResponseReadBidDto entityToDto(Bid bid){
        return ResponseReadBidDto.builder()
                .id(bid.getId())
                .title(bid.getTitle())
                .discription(bid.getDiscription())
                .bidStatus(bid.getBidStatus())
                .startTime(bid.getStartTime())
                .endTime(bid.getEndTime())
                .memberid(bid.getMember().getId())
                .build();
    }

    public static ResponseReadBidDto response(Bid bid){
        return ResponseReadBidDto.builder()
                .id(bid.getId())
                .title(bid.getTitle())
                .discription(bid.getDiscription())
                .bidStatus(bid.getBidStatus())
                .startTime(bid.getStartTime())
                .endTime(bid.getEndTime())
                .memberid(bid.getMember().getId())
                .build();
    }
}
