package com.example.onlimitedauction.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

//        String refreshHeader = request.getHeader("refresh");
//        && authorizationHeader.startsWith("Bearer ")
        //jwt가 헤더에 있는 경우
        if(authorizationHeader != null){
//            String token = authorizationHeader.substring(7);
            String token = authorizationHeader;

            if(jwtUtil.validateToken(token)){
                Long userId = jwtUtil.getUserId(token);

                UserDetails userDetails = customUserDetailService.loadUserByUsername(String.valueOf(userId));

                if(userDetails!=null){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
