package com.commerce.web.domain.auth.model.rq;

import com.commerce.db.enums.auth.ClientType;
import lombok.Getter;

@Getter
public class LoginMemberRq {

    private String username;
    private String password;
    private String email;
    private ClientType clientType;


}
