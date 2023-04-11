package com.commerce.db.entity.member;

import com.commerce.db.enums.member.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(nullable = false)
    @Enumerated(STRING)
    private MemberRole memberRole;


    public static Member createSeller(String name, String email, String phone) {
        Member member = new Member();
        member.name = name;
        member.email = email;
        member.phone = phone;
        member.memberRole = MemberRole.ROLE_SELLER;
        return member;
    }

}
