package com.example.onlimitedauction.global.security;

import com.example.onlimitedauction.web.member.dto.CustomUserInfoDto;
import com.example.onlimitedauction.web.member.entity.Member;
import com.example.onlimitedauction.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException("해당유저가 없습니다."));

        CustomUserInfoDto dto = modelMapper.map(member, CustomUserInfoDto.class);

        return new CustomUserDetails(dto);
    }
}
