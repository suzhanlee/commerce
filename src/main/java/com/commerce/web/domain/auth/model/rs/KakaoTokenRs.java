package com.commerce.web.domain.auth.model.rs;

import com.commerce.web.domain.auth.model.rq.KakaoTokenRq;
import lombok.Getter;

@Getter
public class KakaoTokenRs {

    private String tokenType;
    private String accessToken;
    private String idToken;
    private String expiresIn;

//    public static KakaoTokenRs createKakaoTokenRs(KakaoTokenRq rq) {
//
//
//
//
//
//    }

}
