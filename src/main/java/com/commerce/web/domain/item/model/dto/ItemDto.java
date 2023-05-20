package com.commerce.web.domain.item.model.dto;

import com.commerce.db.entity.item.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ItemDto {

    @Schema(name = "상품명")
    private String name;

    @Schema(name = "가격")
    private Long price;

    @Schema(name = "상품설명")
    private String description;

    @Schema(name = "카테고리 ID")
    private Long categoryId;

    public static ItemDto createItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.name = item.getName();
        itemDto.price = item.getPrice();
        itemDto.description = item.getDescription();
        itemDto.categoryId = item.getCategory().getId();

        return itemDto;

    }

}
