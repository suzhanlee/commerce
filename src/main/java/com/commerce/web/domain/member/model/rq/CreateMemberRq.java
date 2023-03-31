package com.commerce.web.domain.member.model.rq;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMemberRq {

    @NotBlank
    private String name;
}
