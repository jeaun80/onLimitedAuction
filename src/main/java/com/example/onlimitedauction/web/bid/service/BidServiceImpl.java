package com.example.onlimitedauction.web.bid.service;

import com.example.onlimitedauction.global.Image.ImageService;
import com.example.onlimitedauction.web.bid.dto.*;
import com.example.onlimitedauction.web.bid.entity.Bid;
import com.example.onlimitedauction.web.bid.repository.BidRepository;
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
public class BidServiceImpl implements BidService{

    private final BidRepository bidRepository;
    private final ItemRepository itemRepository;
    private final MemberService memberService;
    private final ImageService imageService;

    @Override
    public ResponseCreateBidDto createBid(RequestCreateBidDto requestCreateBidDto) {
        Member member = memberService.getCurrentMember(requestCreateBidDto.getMemberId());

        Bid bid = bidRepository.save(requestCreateBidDto.toEntity(member));

        return new ResponseCreateBidDto(bid.getId());
    }

    @Override
    public ResponseReadBidDto readBid(Long id) {

        Bid bid= bidRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        ResponseReadBidDto r = new ResponseReadBidDto(bid);
        return r;
    }

    @Override
    public ResponseReadAllBidDto readAllBid(int pageIndex,int sizePerPage, Long topId) {

        Page<ResponseReadBidDto> bidPage;

        Pageable pageable = PageRequest.of(pageIndex, sizePerPage, Sort.Direction.DESC, "bid_id");

        if(topId == 0) {
            bidPage = bidRepository.findAll(pageable).map(ResponseReadBidDto::new);
        }
        else {
            bidPage = bidRepository.findAllByTopId(topId, pageable).map(ResponseReadBidDto::new);
        }

        return new ResponseReadAllBidDto(bidPage.getContent(), bidPage.getPageable(), bidPage.isLast());
    }

    @Override
    public ResponseDeleteBidDto deleteBid(Long id) {
        Bid bid = bidRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        for (Item item:
                bid.getItems()) {
            item.setBid(null);
            imageService.deleteImage(item.getImagePath());
        }
        itemRepository.saveAll(bid.getItems());
        if(bid.getVideoPath().length()>0){
            imageService.deleteImage(bid.getVideoPath());
        }
        bidRepository.deleteById(id);

        return new ResponseDeleteBidDto(id);
    }

    @Override
    public Bid getCurrentBid(Long id){

        return bidRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public ResponseUploadBidDto VideoUpload(RequestUploadBidDto requestUploadBidDto) {
        Bid bid = getCurrentBid(requestUploadBidDto.getId());
        if(bid.getVideoPath()!=null){
            imageService.deleteImage(bid.getVideoPath());
        }
        bid.setVideoPath(imageService.saveImage(requestUploadBidDto.getFile()));
        bidRepository.save(bid);
        return new ResponseUploadBidDto(bid.getId());
    }

    @Override
    public Long updateStatus(RequestUpdateStatusBidDto requestDto){

        Bid bid = bidRepository.findByStreamKey(requestDto.getStreamKey()).orElseThrow(IllegalArgumentException::new);

        bid.setBidStatus(requestDto.getStatus());
        return bidRepository.save(bid).getId();
    }

    @Override
    public String getStreamKey(Long id){
        return bidRepository.findById(id).orElseThrow(IllegalArgumentException::new).getStreamKey();
    }
}
