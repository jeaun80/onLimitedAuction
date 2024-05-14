package com.example.onlimitedauction.web.auction.repository;

import com.example.onlimitedauction.web.auction.entity.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {

    @Query(
            value = "SELECT * FROM bid AS m WHERE m.bid_id <= :topId",
            countQuery = "SELECT COUNT(*) FROM bid AS m WHERE m.bid_id <= :topId",
            nativeQuery = true
    )
    Page<Auction> findAllByTopId(
            @Param("topId") Long topId,
            Pageable pageable
    );
    Optional<Auction> findByStreamKey(String streamKey);

}
