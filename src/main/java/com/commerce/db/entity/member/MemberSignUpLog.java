package com.commerce.db.entity.member;

import com.commerce.db.entity.BaseCreateTimeEntity;
import com.commerce.db.enums.auth.ClientType;
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
public class MemberSignUpLog extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(STRING)
    private ClientType clientType;

    public static MemberSignUpLog create(Member member) {
        MemberSignUpLog log = new MemberSignUpLog();
        log.memberId = member.getId();
        log.name = member.getName();
        log.email = member.getEmail();
        log.clientType = member.getClientType();
        return log;
    }
}
