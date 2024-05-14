package com.example.onlimitedauction.web.item.service;


import com.example.onlimitedauction.global.Image.ImageService;
import com.example.onlimitedauction.global.type.auctionItemType;
import com.example.onlimitedauction.web.auction.entity.Auction;
import com.example.onlimitedauction.web.auction.service.AuctionService;
import com.example.onlimitedauction.web.item.dto.*;
import com.example.onlimitedauction.web.item.entity.Item;
import com.example.onlimitedauction.web.item.repository.ItemRepository;
import com.example.onlimitedauction.web.member.entity.Member;
import com.example.onlimitedauction.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {


    private final MemberService memberService;
    private final ItemRepository itemRepository;
    private final ImageService imageService;
    private final AuctionService auctionService;

    @Override
    public ResponseCreateItemDto createItem(RequestCreateItemDto requestCreateItemDto) {
        Member member = memberService.getCurrentMember(requestCreateItemDto.getMemberId());

        String filePath = imageService.saveImage(requestCreateItemDto.getFile());

        Item item = requestCreateItemDto.toEntity(member,filePath);

        return new ResponseCreateItemDto(itemRepository.save(item).getId());
    }

    @Override
    public ResponseReadItemDto readItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException());

        return new ResponseReadItemDto(item);
    }

    @Override
    public ResponseReadAllItemDto readItemAll(Integer pageIndex, Long topId, Integer sizePerPage) {
        Page<ResponseReadItemDto> itemPage;

        Pageable pageable = PageRequest.of(pageIndex, sizePerPage, Sort.Direction.DESC, "item_id");

        if(topId == 0) {
            itemPage = itemRepository.findAll(pageable).map(ResponseReadItemDto::new);
        }
        else {
            itemPage = itemRepository.findAllByTopId(topId, pageable).map(ResponseReadItemDto::new);
        }

        return new ResponseReadAllItemDto(itemPage.getContent(), itemPage.getPageable(), itemPage.isLast());
    }

    @Override
    public ResponseUpdateItemDto updateItem(RequestUpdateItemDto requestUpdateItemDto) {
        Item item = itemRepository.findById(requestUpdateItemDto.getId()).orElseThrow(() -> new IllegalArgumentException());

        item.setItemName(requestUpdateItemDto.getItemName());
        item.setItemStatus(requestUpdateItemDto.getItemStatus());
        item.setDiscription(requestUpdateItemDto.getDiscription());
        item.setMinPrice(requestUpdateItemDto.getMinPrice());

        item.setImagePath(imageService.updateImage(item.getImagePath(), requestUpdateItemDto.getFile()));
        itemRepository.save(item);

        return new ResponseUpdateItemDto(item.getId());
    }

    @Override
    public ResponseDeleteItemDto deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());

        itemRepository.deleteById(id);
        imageService.deleteImage(item.getImagePath());

        return new ResponseDeleteItemDto(id);
    }

    @Override
    public void updateAuctionItemAll(RequestUpdateAuctionItemDto requestUpdateauctionItemDto) {
        Auction auction = auctionService.getCurrentAuction(requestUpdateauctionItemDto.getAuctionId());

        List<Item> itemList = itemRepository.findAllById(requestUpdateauctionItemDto.getAuctionList());

        itemRepository.saveAll(setauctionAll(itemList, auction));
    }

    private List<Item> setauctionAll(List<Item> itemList, Auction auction){
        for (Item item:
                itemList) {
            item.setAuction(auction);
            item.setAuctionStatus(auctionItemType.PROGRESS);
        }
        return itemList;
    }
}
