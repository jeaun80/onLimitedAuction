package com.example.onlimitedauction.web.item.dto;

import com.example.onlimitedauction.global.type.itemType;
import com.example.onlimitedauction.web.item.entity.Item;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ResponseReadItemDto {



    private Long id;

    private String itemName;

    private String discription;

    private int minPrice;

    private itemType itemStatus;

    private String imagePath;

    private Long memberId;

    private Long bidId;

    public ResponseReadItemDto(Item item){
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.discription = item.getDiscription();
        this.minPrice = item.getMinPrice();
        this.itemStatus = item.getItemStatus();
        this.memberId = item.getMember().getId();
        this.imagePath = "http://localhost:8083"+item.getImagePath();
        if(item.getAuction()!=null){
            this.bidId = item.getAuction().getId();
        }
    }

}
