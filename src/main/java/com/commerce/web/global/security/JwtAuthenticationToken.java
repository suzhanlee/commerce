//package com.commerce.web.global.security;
//
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//
//public class JwtAuthenticationToken extends AbstractAuthenticationToken {
//
//    private final String username;
//    private String password;
//
//    public JwtAuthenticationToken(String username, String password) {
//        super(null);
//        setAuthenticated(false);
//        this.username = username;
//        this.password = password;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return this.password;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return this.username;
//    }
//
//}
