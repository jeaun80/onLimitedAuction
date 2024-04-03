package com.example.onlimitedauction.web.item.dto;

import com.example.onlimitedauction.global.type.bidItemType;
import com.example.onlimitedauction.global.type.itemType;
import com.example.onlimitedauction.web.item.entity.Item;
import com.example.onlimitedauction.web.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateItemDto {

    private String itemName;
    private String discription;
    private int minPrice;
    private itemType itemStatus;
    private Long memberId;
    private MultipartFile file;
    public Item toEntity(Member member,String imageName){
        Item item = Item.builder()
                .itemName(this.itemName)
                .discription(this.discription)
                .imagePath(imageName)
                .minPrice(this.minPrice)
                .itemStatus(this.itemStatus)
                .bidStatus(bidItemType.BEFORE)
                .member(member)
                .build();
        return item;
    }
}
