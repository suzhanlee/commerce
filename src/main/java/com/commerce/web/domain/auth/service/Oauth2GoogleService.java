package com.commerce.web.domain.auth.service;

import static com.commerce.db.enums.auth.ClientType.GOOGLE;
import static com.commerce.db.enums.auth.ClientType.KAKAO;
import static com.commerce.web.domain.auth.constant.GoogleConstant.ACCESS_TOKEN;
import static com.commerce.web.domain.auth.constant.GoogleConstant.CLIENT_ID;
import static com.commerce.web.domain.auth.constant.GoogleConstant.CLIENT_SECRET;
import static com.commerce.web.domain.auth.constant.GoogleConstant.CODE;
import static com.commerce.web.domain.auth.constant.GoogleConstant.REDIRECT_URI;
import static com.commerce.web.domain.auth.constant.GoogleConstant.TOKEN_URL;
import static com.commerce.web.domain.auth.constant.KakaoConstants.AUTHORIZATION;
import static com.commerce.web.domain.auth.constant.KakaoConstants.BEARER;

import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.member.MemberLogInLog;
import com.commerce.db.entity.member.MemberSignUpLog;
import com.commerce.web.domain.auth.constant.GoogleConstant;
import com.commerce.web.domain.auth.constant.KakaoConstants;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.member.repository.MemberLogInLogRepository;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.domain.member.repository.MemberSignUpLogRepository;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.global.exception.AuthenticationException;
import com.commerce.web.global.security.jwt.JwtTokenFactory;
import java.util.Map;
import java.util.Objects;
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
public class Oauth2GoogleService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

//    @Value("${spring.security.oauth2.client.registration.google.grant-type}")
//    private String grantType;

    private final JwtTokenFactory jwtTokenFactory;
    private final FindMemberService findMemberService;
    private final MemberRepository memberRepository;
    private final MemberSignUpLogRepository memberSignUpLogRepository;
    private final MemberLogInLogRepository memberLogInLogRepository;


    public JwtTokenDto getToken(String code) {

        String token = getGoogleToken(code);
        Member member = getMemberByGoogleToken(token);

        MemberLogInLog memberLogInLog = MemberLogInLog.create(member);

        memberLogInLogRepository.save(memberLogInLog);

        return jwtTokenFactory.generateJwtToken(member);
    }

    private Member getMemberByGoogleToken(String googleToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(AUTHORIZATION, BEARER + googleToken);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        JSONObject jsonObject = getResponse(httpEntity, GoogleConstant.USER_INFO_URL);
        Map<String, Object> attributes = (Map<String, Object>) jsonObject.get(KakaoConstants.KAKAO_ACCOUNT);

        String email = (String) attributes.get("email");

        Member member = findMemberService.findByEmailAndClientTypeOrElseNull(email, KAKAO);
        if (Objects.nonNull(member)) {
            return member;
        }

        JSONObject profile = (JSONObject) attributes.get("profile");
        String nickname = (String) profile.get("nickname");

        member = Member.createCustomer(nickname, email, GOOGLE);

        // 회원가입 로그
        memberRepository.save(member);

        MemberSignUpLog memberSignUpLog = MemberSignUpLog.create(member);
        memberSignUpLogRepository.save(memberSignUpLog);

        return member;

    }

    private String getGoogleToken(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Authorization", "Basic c3FIOG9vSGV4VHo4QzAyg5T1JvNnJoZ3ExaVNyQWw6WjRsanRKZG5lQk9qUE1BVQ");

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add(CLIENT_ID, clientId);
        params.add(CLIENT_SECRET, clientSecret);
        params.add(CODE, code);
        params.add(REDIRECT_URI, redirectUri);
        params.add("grant_type", "authorization_code");

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        JSONObject jsonObject = getResponse(httpEntity, TOKEN_URL);

        System.out.println("jsonObject = " + jsonObject);

        return (String) jsonObject.get(ACCESS_TOKEN);

    }

    private JSONObject getResponse(HttpEntity<LinkedMultiValueMap<String, String>> httpEntity, String url) {

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
