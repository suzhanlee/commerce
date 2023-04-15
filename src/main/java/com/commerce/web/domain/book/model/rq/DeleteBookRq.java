package com.commerce.web.domain.book.model.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class DeleteBookRq {

    @Schema(name = "책 아이디")
    private Long bookId;

}
