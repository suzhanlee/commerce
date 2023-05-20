package com.commerce.web.domain.auth.service;

import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.member.MemberLogInLog;
import com.commerce.db.entity.member.MemberSignUpLog;
import com.commerce.web.domain.auth.constant.KakaoConstants;
import com.commerce.web.domain.auth.model.dto.JwtTokenDto;
import com.commerce.web.domain.member.repository.MemberLogInLogRepository;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.domain.member.repository.MemberSignUpLogRepository;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.global.exception.AuthenticationException;
import com.commerce.web.global.security.jwt.JwtTokenFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Objects;

import static com.commerce.db.enums.auth.ClientType.KAKAO;
import static com.commerce.web.domain.auth.constant.KakaoConstants.AUTHORIZATION;
import static com.commerce.web.domain.auth.constant.KakaoConstants.BEARER;
import static com.commerce.web.domain.auth.constant.KakaoConstants.TOKEN_URL;
import static com.commerce.web.domain.auth.constant.KakaoConstants.USER_INFO_URL;

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
    private final FindMemberService findMemberService;
    private final MemberRepository memberRepository;
    private final MemberSignUpLogRepository memberSignUpLogRepository;
    private final MemberLogInLogRepository memberLogInLogRepository;


    public JwtTokenDto getToken(String code) {

        String kakaoToken = getKakaoToken(code);
        Member member = getMemberByKaKaoToken(kakaoToken);

        // 로그인 로그
        MemberLogInLog memberLogInLog = MemberLogInLog.create(member);
        memberLogInLogRepository.save(memberLogInLog);

        return jwtTokenFactory.generateJwtToken(member);
    }

    private Member getMemberByKaKaoToken(String kakaoToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(AUTHORIZATION, BEARER + kakaoToken);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        JSONObject jsonObject = getResponse(httpEntity, USER_INFO_URL);
        Map<String, Object> attributes = (Map<String, Object>) jsonObject.get(KakaoConstants.KAKAO_ACCOUNT);

        String email = (String) attributes.get("email");

        Member member = findMemberService.findByEmailAndClientTypeOrElseNull(email, KAKAO);
        if (Objects.nonNull(member)) {
            return member;
        }

        JSONObject profile = (JSONObject) attributes.get("profile");
        String nickname = (String) profile.get("nickname");

        member = Member.createCustomer(nickname, email, KAKAO);

        // 회원가입 로그
        memberRepository.save(member);

        MemberSignUpLog memberSignUpLog = MemberSignUpLog.create(member);
        memberSignUpLogRepository.save(memberSignUpLog);

        return member;
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
