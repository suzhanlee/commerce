package com.commerce.web.global.exception.handler;

import static com.commerce.web.global.path.ApiPath.ACCESS_DENIED;

import com.commerce.web.global.exception.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationExceptionController {

    @GetMapping(ACCESS_DENIED)
    public void accessDenyException() {

        throw new AccessDeniedException();
    }

}
