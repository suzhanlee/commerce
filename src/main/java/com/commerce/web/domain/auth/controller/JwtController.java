package com.commerce.web.domain.auth.controller;

import static com.commerce.web.global.path.ApiPath.SIGNIN;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.auth.model.rq.LoginMemberRq;
import com.commerce.web.domain.auth.service.JwtLoginService;
import com.commerce.web.global.path.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtController {

    private final JwtLoginService jwtLoginService;

    @PostMapping(SIGNIN)
    public JwtTokenDto loginMember(@RequestBody @Validated LoginMemberRq rq) {

        String username = rq.getUsername();
        String password = rq.getPassword();

        return jwtLoginService.login(username, password);

    }
}
