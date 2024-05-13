package com.example.onlimitedauction.web.member.service;

import com.example.onlimitedauction.web.member.dto.RequestLoginDto;
import com.example.onlimitedauction.web.member.dto.ResponseLoginDto;

public interface AuthService {

    ResponseLoginDto login(RequestLoginDto dto);
}
