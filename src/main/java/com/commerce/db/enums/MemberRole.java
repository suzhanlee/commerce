package com.commerce.db.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberRole {
    ROLE_ADMIN("관리자"),
    ROLE_NORMAL("일반회원");

    private final String name;

}
