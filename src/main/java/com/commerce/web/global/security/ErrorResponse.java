package com.commerce.web.global.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String error;
    private String message;
}