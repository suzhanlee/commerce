package com.commerce.web.domain.book.model.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SaveBookRq {

    @Schema(name = "책 제목")
    private String name;

    @Schema(name = "가격")
    private Long price;

    @Schema(name = "설명")
    private String description;

//    @Schema(name = "썸네일 파일 UID")
//    private String fileUid;

    @Schema(name = "카테고리 ID")
    private Long categoryId;

    @Schema(name = "멤버 ID")
    private Long memberId;

    @Schema(name = "저자")
    private String author;

    @Schema(name = "책번호")
    private String isbn;
    
}
