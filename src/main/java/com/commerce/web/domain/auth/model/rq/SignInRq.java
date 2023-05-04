package com.commerce.web.domain.auth.model.rq;

import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import lombok.Getter;

@Getter
public class SignInRq {

    private Long memberId;
    private String username;
    private String email;

}
