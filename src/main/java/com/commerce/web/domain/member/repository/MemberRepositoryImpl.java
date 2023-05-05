package com.commerce.web.domain.member.repository;

import com.commerce.db.entity.member.Member;
import com.commerce.db.enums.auth.ClientType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.commerce.db.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findByEmailAndClientType(String email, ClientType clientType) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(member)
                        .where(member.email.eq(email),
                                member.clientType.eq(clientType))
                        .fetchFirst()
        );
    }
}
