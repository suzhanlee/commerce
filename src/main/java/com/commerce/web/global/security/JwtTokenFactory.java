package com.commerce.web.global.security;

import static com.commerce.web.global.security.constant.JwtConstants.EMAIL;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_EXPIRE_TIME;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class JwtTokenFactory {

    @Value("${jwt.secret}")
    private String secretKey;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final FindMemberService findMemberService;

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
        return (String)parseClaim(token).get("email");
    }

    private Claims parseClaim(String token) {

        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public JwtTokenDto authenticate(JwtTokenDto jwtTokenDto) {
        String token = jwtTokenDto.getToken();

        String username = getUsername(token);
        String email = getEmail(token);

        Member findMember = findMemberService.findByUsernameOrElseThrow(username);

        if (passwordEncoder.matches(email, findMember.getEmail())) {
            throw new RuntimeException("이메일이 일치하지 않습니다");
        }

        return jwtTokenDto;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = memberService.loadUserByUsername(getUsername(token));

        return new UsernamePasswordAuthenticationToken(userDetails, null);
    }
}
