package com.commerce.web.global.security;

import static com.commerce.web.global.security.constant.JwtConstants.EMAIL;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_EXPIRE_TIME;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtTokenFactory {

    //    @Value()
    private String secretKey = "Z2l0QGdpdGh1Yi5jb206c3V6aGFubGVlL2NvbW1lcmNlLmdpdC1jb21tZXJjZS1wcm9qZWN0LXNlY3JldC1rZXk=";


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

        return JwtTokenDto.createJwtTokenDto(token, expiredDate);

    }

    public boolean validateToken(JwtTokenDto jwtTokenDto) {
        Date expiredDateTime = jwtTokenDto.getExpiredDateTime();

        if (!StringUtils.hasText(jwtTokenDto.getToken())) {
            return false;
        }

        return !expiredDateTime.before(new Date());
    }

    public String getUsername(String token) {
        return parseClaim(token).getSubject();
    }

    public String getEmail(String token) {
        return (String)parseClaim(token).get("email");
    }

    private Claims parseClaim(String token) {

        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
