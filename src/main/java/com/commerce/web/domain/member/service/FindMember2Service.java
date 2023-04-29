//package com.commerce.web.domain.member.service;
//
//import com.commerce.web.domain.member.model.rs.FindMember2ByIdRs;
//import com.commerce.web.global.exception.CannotFindMemberException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class FindMember2Service {
//
//    private final Member2Repository memberRepository;
//
//    public FindMember2ByIdRs findMemberById(Long memberId) {
//        Member2 member = findByIdOrElseThrow(memberId);
//        return FindMember2ByIdRs.create(member);
//    }
//
//
//    public Member2 findByIdOrElseThrow(Long memberId) {
//        return memberRepository.findById(memberId)
//                .orElseThrow(CannotFindMemberException::new);
//    }
//}
