package com.commerce.web.domain.category.model.rq;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SaveCategoryRq {

    @Schema(name = "카테고리 이름")
    private String categoryName;

}
