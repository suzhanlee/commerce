package com.commerce.web.domain.auth.model.rq;

import com.commerce.db.enums.auth.ClientType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class LoginOauth2Rq {

    @Schema(name = "클라이언트 타입")
    private ClientType clientType;

    @Schema(name = "Oauth2 인증 코드")
    private String code;

}
