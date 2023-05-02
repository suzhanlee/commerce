package com.commerce.web.global.security;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFactory {


    public JwtTokenDto generateJwtToken(Member member) {
        // TODO: 2023-05-02 구현
        return null;
    }

}
