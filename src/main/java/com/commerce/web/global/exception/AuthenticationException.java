package com.commerce.web.global.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends GeneralException {

    public AuthenticationException() {
        super(HttpStatus.FORBIDDEN, "인증에 실패했습니다.");
    }

}
