package com.example.onlimitedauction.web.item.entity;

import com.example.onlimitedauction.global.type.bidItemType;
import com.example.onlimitedauction.global.type.bidType;
import com.example.onlimitedauction.global.type.itemType;
import com.example.onlimitedauction.web.bid.entity.Bid;
import com.example.onlimitedauction.web.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    private String discription;

    private String imagePath;

    private int minPrice;

    private itemType itemStatus;

    private bidItemType bidStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_id")
    private Bid bid;
}
