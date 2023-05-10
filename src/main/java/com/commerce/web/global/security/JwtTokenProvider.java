package com.commerce.web.global.security;

import static com.commerce.web.domain.auth.model.dto.JwtTokenDto.createJwtTokenDto;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_EXPIRE_TIME;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public JwtTokenDto generateToken(Authentication authentication) {

        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        String token = Jwts.builder()
            .setSubject(authentication.getName())
            .setExpiration(expiredDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

        return createJwtTokenDto(token, String.valueOf(expiredDate));
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails userDetails = new User(claims.getSubject(), "", null);
        return new JwtAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }




}
