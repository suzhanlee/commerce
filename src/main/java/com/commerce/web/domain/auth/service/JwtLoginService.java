package com.commerce.web.domain.auth.service;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.global.security.JwtAuthenticationToken;
import com.commerce.web.global.security.JwtTokenFactory;
import com.commerce.web.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtLoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private JwtTokenDto login(String username, String password) {

        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(username,
            password);

        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);

        jwtTokenProvider.authenticate(authentication)




    }

}
