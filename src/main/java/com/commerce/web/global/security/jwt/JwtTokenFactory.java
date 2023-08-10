package com.commerce.web.global.security.jwt;

import static com.commerce.web.global.security.constant.JwtConstants.CLIENT_TYPE;
import static com.commerce.web.global.security.constant.JwtConstants.EMAIL;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_EXPIRE_TIME;

import com.commerce.db.entity.member.Member;
import com.commerce.db.enums.auth.ClientType;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.global.security.MemberContext;
import com.commerce.web.global.uitil.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenFactory {

    @Value("${jwt.secret}")
    private String secretKey;
    private final RedisTemplate<Map<String, Object>, String> redisTemplate;

    public JwtTokenDto generateJwtToken(Member member) {
        Date now = DateUtils.now();
        Date expiredDate = DateUtils.addTime(now, TOKEN_EXPIRE_TIME);
        String token = Jwts.builder()
            .setClaims(createClaims(member))
            .setIssuedAt(now)
            .setExpiration(expiredDate)
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();

        LocalDateTime expiredDateTime = LocalDateTime.ofInstant(expiredDate.toInstant(),
            ZoneId.systemDefault());
        String refreshToken = generateRefreshToken(member);

        return JwtTokenDto.createJwtTokenDto(token, refreshToken, expiredDateTime);

    }

    private String generateRefreshToken(Member member) {

        Date now = DateUtils.now();
        Date expiredDate = DateUtils.addTime(now, TOKEN_EXPIRE_TIME);
        String token = Jwts.builder()
            .setClaims(createClaims(member))
            .setIssuedAt(now)
            .setExpiration(expiredDate)
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
        long timeout = 60;
        // redis에 저장
        redisTemplate.opsForValue().set(
            createClaims(member),
            token,
            timeout,
            TimeUnit.MILLISECONDS
        );

        return token;
    }

    private Map<String, Object> createClaims(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(EMAIL, member.getEmail());
        claims.put(CLIENT_TYPE, member.getClientType());
        return claims;
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        String email = claims.get(EMAIL, String.class);
        ClientType clientType = ClientType.valueOf(claims.get(CLIENT_TYPE, String.class));
        MemberContext memberContext = MemberContext.create(email, clientType);
        return new UsernamePasswordAuthenticationToken(memberContext, null, null);
    }
}
