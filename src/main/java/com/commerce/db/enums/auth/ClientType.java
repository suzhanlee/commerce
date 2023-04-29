package com.commerce.db.enums.auth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ClientType {

    KAKAO("카카오"),
    GOOGLE("구글");

    private final String name;
}
