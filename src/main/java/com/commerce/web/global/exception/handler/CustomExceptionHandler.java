package com.commerce.web.global.exception.handler;

import com.commerce.web.global.exception.GeneralException;
import com.commerce.web.global.security.JwtExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> generalHttpExceptionHandler(GeneralException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(e.getHttpStatus().value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> generalHttpExceptionHandler(RuntimeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("알 수 없는 에러입니다.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> generalHttpExceptionHandler(JwtExceptionCode e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .message("인증이 실패했습니다.")
            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
