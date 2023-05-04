package com.commerce.web.global.exception;

import org.springframework.http.HttpStatus;

public class SignUpFailException extends GeneralException{

    public SignUpFailException() {
        super(HttpStatus.BAD_REQUEST, "회원가입에 실패했습니다.");
    }


}
