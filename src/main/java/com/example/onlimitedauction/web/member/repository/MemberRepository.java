package com.example.onlimitedauction.web.member.repository;

import com.example.onlimitedauction.web.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    boolean existsByEmail(String email);
}
