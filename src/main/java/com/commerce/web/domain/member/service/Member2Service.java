package com.commerce.web.domain.member.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.member.Member2;
import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.model.rq.DeleteMemberRq;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.domain.member.repository.MemberRepository2;
import com.commerce.web.global.exception.CannotFindMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class Member2Service {

    private final MemberRepository2 memberRepository;

    public void createMember(CreateMemberRq rq) {
        Member2 member = Member2.createSeller(rq.getName());
        memberRepository.save(member);
    }

    public void deleteMember(DeleteMemberRq rq) {
        memberRepository.deleteById(rq.getMemberId());
    }

    public void deleteItem(DeleteMemberRq rq) {
        Member2 member = memberRepository.findById(rq.getMemberId())
            .orElseThrow(CannotFindMemberException::new);
        member.getItemList().remove(0);

        for (Item item : member.getItemList()) {
            System.out.println("item = " + item);
        }

    }
}

