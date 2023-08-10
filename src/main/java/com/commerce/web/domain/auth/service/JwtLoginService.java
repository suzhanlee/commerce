package com.commerce.web.domain.auth.service;

import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_PREFIX;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class JwtLoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate<String, String> redisTemplate;

    public JwtTokenDto login(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            username, password);

        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);

    }

    public JwtTokenDto reissueToken(String refreshToken) {

        if (StringUtils.hasText(refreshToken) && refreshToken.startsWith(TOKEN_PREFIX)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
            String token = redisTemplate.opsForValue().get(authentication.getName());
            if (!refreshToken.equals(token)) {
                throw new RuntimeException("refreshToken is not equal");
            }
            return jwtTokenProvider.generateToken(authentication);

        }

        throw new RuntimeException("refreshToken syntax is incorrect");
    }
}
