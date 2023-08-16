package com.commerce.db.entity.educationalhistory;

public enum GraduateType {

    EXPECTED_GRADUATE("졸업예정"),
    LEAVE_OF_ABSENCE("휴학"),
    GRADUATE("졸업"),
    COMPLETION("수료");

    private String name;

    GraduateType(String name) {
        this.name = name;
    }
}
