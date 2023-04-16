package com.commerce.web.domain.item.model.rq;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteItemRq {

    @NotNull
    private Long itemId;

}
