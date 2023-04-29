package com.commerce.web.domain.vegetable.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.enums.item.Vegetable;
import com.commerce.web.domain.item.service.ItemService;
import com.commerce.web.domain.vegetable.model.CreateVegetableRq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VegetableService {

    private final ItemService itemService;

    public void saveVegetable(CreateVegetableRq rq) {

        Long itemId = itemService.saveItem(rq.getItem());
        Item item = itemService.findItemOrElseThrow(itemId);

        Vegetable vegetable = Vegetable.createVegetable(rq.getOrigin());

        item.addVegetable(vegetable);
    }
}
