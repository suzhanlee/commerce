package com.commerce.web.domain.member.service;

import com.commerce.db.entity.Member;
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
        Member member = memberRepository.findById(memberId)
                .orElseThrow(CannotFindMemberException::new);
        return FindMemberByIdRs.create(member);
    }
}
