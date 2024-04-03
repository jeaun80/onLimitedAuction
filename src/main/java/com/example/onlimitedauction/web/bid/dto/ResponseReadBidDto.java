package com.example.onlimitedauction.web.bid.dto;

import com.example.onlimitedauction.global.type.bidType;
import com.example.onlimitedauction.web.bid.entity.Bid;
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
public class ResponseReadBidDto {

    private Long id;

    private String title;

    private String discription;

    private bidType bidStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long memberid;

    private List<ResponseReadItemDto> itemList;

    public ResponseReadBidDto(Bid bid){
        ResponseReadBidDto.builder()
                .id(bid.getId())
                .title(bid.getTitle())
                .discription(bid.getDiscription())
                .bidStatus(bid.getBidStatus())
                .startTime(bid.getStartTime())
                .endTime(bid.getEndTime())
                .memberid(bid.getMember().getId())
                .itemList(bid.getItems().stream().map(ResponseReadItemDto::new).toList())
                .build();
    }

}
