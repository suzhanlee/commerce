package com.commerce.web.domain.auth.model.rq;

import com.commerce.web.domain.auth.constant.KakaoConstant;
import lombok.Getter;

@Getter
public class KakaoTokenRq {

    private String grantType = KakaoConstant.GRANT_TYPE;
    private String clientId = KakaoConstant.CLIENT_ID;
    private String redirectUrl = KakaoConstant.REDIRECT_URI;
    private String code;

}
