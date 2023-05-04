package com.commerce.web.domain.auth.model.dto;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import okio.BufferedSink;

@Getter
public class JwtTokenDto extends RequestBody {

    @Schema(name = "토큰")
    private String token;

    @Schema(name = "만료시간")
    private Date expiredDateTime;

    public static JwtTokenDto createJwtTokenDto(String token, Date expiredDateTime) {
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.token = token;
        jwtTokenDto.expiredDateTime = expiredDateTime;
        return jwtTokenDto;
    }

    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
