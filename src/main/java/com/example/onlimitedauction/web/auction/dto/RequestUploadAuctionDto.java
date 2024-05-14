package com.example.onlimitedauction.web.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUploadAuctionDto {

    private Long id;
    private MultipartFile file;

}
