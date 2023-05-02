package com.commerce.web.domain.auth.model.rq;

import com.commerce.web.domain.auth.constant.KakaoConstants;
import lombok.Getter;

@Getter
public class KakaoTokenRq {

    private String grantType = KakaoConstants.GRANT_TYPE;
    private String clientId = KakaoConstants.CLIENT_ID;
    private String redirectUrl = KakaoConstants.REDIRECT_URI;
    private String code;

}
