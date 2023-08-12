package com.commerce.web.global.security;

import static com.commerce.web.global.security.jwt.JwtExceptionCode.EXPIRED;
import static com.commerce.web.global.security.jwt.JwtExceptionCode.INVALID;
import static com.commerce.web.global.security.jwt.JwtExceptionCode.INVALID_SIGNATURE;
import static com.commerce.web.global.security.jwt.JwtExceptionCode.MALFORMED;
import static com.commerce.web.global.security.jwt.JwtExceptionCode.UNSUPPORTED;


import com.commerce.web.global.security.jwt.JwtExceptionCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // 인증 예외 처리

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        Object exception = request.getAttribute("JWT Exception");

        ObjectMapper mapper = new ObjectMapper();

        ErrorResponse error = null;

        if (exception == EXPIRED) {

            error = createErrorResponse(error, EXPIRED);

        } else if (exception == MALFORMED) {

            error = createErrorResponse(error, MALFORMED);

        } else if (exception == UNSUPPORTED) {

            error = createErrorResponse(error, UNSUPPORTED);

        } else if (exception == INVALID) {

            error = createErrorResponse(error, INVALID);

        } else if (exception == INVALID_SIGNATURE) {

            error = createErrorResponse(error, INVALID_SIGNATURE);

        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("content-type", "application/json");

        String result = mapper.writeValueAsString(error);
        response.getWriter().write(result);

        // vs sendRedirect로 ExceptionController로 보내기
    }

    private static ErrorResponse createErrorResponse(ErrorResponse error,
        JwtExceptionCode expired) {
        error = ErrorResponse
            .builder()
            .error(expired.getCode())
            .message(expired.getMessage())
            .build();
        return error;
    }
}
