package com.commerce.web.domain.member.controller;

import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.member.service.MemberService;
import com.commerce.web.global.path.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final FindMemberService findMemberService;


    @PostMapping(ApiPath.MEMBER)
    public void createMember(@Validated @RequestBody CreateMemberRq rq) {
        memberService.createMember(rq);
    }

}
