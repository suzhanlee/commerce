package com.commerce.web.global.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtExceptionCode {

    EXPIRED("ExpiredJwtException", "유효기간이 만료된 토큰입니다."),
    UNSUPPORTED("UnsupportedJwtException", "지원하지 않는 토큰 형식입니다."),
    MALFORMED("MalformedJwtException", "올바른 구성의 토큰이 아닙니다."),
    INVALID_SIGNATURE("SignatureException", "유효한 서명의 토큰이 아닙니다."),
    INVALID("IllegalArgumentException", "유효하지 않은 토큰입니다.");

    private final String code;
    private final String message;

}
