package com.commerce.web.domain.auth.controller;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.auth.model.rq.LoginOauth2Rq;
import com.commerce.web.domain.auth.model.rs.CodeRs;
import com.commerce.web.domain.auth.service.Oauth2LoginService;
import com.commerce.web.global.path.ApiPath;
import jakarta.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping(ApiPath.LOGIN_OAUTH2_CODE)
//    public JwtTokenDto loginOauth2(@Validated @RequestBody LoginOauth2Rq rq) {
//        return oauth2LoginService.loginOauth2(rq);
//    }

    @GetMapping(ApiPath.LOGIN_OAUTH2_CODE)
    public CodeRs loginOauth2(@RequestParam("code") String code) {
        return oauth2LoginService.loginOauth2code(code);

    }

//    @GetMapping(ApiPath.LOGIN_OAUTH2_TOKEN)
//    public JwtTokenDto createOath2Token() {
////        return JwtTokenDto;
//    }

}
