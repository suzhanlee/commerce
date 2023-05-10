package com.commerce.web.global.security;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

import static com.commerce.web.global.security.constant.JwtConstants.EMAIL;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_EXPIRE_TIME;

@Component
@RequiredArgsConstructor
public class JwtTokenFactory {

    @Value("${jwt.secret}")
    private String secretKey;

    public JwtTokenDto generateJwtToken(Member member) {

        // TODO: 2023-05-02 구현

        Claims claims = Jwts.claims().setSubject(member.getName());
        claims.put(EMAIL, member.getEmail());

        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return JwtTokenDto.createJwtTokenDto(token, String.valueOf(expiredDate));

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
