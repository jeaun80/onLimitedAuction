package com.example.onlimitedauction.web.item.dto;

import com.example.onlimitedauction.global.type.itemType;
import com.example.onlimitedauction.web.item.entity.Item;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;

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

    private Resource imageFiles;

    private int minPrice;

    private itemType itemStatus;

    private String imagePath;

    private Long memberId;

    public ResponseReadItemDto(Item item,Resource file){
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.discription = item.getDiscription();
        this.imageFiles =file;
        this.minPrice = item.getMinPrice();
        this.itemStatus = item.getItemStatus();
        this.memberId = item.getMember().getId();
        this.imagePath = item.getImagePath();
    }

    public static ResponseReadItemDto response(Item item){

        return ResponseReadItemDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .discription(item.getDiscription())
                .itemStatus(item.getItemStatus())
                .minPrice(item.getMinPrice())
                .memberId(item.getMember().getId())
                .build();


    }
}
