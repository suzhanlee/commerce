package com.commerce.web.domain.member.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.member.model.rq.CreateMemberRq;
import com.commerce.web.domain.member.model.rq.DeleteMemberRq;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.CannotFindMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void createMember(CreateMemberRq rq) {
        Member member = Member.createSeller(rq.getName(), rq.getEmail(), rq.getPhone());
        memberRepository.save(member);
    }

    public void deleteMember(DeleteMemberRq rq) {
        memberRepository.deleteById(rq.getMemberId());
    }

    public void deleteItem(DeleteMemberRq rq) {
        Member member = memberRepository.findById(rq.getMemberId())
                .orElseThrow(CannotFindMemberException::new);
        member.getItemList().remove(0);

        for (Item item : member.getItemList()) {
            System.out.println("item = " + item);
        }
    }
}

