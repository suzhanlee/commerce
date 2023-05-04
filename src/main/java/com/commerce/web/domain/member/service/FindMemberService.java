package com.commerce.web.domain.member.service;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.member.model.rs.FindMemberByIdRs;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.CannotFindMemberException;
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
}
