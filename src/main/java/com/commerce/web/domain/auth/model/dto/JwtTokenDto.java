package com.commerce.web.domain.auth.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Getter;

@Getter
public class JwtTokenDto {

    @Schema(name = "토큰")
    private String token;

    @Schema(name = "만료시간")
    private String expiredDateTime;

    public static JwtTokenDto createJwtTokenDto(String token, String expiredDateTime) {
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.token = token;
        jwtTokenDto.expiredDateTime = expiredDateTime;
        return jwtTokenDto;
    }


}
