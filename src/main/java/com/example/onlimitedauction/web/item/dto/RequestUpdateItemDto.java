package com.example.onlimitedauction.web.item.dto;

import com.example.onlimitedauction.global.type.itemType;
import com.example.onlimitedauction.web.member.entity.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUpdateItemDto {

    private Long id;

    private String itemName;

    private String discription;

    private MultipartFile file;

    private int minPrice;

    private itemType itemStatus;

}
