package com.example.onlimitedauction.web.member.service;

import com.example.onlimitedauction.web.member.dto.RequestUpdateMemberDto;
import com.example.onlimitedauction.web.member.dto.RequestCreateMemberDto;
import com.example.onlimitedauction.web.member.dto.ResponseMemberDto;
import com.example.onlimitedauction.web.member.entity.Member;
import com.example.onlimitedauction.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void create(RequestCreateMemberDto memberDto) {
        if(memberRepository.existsByEmail(memberDto.getEmail())){
            throw new RuntimeException();
        }

        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(toEntity(memberDto));
    }

    @Override
    public ResponseMemberDto read(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("데이터가 없습니다."));

        return new ResponseMemberDto(findMember);
    }

    @Override
    public void update(RequestUpdateMemberDto memberDto) {
        Member findMember = memberRepository.findById(memberDto.getId()).orElseThrow(() ->new IllegalArgumentException("데이터가 없습니다."));

        findMember.update(memberDto);

        memberRepository.save(findMember);
    }

    @Override
    public void delete(Long id) {
        memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("데이터가 없습니다."));

        memberRepository.deleteById(id);
    }

    @Override
    public Member getCurrentMember(Long id) {
        return memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
    }
}
