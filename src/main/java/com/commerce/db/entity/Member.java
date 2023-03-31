package com.commerce.db.entity;

import com.commerce.db.enums.MemberRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Member {
    @Id
    private Long id;

    private String name;

    private MemberRole memberRole;

    // 정적 팩터리 메서드 패턴
    public static Member createAdmin(String name) {
        Member member = new Member();
        member.name = name;
        member.memberRole = MemberRole.ROLE_ADMIN;
        return member;
    }

    public static Member createNormal(String name) {
        Member member = new Member();
        member.name = name;
        member.memberRole = MemberRole.ROLE_NORMAL;
        return member;
    }

}
