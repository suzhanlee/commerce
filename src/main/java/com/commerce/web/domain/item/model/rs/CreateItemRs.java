package com.commerce.web.domain.item.model.rs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateItemRs {

    @NotNull
    private Long memberId;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String itemName;
    @NotBlank
    private Long itemId;
    @NotBlank
    private Long price;
    @NotBlank
    private String description;
    @NotBlank
    private String image;

    private String author;
    private String isbn;

    private int ram;
    private String cpu;
    private int ssd;

    private String origin;



}
