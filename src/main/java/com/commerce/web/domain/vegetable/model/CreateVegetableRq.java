package com.commerce.web.domain.vegetable.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateVegetableRq {

    @Schema(name = "채소 이름")
    private String name;

    @Schema(name = "가격")
    private Long price;

    @Schema(name = "채소 설명")
    private String description;

    @Schema(name = "이미지 고유 번호")
    private String fileUid;

    @Schema(name = "카테고리 아이디")
    private Long categoryId;

    @Schema(name = "회원 아이디")
    private Long memberId;

    @Schema(name = "원산지")
    private String origin;

}
