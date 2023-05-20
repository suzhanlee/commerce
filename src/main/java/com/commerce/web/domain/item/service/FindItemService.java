package com.commerce.web.domain.item.service;

import com.commerce.db.entity.item.Item;
import com.commerce.web.domain.item.repository.ItemRepository;
import com.commerce.web.global.exception.CannotFindItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindItemService {

    private final ItemRepository itemRepository;

    public Item findItemByIdOrElseThrow(Long itemId) {

        return itemRepository.findById(itemId).orElseThrow(CannotFindItemException::new);

    }

}
