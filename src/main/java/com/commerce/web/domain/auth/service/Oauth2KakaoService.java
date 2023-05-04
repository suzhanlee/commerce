package com.commerce.web.domain.auth.service;

import static com.commerce.web.domain.auth.constant.KakaoConstants.AUTHORIZATION;
import static com.commerce.web.domain.auth.constant.KakaoConstants.BEARER;
import static com.commerce.web.domain.auth.constant.KakaoConstants.TOKEN_URL;
import static com.commerce.web.domain.auth.constant.KakaoConstants.USER_INFO_URL;
import static com.commerce.web.global.security.constant.JwtConstants.APPLICATION_JSON;
import static com.commerce.web.global.security.constant.JwtConstants.SIGN_IN_URL;
import static com.commerce.web.global.security.constant.JwtConstants.SIGN_UP_URL;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.constant.KakaoConstants;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.AuthenticationException;
import com.commerce.web.global.exception.SignInFailException;
import com.commerce.web.global.exception.SignUpFailException;
import com.commerce.web.global.security.JwtTokenFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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
@RequiredArgsConstructor
@Slf4j
public class Oauth2KakaoService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.grant-type}")
    private String grantType;

    private final JwtTokenFactory jwtTokenFactory;
    private final MemberRepository memberRepository;

    public JwtTokenDto getToken(String code) {

        String kakaoToken = getKakaoToken(code);
        Member member = getMemberByKaKaoToken(kakaoToken);

        Optional<Member> findMember = memberRepository.findByName(member.getName());

        OkHttpClient client = new OkHttpClient();

        // 회원가입
        if (findMember.isEmpty()) {

//            String json = "{\r\n" +
//                " \"username\" : \""+member.getName()+"\",\r\n" +
//                " \"email\" : \""+member.getEmail()+"\",\r\n" +
//                "}";

            Map<String, Object> params = new HashMap<>();

            params.put("username", member.getName());
            params.put("email", member.getEmail());

            JSONObject jsonObject = new JSONObject(params);
            ObjectMapper objectMapper = new ObjectMapper();
            String postBody;
            try {
                postBody = objectMapper.writeValueAsString(jsonObject);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            RequestBody requestBody = RequestBody.create(
                okhttp3.MediaType.parse(APPLICATION_JSON),
                postBody);

            Request.Builder builder = new Request.Builder().url(SIGN_UP_URL)
                .post(requestBody);
            Request request = builder.build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (response.isSuccessful()) {
                log.info("회원가입 성공!");
                return null;
            } else {
                throw new SignUpFailException();
            }

        }

        // 로그인
        JwtTokenDto jwtTokenDto = jwtTokenFactory.generateJwtToken(member);

        Map<String, Object> params = new HashMap<>();

        params.put("token", jwtTokenDto.getToken());
        params.put("expiredDateTime", jwtTokenDto.getExpiredDateTime());

        JSONObject jsonObject = new JSONObject(params);
        ObjectMapper objectMapper = new ObjectMapper();
        String postBody;
        try {
            postBody = objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RequestBody requestBody = RequestBody.create(
            okhttp3.MediaType.parse(APPLICATION_JSON),
            postBody);

        Request.Builder builder = new Request.Builder().url(SIGN_IN_URL)
            .post(requestBody);
        Request request = builder.build();

        Response response;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response.isSuccessful()) {
            log.info("로그인 성공");
            return jwtTokenDto;
        } else {
            throw new SignInFailException();
        }

    }

    private Member getMemberByKaKaoToken(String kakaoToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(AUTHORIZATION, BEARER + kakaoToken);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        JSONObject jsonObject = getResponse(httpEntity, USER_INFO_URL);
        Map<String, Object> attributes = (Map<String, Object>) jsonObject.get(
            KakaoConstants.KAKAO_ACCOUNT);

        JSONObject profile = (JSONObject) attributes.get("profile");
        String nickname = (String) profile.get("nickname");
        String email = (String) attributes.get("email");

        return Member.createCustomer(nickname, email);
    }

    private String getKakaoToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(KakaoConstants.CLIENT_ID, clientId);
        params.add(KakaoConstants.REDIRECT_URI, redirectUri);
        params.add(KakaoConstants.GRANT_TYPE, grantType);
        params.add(KakaoConstants.CODE, code);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(params,
            headers);

        JSONObject jsonObject = getResponse(httpEntity, TOKEN_URL);
        return (String) jsonObject.get(KakaoConstants.ACCESS_TOKEN);
    }

    private JSONObject getResponse(HttpEntity<LinkedMultiValueMap<String, String>> httpEntity,
        String url) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            // 여기서 .postForEntity를 통해 kakao api 로 요청함.
            ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity,
                String.class);

            // body에 토큰이 담겨있음/
            String body = response.getBody();
            JSONParser jsonParser = new JSONParser();
            return (JSONObject) jsonParser.parse(body);

        } catch (ParseException e) {

            throw new AuthenticationException();
        }
    }
}
