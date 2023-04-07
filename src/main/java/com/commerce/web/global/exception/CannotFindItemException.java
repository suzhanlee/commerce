package com.commerce.web.global.exception;

import org.springframework.http.HttpStatus;

public class CannotFindItemException extends GeneralException{

    public CannotFindItemException() {
        super(HttpStatus.BAD_REQUEST, "상품을 찾을 수 없습니다.");
    }
}
