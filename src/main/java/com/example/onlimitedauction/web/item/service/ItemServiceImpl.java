package com.example.onlimitedauction.web.item.service;


import com.example.onlimitedauction.global.Image.ImageService;
import com.example.onlimitedauction.web.item.dto.*;
import com.example.onlimitedauction.web.item.entity.Item;
import com.example.onlimitedauction.web.item.repository.ItemRepository;
import com.example.onlimitedauction.web.member.entity.Member;
import com.example.onlimitedauction.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Log4j2
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {


    private final MemberService memberService;
    private final ItemRepository itemRepository;
    private final ImageService imageService;

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

        Resource file = imageService.readImage(item.getImagePath());

        return new ResponseReadItemDto(item,file);
    }

    @Override
    public ResponseReadAllItemDto readItemAll(Integer pageIndex, Long topId, Integer sizePerPage) {
        Page<ResponseReadItemDto> itemPage;

        Pageable pageable = PageRequest.of(pageIndex, sizePerPage, Sort.Direction.DESC, "id");

        if(topId == 0) {
            itemPage = itemRepository.findAll(pageable).map(ResponseReadItemDto::response);
        }
        else {
            itemPage = itemRepository.findAllByTopId(topId, pageable).map(ResponseReadItemDto::response);
        }
        for(ResponseReadItemDto item: itemPage){
            item.setImageFiles(imageService.readImage(item.getImagePath()));
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

        item.setItemName(imageService.updateImage(requestUpdateItemDto.getImagePath(), requestUpdateItemDto.getFile()));

        return new ResponseUpdateItemDto(item.getId());
    }

    @Override
    public ResponseDeleteItemDto deleteItem(Long id) {
        itemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());

        itemRepository.deleteById(id);

        return new ResponseDeleteItemDto(id);
    }
}
