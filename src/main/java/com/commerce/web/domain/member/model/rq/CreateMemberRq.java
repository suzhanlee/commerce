package com.commerce.web.domain.member.model.rq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CreateMemberRq {

    @NotBlank
    private String name;

    @NotEmpty
    private String email;

    private String phone;

}
