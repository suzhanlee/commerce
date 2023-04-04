package com.commerce.web.domain.member.controller;

import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.model.rq.DeleteMemberRq;
import com.commerce.web.domain.member.model.rq.UpdateMemberRq;
import com.commerce.web.domain.member.model.rs.FindMemberByIdRs;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.member.service.MemberService;
import com.commerce.web.global.path.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(ApiPath.MEMBER_ID)
    public FindMemberByIdRs findMemberById(@PathVariable("member-id") Long memberId) {
        return findMemberService.findMemberById(memberId);
    }

    @PutMapping(ApiPath.MEMBER)
    public void updateMember(@Validated @RequestBody UpdateMemberRq rq) {

    }

    @DeleteMapping(ApiPath.MEMBER)
    public void deleteMember(@Validated @RequestBody DeleteMemberRq rq) {

    }

}
