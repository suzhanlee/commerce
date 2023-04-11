package com.commerce.web.domain.category.model.rs;

import com.commerce.db.entity.item.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindItemByCategoryRs {

    private String categoryName;
    private List<ItemDto> itemDtoList = new ArrayList<>();

    public FindItemByCategoryRs(String categoryName, List<Item> items) {
        this.categoryName = categoryName;
        this.itemDtoList = items.stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
    }

    @Getter
    static class ItemDto {

        private Long itemId;
        private String name;
        private Long price;
        private String description;

        public ItemDto(Item item) {
            this.itemId = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
            this.description = item.getDescription();
        }
    }
}

