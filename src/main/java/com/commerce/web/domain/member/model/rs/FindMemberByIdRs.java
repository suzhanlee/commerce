package com.commerce.web.domain.member.model.rs;

import com.commerce.db.entity.member.Member;
import com.commerce.db.enums.member.MemberRole;
import lombok.Getter;

@Getter
public class FindMemberByIdRs {

    private Long id;

    private String name;

    private String email;

    private MemberRole memberRole;

    public static FindMemberByIdRs create(Member member) {
        FindMemberByIdRs rs = new FindMemberByIdRs();
        rs.id = member.getId();
        rs.name = member.getName();
        rs.email = member.getEmail();
        rs.memberRole = member.getMemberRole();
        return rs;
    }
}
