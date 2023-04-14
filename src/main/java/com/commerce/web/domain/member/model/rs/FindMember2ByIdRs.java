package com.commerce.web.domain.member.model.rs;

import com.commerce.db.entity.member.Member2;
import lombok.Getter;

@Getter
public class FindMember2ByIdRs {

    private Long id;
    private String name;

    public static FindMember2ByIdRs create(Member2 member) {
        FindMember2ByIdRs rs = new FindMember2ByIdRs();
        rs.id = member.getId();
        rs.name = member.getName();
        return rs;
    }
}
