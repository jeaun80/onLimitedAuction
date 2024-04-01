package com.example.onlimitedauction.web.bid.service;

import com.example.onlimitedauction.web.bid.dto.*;
import com.example.onlimitedauction.web.bid.entity.Bid;
import com.example.onlimitedauction.web.bid.repository.BidRepository;
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
    private final MemberService memberService;
    @Override
    public ResponseCreateBidDto createBid(RequestCreateBidDto requestCreateBidDto) {
        Member member = memberService.getCurrentMember(requestCreateBidDto.getMemberId());

        Bid bid = bidRepository.save(requestCreateBidDto.toEntity(member));

        return new ResponseCreateBidDto(bid.getId());
    }

    @Override
    public ResponseReadBidDto readBid(Long id) {

        Bid bid= bidRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return new ResponseReadBidDto().entityToDto(bid);
    }

    @Override
    public ResponseReadAllBidDto readAllBid(int pageIndex,int sizePerPage, Long topId) {

        Page<ResponseReadBidDto> bidPage;

        Pageable pageable = PageRequest.of(pageIndex, sizePerPage, Sort.Direction.DESC, "bid_id");

        if(topId == 0) {
            bidPage = bidRepository.findAll(pageable).map(ResponseReadBidDto::response);
        }
        else {
            bidPage = bidRepository.findAllByTopId(topId, pageable).map(ResponseReadBidDto::response);
        }

        return new ResponseReadAllBidDto(bidPage.getContent(), bidPage.getPageable(), bidPage.isLast());
    }

    @Override
    public ResponseDeleteBidDto deleteBid(Long id) {
        bidRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        bidRepository.deleteById(id);

        return new ResponseDeleteBidDto(id);
    }


}
