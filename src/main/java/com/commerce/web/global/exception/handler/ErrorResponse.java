package com.commerce.web.global.exception.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private Integer httpStatus;
    private String message;
}
