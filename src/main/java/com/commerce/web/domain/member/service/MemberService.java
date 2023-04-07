package com.commerce.web.domain.member.service;

import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(CreateMemberRq rq) {

        Member member = Member.createSeller(rq.getName(), rq.getEmail(), rq.getPhone());
        memberRepository.save(member);

    }
}

