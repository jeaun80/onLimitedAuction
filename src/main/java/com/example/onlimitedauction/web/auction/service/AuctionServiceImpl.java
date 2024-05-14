package com.example.onlimitedauction.web.auction.service;

import com.example.onlimitedauction.global.Image.ImageService;
import com.example.onlimitedauction.web.auction.dto.*;
import com.example.onlimitedauction.web.auction.entity.Auction;
import com.example.onlimitedauction.web.auction.repository.AuctionRepository;
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

@Service
@RequiredArgsConstructor
@Log4j2
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final ImageService imageService;

    @Override
    public ResponseCreateAuctionDto createAuction(RequestCreateAuctionDto requestCreateAuctionDto) {
        Member member = memberService.getCurrentMember(requestCreateAuctionDto.getMemberId());

        Auction auction = auctionRepository.save(requestCreateAuctionDto.toEntity(member));

        return new ResponseCreateAuctionDto(auction.getId());
    }

    @Override
    public ResponseReadAuctionDto readAuction(Long id) {

        Auction auction = auctionRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        ResponseReadAuctionDto r = new ResponseReadAuctionDto(auction);
        return r;
    }

    @Override
    public ResponseReadAllAuctionDto readAllAuction(int pageIndex, int sizePerPage, Long topId) {

        Page<ResponseReadAuctionDto> AuctionPage;

        Pageable pageable = PageRequest.of(pageIndex, sizePerPage, Sort.Direction.DESC, "Auction_id");

        if(topId == 0) {
            AuctionPage = auctionRepository.findAll(pageable).map(ResponseReadAuctionDto::new);
        }
        else {
            AuctionPage = auctionRepository.findAllByTopId(topId, pageable).map(ResponseReadAuctionDto::new);
        }

        return new ResponseReadAllAuctionDto(AuctionPage.getContent(), AuctionPage.getPageable(), AuctionPage.isLast());
    }

    @Override
    public ResponseDeleteAuctionDto deleteAuction(Long id) {
        Auction auction = auctionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        for (Item item:
                auction.getItems()) {
            item.setAuction(null);
            imageService.deleteImage(item.getImagePath());
        }
        itemRepository.saveAll(auction.getItems());
        if(auction.getVideoPath().length()>0){
            imageService.deleteImage(auction.getVideoPath());
        }
        auctionRepository.deleteById(id);

        return new ResponseDeleteAuctionDto(id);
    }

    @Override
    public Auction getCurrentAuction(Long id){

        return auctionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public ResponseUploadAuctionDto VideoUpload(RequestUploadAuctionDto requestUploadAuctionDto) {
        Auction auction = getCurrentAuction(requestUploadAuctionDto.getId());
        if(auction.getVideoPath()!=null){
            imageService.deleteImage(auction.getVideoPath());
        }
        auction.setVideoPath(imageService.saveImage(requestUploadAuctionDto.getFile()));
        auctionRepository.save(auction);
        return new ResponseUploadAuctionDto(auction.getId());
    }

    @Override
    public Long updateStatus(RequestUpdateStatusAuctionDto requestDto){

        Auction auction = auctionRepository.findByStreamKey(requestDto.getStreamKey()).orElseThrow(IllegalArgumentException::new);

        auction.setAuctionStatus(requestDto.getStatus());
        return auctionRepository.save(auction).getId();
    }

    @Override
    public String getStreamKey(Long id){
        return auctionRepository.findById(id).orElseThrow(IllegalArgumentException::new).getStreamKey();
    }
}
