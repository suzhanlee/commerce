package com.commerce.web.domain.vegetable.model;

import com.commerce.web.domain.item.model.dto.ItemDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateVegetableRq {


    @Schema(name = "아이템 정보")
    private ItemDto item;

    @Schema(name = "회원 아이디")
    private Long memberId;

    @Schema(name = "원산지")
    private String origin;

}
