package com.commerce.web.domain.auth.controller;

import com.commerce.web.domain.auth.model.rq.LoginMemberRq;
import com.commerce.web.domain.auth.service.JwtLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtController {

    private final JwtLoginService jwtLoginService;

    @PostMapping("/api/signin")
    public void loginMember(@RequestBody @Validated LoginMemberRq rq) {

        String username = rq.getUsername();
        String password = rq.getPassword();

        jwtLoginService.




    }

}
