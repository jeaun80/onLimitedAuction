package com.example.onlimitedauction.web.item.dto;

import com.example.onlimitedauction.global.type.itemType;
import com.example.onlimitedauction.web.item.entity.Item;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseReadItemDto(Item item){
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.discription = item.getDiscription();
        this.minPrice = item.getMinPrice();
        this.itemStatus = item.getItemStatus();
        this.memberId = item.getMember().getId();
        this.imagePath = "http://localhost:8080"+item.getImagePath();
    }

    public static ResponseReadItemDto response(Item item){

        return ResponseReadItemDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .discription(item.getDiscription())
                .itemStatus(item.getItemStatus())
                .minPrice(item.getMinPrice())
                .memberId(item.getMember().getId())
                .imagePath("http://localhost:8080"+item.getImagePath())
                .build();


    }
}
