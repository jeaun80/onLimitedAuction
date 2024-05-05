package com.example.onlimitedauction.web.bid.repository;

import com.example.onlimitedauction.web.bid.entity.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {

    @Query(
            value = "SELECT * FROM bid AS m WHERE m.bid_id <= :topId",
            countQuery = "SELECT COUNT(*) FROM bid AS m WHERE m.bid_id <= :topId",
            nativeQuery = true
    )
    Page<Bid> findAllByTopId(
            @Param("topId") Long topId,
            Pageable pageable
    );
    Optional<Bid> findByStreamKey(String streamKey);

}
