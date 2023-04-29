package com.commerce.web.global.exception;

import org.springframework.http.HttpStatus;

public class CannotFindVegetableException extends GeneralException {

    public CannotFindVegetableException() {
        super(HttpStatus.BAD_REQUEST, "채소를 찾을 수 없습니다.");
    }


}
