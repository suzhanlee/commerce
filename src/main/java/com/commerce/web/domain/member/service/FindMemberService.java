package com.commerce.web.domain.member.service;

import com.commerce.db.entity.member.Member;
import com.commerce.db.enums.auth.ClientType;
import com.commerce.web.domain.member.model.rs.FindMemberByIdRs;
import com.commerce.web.domain.member.model.rs.FindMySelfRs;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.CannotFindMemberException;
import com.commerce.web.global.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindMemberService {

    private final MemberRepository memberRepository;

    public FindMemberByIdRs findMemberById(Long memberId) {
        Member member = findByIdOrElseThrow(memberId);
        return FindMemberByIdRs.create(member);
    }

    public Member findByIdOrElseThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(CannotFindMemberException::new);
    }

    public Member findByUsernameOrElseThrow(String username) {
        return memberRepository.findByName(username)
                .orElseThrow(CannotFindMemberException::new);
    }

    public Member findByEmailAndClientTypeOrElseNull(String email, ClientType clientType) {
        return memberRepository.findByEmailAndClientType(email, clientType)
                .orElse(null);
    }

    public Member findByEmailAndClientTypeOrElseThrow(String email, ClientType clientType) {
        return memberRepository.findByEmailAndClientType(email, clientType)
                .orElseThrow(CannotFindMemberException::new);
    }

    private Member findLoginMember(MemberContext memberContext){
        String email = memberContext.getEmail();
        ClientType clientType = memberContext.getClientType();
        return findByEmailAndClientTypeOrElseThrow(email, clientType);
    }

    public FindMySelfRs findMySelf(MemberContext memberContext) {
        Member loginMember = findLoginMember(memberContext);
        return FindMySelfRs.create(loginMember);
    }
}
