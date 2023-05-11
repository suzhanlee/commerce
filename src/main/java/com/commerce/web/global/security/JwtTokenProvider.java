package com.commerce.web.global.security;

import static com.commerce.web.domain.auth.model.dto.JwtTokenDto.createJwtTokenDto;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_EXPIRE_TIME;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
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

        UserDetails userDetails = new User(claims.getSubject(), "", null);
        return new UsernamePasswordAuthenticationToken(userDetails, "", null);
    }

    public boolean validateToken(String token) {
        Claims claims = parseClaims(token);
        Date expiredDateTime = claims.getExpiration();

        if (!StringUtils.hasText(token)) {
            return false;
        }

        return !expiredDateTime.before(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }


}
