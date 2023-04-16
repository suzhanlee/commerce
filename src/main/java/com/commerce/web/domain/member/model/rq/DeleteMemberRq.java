package com.commerce.web.domain.member.model.rq;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteMemberRq {

    @NotNull
    private Long memberId;
}
