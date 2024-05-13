package com.example.onlimitedauction.global.security;

import com.example.onlimitedauction.web.member.dto.CustomUserInfoDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.security.Key;
import java.sql.Date;
import java.time.ZonedDateTime;

@Log4j2
@Component
public class JwtUtil {

    private final Key key;
    private final Long accessTokenExpTime;

    public JwtUtil(@Value("${jwt.secret}") String key, @Value("${jwt.expiration_time}") Long accessTokenExpTime){

        this.accessTokenExpTime =accessTokenExpTime;
        byte[]bytes = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(bytes);
    }
    public String createAccessToken(CustomUserInfoDto member){
        return createToken(member,accessTokenExpTime);
    }

    private String createToken(CustomUserInfoDto member, long expireTime){

        Claims claims = Jwts.claims();
        claims.put("memberId",member.getMemberId());
        claims.put("email",member.getEmail());
        claims.put("role",member.getUserType());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public Long getUserId(String token){
        return parseClaims(token).get("memberId",Long.class);

    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("invalid Jwt token",e);
        }
        catch (ExpiredJwtException e){
            log.info("Expired JWT Token",e);
        }
        catch (UnsupportedJwtException e){
            log.info("Unsupported JWT Token",e);
        }
        catch (IllegalArgumentException e){
            log.info("JWT cliams string is empty",e);
        }
        return false;
    }

    public Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }
        catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }


}
