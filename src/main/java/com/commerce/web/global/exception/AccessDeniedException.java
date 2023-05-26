package com.commerce.web.global.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends GeneralException{

    public AccessDeniedException() {
        super(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다.");
    }

}
