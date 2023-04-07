package com.commerce.web.domain.item.model.rs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FindItemDetailsRs {

    @Schema(description = "상품 아이디")
    private Long itemId;

    @Schema(description = "카테고리 이름")
    private String categoryName;

    @Schema(description = "상품 이름")
    private String name;

    @Schema(description = "상품 가격")
    private int price;

    @Schema(description = "상품 설명")
    private String description;

    @Schema(description = "상품 이미지")
    private String image;

    @Schema(description = "작가 이름")
    private String author;

    @Schema(description = "책 번호")
    private String isbn;

    @Schema(description = "램 용량")
    private int ram;

    @Schema(description = "cpu")
    private String cpu;

    @Schema(description = "ssd 용량")
    private int ssd;

    @Schema(description = "원산지")
    private String origin;

}
