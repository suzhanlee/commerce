package com.commerce.web.domain.auth.service;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.auth.model.rq.LoginOauth2Rq;
import com.commerce.web.domain.auth.model.rs.CodeRs;
import org.springframework.stereotype.Service;

@Service
public class Oauth2LoginService {

    public String loginOauth2(LoginOauth2Rq rq) {

        String kakaoUrl = "https://kauth.kakao.com/oauth/token?"
            + "client_id=" + rq.getClientId()
            + "&redirect_uri=" + rq.getRedirectUrl()
            + "&code=" + rq.getCode()
            + "&grant_type=authorization_code";

        return kakaoUrl;
    }

    public CodeRs loginOauth2code(String code) {

        return CodeRs.createCodeRs(code);

    }
//
//    public void getUserInfo(JwtTokenDto token) {
//
//
//
//
//
//
//
//
//
//    }


}
