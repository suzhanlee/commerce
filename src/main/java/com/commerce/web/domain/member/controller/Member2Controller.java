package com.commerce.web.domain.member.controller;

import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.model.rq.DeleteMemberRq;
import com.commerce.web.domain.member.model.rq.UpdateMemberRq;
import com.commerce.web.domain.member.model.rs.FindMember2ByIdRs;
import com.commerce.web.domain.member.model.rs.FindMemberByIdRs;
import com.commerce.web.domain.member.service.FindMember2Service;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.member.service.Member2Service;
import com.commerce.web.domain.member.service.MemberService;
import com.commerce.web.global.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
public class Member2Controller {

    private final Member2Service memberService;
    private final FindMember2Service findMemberService;

    @Operation(summary = "", description = "")
    @PostMapping(ApiPath.MEMBER2)
    public void createMember(@Validated @RequestBody CreateMemberRq rq) {
        memberService.createMember(rq);
    }

    @Operation(summary = "", description = "")
    @GetMapping(ApiPath.MEMBER2_ID)
    public FindMember2ByIdRs findMemberById(@PathVariable("member-id") Long memberId) {
        return findMemberService.findMemberById(memberId);
    }

    @Operation(summary = "", description = "")
    @PutMapping(ApiPath.MEMBER2)
    public void updateMember(@Validated @RequestBody UpdateMemberRq rq) {

    }

    @Operation(summary = "", description = "")
    @DeleteMapping(ApiPath.MEMBER2)
    public void deleteMember(@Validated @RequestBody DeleteMemberRq rq) {

        memberService.deleteMember(rq);

    }

    @Operation(summary = "", description = "")
    @DeleteMapping(ApiPath.MEMBER2_ITEM)
    public void deleteItemByMember(@Validated @RequestBody DeleteMemberRq rq) {

        memberService.deleteItem(rq);

    }

}
