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

    private String videoFile;

    private String streamKey;

    private List<ResponseReadItemDto> itemList;

    public ResponseReadBidDto(Bid bid){
        this.id = bid.getId();
        this.title = bid.getTitle();
        this.discription = bid.getDiscription();
        this.bidStatus = bid.getBidStatus();
        this.startTime = bid.getStartTime();
        this.endTime = bid.getEndTime();
        this.memberid = bid.getMember().getId();
        this.itemList = bid.getItems().stream().map(ResponseReadItemDto::new).toList();
        this.streamKey = bid.getStreamKey();
        if(bid.getVideoPath()!=null){
            this.videoFile = "http://localhost:8083"+bid.getVideoPath();
        }
    }

}
