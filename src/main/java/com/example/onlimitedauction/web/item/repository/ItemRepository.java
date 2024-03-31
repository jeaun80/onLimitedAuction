package com.example.onlimitedauction.web.item.repository;

import com.example.onlimitedauction.web.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query(
            value = "SELECT * FROM item AS m WHERE m.item_id <= :topId",
            countQuery = "SELECT COUNT(*) FROM item AS m WHERE m.item_id <= :topId",
            nativeQuery = true
    )
    Page<Item> findAllByTopId(
            @Param("topId") Long topId,
            Pageable pageable
    );
}
