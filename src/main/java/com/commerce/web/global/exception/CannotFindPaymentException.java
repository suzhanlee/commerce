package com.commerce.web.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CannotFindPaymentException extends GeneralException{

    public CannotFindPaymentException() {
        super(HttpStatus.BAD_REQUEST, "지불 금액을 찾을 수 없습니다.");
    }
}
