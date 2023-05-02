package com.commerce.web.domain.auth.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class JwtTokenDto {

    @Schema(name = "토큰")
    private String token;

    @Schema(name = "만료시간")
    private String expiredDateTime;

    public JwtTokenDto(String code) {
        this.token = code;
    }
}
