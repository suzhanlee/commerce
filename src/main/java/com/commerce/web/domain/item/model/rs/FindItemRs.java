package com.commerce.web.domain.item.model.rs;

import com.commerce.db.entity.item.Item;
import com.commerce.web.domain.item.model.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class FindItemRs {

    private ItemDto itemDto;

    private List<ItemDto> itemDtos = new ArrayList<>();

    public FindItemRs(Item item, List<Item> items) {
        this.itemDto = ItemDto.createItemDto(item);
        this.itemDtos = items.stream().map(ItemDto::createItemDto).collect(Collectors.toList());
    }
}
