package com.commerce.web.domain.member.repository;

import com.commerce.db.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByName(String username);

    Optional<Member> findByEmail(String email);


}
