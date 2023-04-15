package com.commerce.web.domain.member.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.member.Member2;
import com.commerce.db.enums.member.MemberRole;
import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.model.rq.DeleteMemberRq;
import com.commerce.web.domain.member.repository.Member2Repository;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.CannotFindMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class Member2Service {

    private final Member2Repository member2Repository;
    private final MemberRepository memberRepository;

    public void createMember(CreateMemberRq rq) {
        Member2 member = Member2.createSeller(rq.getName());
        member2Repository.save(member);
    }

    public void deleteMember(DeleteMemberRq rq) {
        Member2 member2 = member2Repository.findById(rq.getMemberId())
                .orElse(null);

        Item item = member2.getItemList().get(0);

        Member member = item.getMember();
        MemberRole memberRole = member.getMemberRole();
        System.out.println(memberRole);

        memberRepository.delete(member);
//        member2Repository.delete(member2);
    }

    public void deleteItem(DeleteMemberRq rq) {
        Member2 member = member2Repository.findById(rq.getMemberId())
                .orElseThrow(CannotFindMemberException::new);
        member.getItemList().remove(0);

        for (Item item : member.getItemList()) {
            System.out.println("item = " + item);
        }

    }
}

