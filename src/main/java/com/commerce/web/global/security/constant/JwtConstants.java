package com.commerce.web.global.security.constant;

public class JwtConstants {

    public static final String EMAIL = "email";
    public static final String CLIENT_TYPE = "clientType";
    public static final long TOKEN_EXPIRE_TIME = 1000 * 10;
    public static final String SIGN_IN_URL = "http://localhost:8080/api/signin";
    public static final String SIGN_UP_URL = "http://localhost:8080/api/signup";

    public static final String APPLICATION_JSON = "application/json; charset=utf-8";

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";


}
