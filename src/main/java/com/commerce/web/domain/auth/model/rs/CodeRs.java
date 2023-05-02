package com.commerce.web.domain.auth.model.rs;

public class CodeRs {

    private String code;

    public static CodeRs createCodeRs(String code) {
        CodeRs codeRs = new CodeRs();
        codeRs.code = code;
        return codeRs;
    }

}
