package com.commerce.web.domain.auth.service;

import static com.commerce.web.domain.auth.constant.KakaoConstants.AUTHORIZATION;
import static com.commerce.web.domain.auth.constant.KakaoConstants.BEARER;
import static com.commerce.web.domain.auth.constant.KakaoConstants.TOKEN_URL;
import static com.commerce.web.domain.auth.constant.KakaoConstants.USER_INFO_URL;
import static com.squareup.okhttp.MediaType.parse;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.auth.constant.KakaoConstants;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.AuthenticationException;
import com.commerce.web.global.security.JwtTokenFactory;
import com.commerce.web.global.security.constant.JwtConstants;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
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

    private final MemberRepository memberRepository;

    public JwtTokenDto getToken(String code) {

        String kakaoToken = getKakaoToken(code);
        Member member = getMemberByKaKaoToken(kakaoToken);

        Optional<Member> findMember = memberRepository.findByName(member.getName());

        OkHttpClient client = new OkHttpClient();

        if (findMember.isEmpty()) {

            String json =
                "{\"username\": " + member.getUsername() + ",\"email\": " + member.getEmail()
                    + "\"}";

            RequestBody body = RequestBody.create(parse("application/json"), json);

            Request request = new Builder().url(JwtConstants.SIGN_UP_URL).post(body).build();

            Response response;

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (response.isSuccessful()) {
                System.out.println("회원가입 성공!");
            } else {
                throw new RuntimeException("회원가입 실패");
            }

        }

        // email이 존재하므로 로그인하기
        JwtTokenDto jwtTokenDto = jwtTokenFactory.generateJwtToken(member);

        Builder builder = new Builder().url(JwtConstants.SIGN_IN_URL).post(jwtTokenDto);

        Request request = builder.build();

        Response response;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response.isSuccessful()) {
            System.out.println("로그인 성공!");
        } else {
            throw new RuntimeException("로그인 실패");
        }

        return jwtTokenDto;
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
