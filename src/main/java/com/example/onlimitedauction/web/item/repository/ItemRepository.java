package com.example.onlimitedauction.web.item.repository;

import com.example.onlimitedauction.web.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Page<Item> findAllByTopId(
            @Param("topId") Long topId,
            Pageable pageable
    );
}
