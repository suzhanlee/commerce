package com.commerce.web.domain.member.repository;

import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.member.Member2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository2 extends JpaRepository<Member2, Long> {

}
