package com.commerce.web.domain.item.model.rq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateItemRq {

    @NotNull
    private Long memberId;
    @NotNull
    private Long categoryId;
    @NotEmpty
    private String itemName;
    @NotNull
    private Long price;
    @NotEmpty
    private String description;
    @NotEmpty
    private String image;

    private String author;
    private String isbn;

    private int ram;
    private String cpu;
    private int ssd;

    private String origin;

}
