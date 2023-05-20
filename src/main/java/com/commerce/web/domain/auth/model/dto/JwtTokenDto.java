package com.commerce.web.domain.auth.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class JwtTokenDto {

    @Schema(name = "토큰")
    private String token;

    @Schema(name = "만료시간")
    private LocalDateTime expiredDateTime;

    public static JwtTokenDto createJwtTokenDto(String token, LocalDateTime expiredDateTime) {
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.token = token;
        jwtTokenDto.expiredDateTime = expiredDateTime;
        return jwtTokenDto;
    }


}
