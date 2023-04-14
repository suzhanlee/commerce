package com.commerce.db.enums.member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberRole {
    ROLE_SELLER("판매자"),
    ROLE_CUSTOMER("구매자");

    private final String name;
}
