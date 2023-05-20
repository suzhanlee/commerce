package com.commerce.web.global.security;

import com.commerce.db.enums.auth.ClientType;
import lombok.Getter;

@Getter
public class MemberContext {

    private final String email;
    private final ClientType clientType;

    private MemberContext(String email, ClientType clientType) {
        this.email = email;
        this.clientType = clientType;
    }

    public static MemberContext create(String email, ClientType clientType) {
        return new MemberContext(email, clientType);
    }
}
