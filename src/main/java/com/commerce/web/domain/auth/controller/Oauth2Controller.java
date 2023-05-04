package com.commerce.web.domain.auth.controller;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.auth.model.rq.LoginOauth2Rq;
import com.commerce.web.domain.auth.model.rq.SignUpRq;
import com.commerce.web.domain.auth.service.Oauth2LoginService;
import com.commerce.web.domain.member.service.MemberService;
import com.commerce.web.global.path.ApiPath;
import com.commerce.web.global.security.JwtTokenFactory;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Google 및 카카오 로그인
@RestController
@RequiredArgsConstructor
public class Oauth2Controller {

    private final Oauth2LoginService oauth2LoginService;
    private final MemberService memberService;
    private final JwtTokenFactory jwtTokenFactory;

    @PostMapping(ApiPath.LOGIN_OAUTH2)
    public JwtTokenDto loginOauth2(@Validated @RequestBody LoginOauth2Rq rq) {
        return oauth2LoginService.loginOauth2(rq);
    }

    @GetMapping(ApiPath.LOGIN_OAUTH2)
    public String getCode(@RequestParam String code) {
        return code;
    }

    @PostMapping(ApiPath.SIGNIN)
    public JwtTokenDto signIn(@Validated JwtTokenDto jwtTokenDto) {

        if (jwtTokenFactory.validateToken(jwtTokenDto)) {
            throw new RuntimeException("토큰 시간 초과");
        }

        return memberService.authenticate(jwtTokenDto);
    }

    @PostMapping(ApiPath.SIGNUP)
    public void signUp(@Validated SignUpRq rq) {

        memberService.registerMember(rq);
    }

}
