package com.commerce.db.entity.educationalhistory;

import lombok.Getter;

@Getter
public enum EduHistoryType {

    HIGHSCHOOL("고등학교"), UNIVERSITY("대학교"), GRADUATESCHOOL("대학원");

    private String name;

    EduHistoryType(String name) {
        this.name = name;
    }
}
