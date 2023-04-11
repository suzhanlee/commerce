package com.commerce.web.domain.category.model.rs;

import com.commerce.db.entity.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class FindItemByCategoryRs {

    private String categoryName;
    private List<ItemDto> itemDtoList = new ArrayList<>();

    public FindItemByCategoryRs(String categoryName, List<Item> Items) {
        this.categoryName = categoryName;
        this.itemDtoList = Items.stream().map(ItemDto::new).collect(Collectors.toList());
    }

    @Getter
    static class ItemDto {

        private Long itemId;
        private String name;
        private Long price;
        private String description;

        private String author;
        private String isbn;
        private int ram;
        private String cpu;
        private int ssd;
        private String origin;

        public ItemDto(Item item) {
            this.itemId = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
            this.description = item.getDescription();
            this.author = item.getAuthor();
            this.isbn = item.getIsbn();
            this.ram = item.getRam();
            this.cpu = item.getCpu();
            this.ssd = item.getSsd();
            this.origin = item.getOrigin();
        }
    }
}

