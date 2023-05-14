package com.commerce.web.domain.member.controller;

import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.model.rq.DeleteMemberRq;
import com.commerce.web.domain.member.model.rq.UpdateMemberRq;
import com.commerce.web.domain.member.model.rs.FindMemberByIdRs;
import com.commerce.web.domain.member.model.rs.FindMySelfRs;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.member.service.MemberService;
import com.commerce.web.global.path.ApiPath;
import com.commerce.web.global.security.MemberContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@Tag(name = "멤버 컨트롤러")
public class MemberController {

    private final MemberService memberService;
    private final FindMemberService findMemberService;

    @Operation(summary = "", description = "")
    @PostMapping(ApiPath.MEMBER)
    public void createMember(@Validated @RequestBody CreateMemberRq rq) {
        memberService.createMember(rq);
    }

    @Operation(summary = "", description = "")
    @GetMapping(ApiPath.MEMBER_ID)
    public FindMemberByIdRs findMemberById(@PathVariable("member-id") Long memberId) {
        return findMemberService.findMemberById(memberId);
    }

    @GetMapping(ApiPath.MEMBER_MYSELF)
    public FindMySelfRs findMySelf(@AuthenticationPrincipal MemberContext memberContext) {
        return findMemberService.findMySelf(memberContext);
    }

    @Operation(summary = "", description = "")
    @PutMapping(ApiPath.MEMBER)
    public void updateMember(@Validated @RequestBody UpdateMemberRq rq) {

    }

    @Operation(summary = "", description = "")
    @DeleteMapping(ApiPath.MEMBER)
    public void deleteMember(@Validated @RequestBody DeleteMemberRq rq) {

        memberService.deleteMember(rq);

    }

    @Operation(summary = "", description = "")
    @DeleteMapping(ApiPath.MEMBER_ITEM)
    public void deleteItemByMember(@Validated @RequestBody DeleteMemberRq rq) {
        memberService.deleteItem(rq);
    }


}
