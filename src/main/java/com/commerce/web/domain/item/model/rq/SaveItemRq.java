package com.commerce.web.domain.item.model.rq;

import com.commerce.db.enums.item.Book;
import com.commerce.db.enums.item.Laptop;
import com.commerce.db.enums.item.Vegetable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class SaveItemRq {

    @NotNull
    @Schema(name = "판매자 아이디")
    private Long memberId;

    @NotNull
    @Schema(name = "카테고리 아이디")
    private Long categoryId;

    @NotEmpty
    @Schema(name = "상품 아이디")
    private String itemName;

    @NotNull
    @Schema(name = "상품 가격")
    private Long price;

    @NotEmpty
    @Schema(name = "상품 설명")
    private String description;

    @NotEmpty
    @Schema(name = "상품 이미지")
    private String image;

    @Schema(name = "책 정보")
    private Book book;

//    @Schema(name = "작가 이름")
//    private String author;
//
//    @Schema(name = "책 번호")
//    private String isbn;

    @Schema(name = "노트북 정보")
    private Laptop laptop;

//    @Schema(name = "cpu")
//    private String cpu;
//
//    @Schema(name = "ram")
//    private Long ram;
//
//    @Schema(name = "ssd")
//    private Long ssd;

    @Schema(name = "채소 정보")
    private Vegetable vegetable;

//    @Schema(name = "원산지")
//    private String origin;

}
