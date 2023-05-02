package com.commerce.web.domain.auth.service;

import com.commerce.db.enums.auth.ClientType;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.auth.model.rq.LoginOauth2Rq;
import com.commerce.web.global.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.commerce.db.enums.auth.ClientType.GOOGLE;
import static com.commerce.db.enums.auth.ClientType.KAKAO;

@Service
@RequiredArgsConstructor
public class Oauth2LoginService {

    private final Oauth2KakaoService oauth2KakaoService;
    private final Oauth2GoogleService oauth2GoogleService;

    public JwtTokenDto loginOauth2(LoginOauth2Rq rq) {

        ClientType clientType = rq.getClientType();
        String code = rq.getCode();

        if (KAKAO.equals(clientType)) {
            return oauth2KakaoService.getToken(code);
        }

        if (GOOGLE.equals(clientType)) {
            return oauth2GoogleService.getToken(code);
        }

        throw new AuthenticationException();
    }
}
