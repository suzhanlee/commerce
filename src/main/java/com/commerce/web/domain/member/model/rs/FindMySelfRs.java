package com.commerce.web.domain.member.model.rs;

import com.commerce.db.entity.member.Member;
import lombok.Getter;

@Getter
public class FindMySelfRs {

    public String name;
    public String email;

    public static FindMySelfRs create(Member loginMember) {
        FindMySelfRs rs = new FindMySelfRs();
        rs.name = loginMember.getName();
        rs.email = loginMember.getEmail();
        return rs;
    }
}
