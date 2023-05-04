package com.commerce.web.global.security.filter;

import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_HEADER;
import static com.commerce.web.global.security.constant.JwtConstants.TOKEN_PREFIX;

import com.commerce.web.global.security.JwtTokenFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenFactory jwtTokenFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String token = resolveTokenFromRequest(request);

        if (StringUtils.hasText(token) && jwtTokenFactory.validateToken(token)) {

            Authentication auth = jwtTokenFactory.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        filterChain.doFilter(request, response);

    }

    public String resolveTokenFromRequest(HttpServletRequest rq) {

        String token = rq.getHeader(TOKEN_HEADER);

        if (!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) {

            return token.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
