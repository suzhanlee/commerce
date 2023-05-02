package com.commerce.web.domain.auth.service;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.constant.KakaoConstants;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.global.exception.AuthenticationException;
import com.commerce.web.global.security.JwtTokenFactory;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.commerce.web.domain.auth.constant.KakaoConstants.AUTHORIZATION;
import static com.commerce.web.domain.auth.constant.KakaoConstants.BEARER;
import static com.commerce.web.domain.auth.constant.KakaoConstants.TOKEN_URL;
import static com.commerce.web.domain.auth.constant.KakaoConstants.USER_INFO_URL;

@Service
@RequiredArgsConstructor
public class Oauth2KakaoService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.grant-type}")
    private String grantType;

    private final JwtTokenFactory jwtTokenFactory;

    public JwtTokenDto getToken(String code) {

        String kakaoToken = getKakaoToken(code);
        Member member = getMemberByKaKaoToken(kakaoToken);
        return jwtTokenFactory.generateJwtToken(member);
    }

    private Member getMemberByKaKaoToken(String kakaoToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(AUTHORIZATION, BEARER + kakaoToken);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        JSONObject jsonObject = getResponse(httpEntity, USER_INFO_URL);
        Map<String, Object> attributes = (Map<String, Object>) jsonObject.get(KakaoConstants.KAKAO_ACCOUNT);
        return null;
    }

    private String getKakaoToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(KakaoConstants.CLIENT_ID, clientId);
        params.add(KakaoConstants.REDIRECT_URI, redirectUri);
        params.add(KakaoConstants.GRANT_TYPE, grantType);
        params.add(KakaoConstants.CODE, code);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        JSONObject jsonObject = getResponse(httpEntity, TOKEN_URL);
        return (String) jsonObject.get(KakaoConstants.ACCESS_TOKEN);
    }

    private JSONObject getResponse(HttpEntity<LinkedMultiValueMap<String, String>> httpEntity, String url) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
            String body = response.getBody();
            JSONParser jsonParser = new JSONParser();
            return (JSONObject) jsonParser.parse(body);

        } catch (ParseException e) {

            throw new AuthenticationException();
        }
    }
}
