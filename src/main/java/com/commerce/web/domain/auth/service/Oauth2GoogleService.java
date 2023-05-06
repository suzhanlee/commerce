package com.commerce.web.domain.auth.service;

import static com.commerce.web.domain.auth.constant.GoogleConstant.ACCESS_TOKEN;
import static com.commerce.web.domain.auth.constant.GoogleConstant.CLIENT_ID;
import static com.commerce.web.domain.auth.constant.GoogleConstant.CLIENT_SECRET;
import static com.commerce.web.domain.auth.constant.GoogleConstant.CODE;
import static com.commerce.web.domain.auth.constant.GoogleConstant.GRANT_TYPE;
import static com.commerce.web.domain.auth.constant.GoogleConstant.REDIRECT_URI;
import static com.commerce.web.domain.auth.constant.GoogleConstant.TOKEN_URL;

import com.commerce.web.domain.auth.constant.GoogleConstant;
import com.commerce.web.domain.auth.constant.KakaoConstants;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.global.exception.AuthenticationException;
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

@Service
public class Oauth2GoogleService {

    @Value("{spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("{spring.security.oauth2.client.registration.google.client-id}")
    private String clientSecret;

    @Value("{spring.security.oauth2.client.registration.google.redirect-url}")
    private String redirectUri;

    @Value("{spring.security.oauth2.client.registration.google.grant-type}")
    private String grantType;


    public JwtTokenDto getToken(String code) {
        String token = getGoogleToken(code);
        getMemberByGoogleToken(token);

        return null;
    }

    private void getMemberByGoogleToken(String googleToken) {

    }

    private String getGoogleToken(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic c3FIOG9vSGV4VHo4QzAyg5T1JvNnJoZ3ExaVNyQWw6WjRsanRKZG5lQk9qUE1BVQ");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add(CLIENT_ID, clientId);
        params.add(CLIENT_SECRET, clientSecret);
        params.add(CODE, code);
        params.add(REDIRECT_URI, redirectUri);
        params.add(GRANT_TYPE, grantType);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        JSONObject jsonObject = getResponse(httpEntity, TOKEN_URL);

        System.out.println("jsonObject = " + jsonObject);

        return (String) jsonObject.get(ACCESS_TOKEN);

    }

    private JSONObject getResponse(HttpEntity<LinkedMultiValueMap<String, String>> httpEntity,
        String url) {

        try {

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity,
                String.class);

            String body = response.getBody();
            JSONParser jsonParser = new JSONParser();
            return (JSONObject) jsonParser.parse(body);

        } catch (ParseException e) {

            throw new AuthenticationException();

        }


    }


}
