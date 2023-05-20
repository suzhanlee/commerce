package com.commerce.web.global.security.filter;

import com.commerce.web.global.security.JwtExceptionCode;
import com.commerce.web.global.security.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_HEADER;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_PREFIX;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenFactory jwtTokenFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(TOKEN_HEADER);

        try {
            authentication(token);
        } catch (ExpiredJwtException e) {
            request.setAttribute("JWT Exception", JwtExceptionCode.EXPIRED);
        } catch (UnsupportedJwtException e) {
            request.setAttribute("JWT Exception", JwtExceptionCode.UNSUPPORTED);
        } catch (MalformedJwtException e) {
            request.setAttribute("JWT Exception", JwtExceptionCode.MALFORMED);
        } catch (SignatureException e) {
            request.setAttribute("JWT Exception", JwtExceptionCode.INVALID_SIGNATURE);
        } catch (IllegalArgumentException e) {
            request.setAttribute("JWT Exception", JwtExceptionCode.INVALID);
        }

        filterChain.doFilter(request, response);
    }

    private void authentication(String token) {
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.substring(TOKEN_PREFIX.length());
            Authentication authentication = jwtTokenFactory.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
