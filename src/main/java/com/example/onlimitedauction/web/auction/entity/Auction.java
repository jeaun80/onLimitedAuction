package com.example.onlimitedauction.web.auction.entity;

import com.example.onlimitedauction.global.type.auctionType;
import com.example.onlimitedauction.web.item.entity.Item;
import com.example.onlimitedauction.web.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;



    private String title;

    private String discription;

    private auctionType auctionStatus;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime startTime;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime endTime;

    private String videoPath;

    private String streamKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "auction", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void setAuctionStatus(String status){this.auctionStatus = auctionType.valueOf(status);}
}
