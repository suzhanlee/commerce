package com.commerce.web.global.security;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.global.uitil.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.commerce.web.global.security.constant.JwtConstants.CLIENT_TYPE;
import static com.commerce.web.global.security.constant.JwtConstants.EMAIL;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_EXPIRE_TIME;

@Component
@RequiredArgsConstructor
public class JwtTokenFactory {

    @Value("${jwt.secret}")
    private String secretKey;

    public JwtTokenDto generateJwtToken(Member member) {
        Date now = DateUtils.now();
        Date expiredDate = DateUtils.addTime(now, TOKEN_EXPIRE_TIME);
        String token = Jwts.builder()
                .setClaims(createClaims(member))
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        LocalDateTime expiredDateTime = LocalDateTime.ofInstant(expiredDate.toInstant(), ZoneId.systemDefault());
        return JwtTokenDto.createJwtTokenDto(token, expiredDateTime);

    }

    private Map<String, Object> createClaims(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(EMAIL, member.getEmail());
        claims.put(CLIENT_TYPE, member.getClientType());
        return claims;
    }

    public boolean validateToken(String token) {
        Claims claims = parseClaim(token);
        Date expiredDateTime = claims.getExpiration();

        if (!StringUtils.hasText(token)) {
            return false;
        }

        return !expiredDateTime.before(new Date());
    }

    public String getUsername(String token) {
        return parseClaim(token).getSubject();
    }

    public String getEmail(String token) {
        return (String) parseClaim(token).get("email");
    }

    private Claims parseClaim(String token) {

        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Authentication getAuthentication(String token) {
        return null;
    }
}
