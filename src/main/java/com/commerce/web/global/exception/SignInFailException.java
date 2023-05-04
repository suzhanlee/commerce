package com.commerce.web.global.exception;

import org.springframework.http.HttpStatus;

public class SignInFailException extends GeneralException{

    public SignInFailException() {
        super(HttpStatus.BAD_REQUEST, "로그인에 실패했습니다.");
    }

}
