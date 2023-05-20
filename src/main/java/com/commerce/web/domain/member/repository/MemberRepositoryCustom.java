package com.commerce.web.domain.member.repository;

import com.commerce.db.entity.member.Member;
import com.commerce.db.enums.auth.ClientType;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmailAndClientType(String email, ClientType clientType);
}
