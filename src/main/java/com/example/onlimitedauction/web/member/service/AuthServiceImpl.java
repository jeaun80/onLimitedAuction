package com.example.onlimitedauction.web.member.service;

import com.example.onlimitedauction.global.security.JwtUtil;
import com.example.onlimitedauction.web.member.dto.CustomUserInfoDto;
import com.example.onlimitedauction.web.member.dto.RequestLoginDto;
import com.example.onlimitedauction.web.member.dto.ResponseLoginDto;
import com.example.onlimitedauction.web.member.entity.Member;
import com.example.onlimitedauction.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseLoginDto login(RequestLoginDto dto){
        String email = dto.getEmail();
        String password = dto.getPassword();
        Member member = memberRepository.findMemberByEmail(email);
        if(member == null){
            throw new UsernameNotFoundException("이메일 존재하지 않습니다.");
        }

        if(!passwordEncoder.matches(password,member.getPassword())){
            log.info("비밀번호 일치 오류");
            throw new BadCredentialsException("비밀번호가 일치하지 안습니다.");
        }

        CustomUserInfoDto info = modelMapper.map(member,CustomUserInfoDto.class);

        String accessToken = jwtUtil.createAccessToken(info);
        ResponseLoginDto response = new ResponseLoginDto(accessToken);
        return response;
    }

}
