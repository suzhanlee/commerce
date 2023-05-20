package com.commerce.web.domain.auth.service;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtLoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public JwtTokenDto login(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            username, password);

        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);

    }
}
