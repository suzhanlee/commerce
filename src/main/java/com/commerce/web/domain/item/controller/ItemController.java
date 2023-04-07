package com.commerce.web.domain.item.controller;

import com.commerce.web.domain.category.model.rs.FindItemByCategoryRs;
import com.commerce.web.domain.item.model.rq.CreateItemRq;
import com.commerce.web.domain.item.model.rs.CreateItemRs;
import com.commerce.web.domain.item.model.rs.FindItemDetailsRs;
import com.commerce.web.domain.item.service.FindItemService;
import com.commerce.web.domain.item.service.ItemService;
import com.commerce.web.global.path.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final FindItemService findItemService;

    @PostMapping(ApiPath.ITEM)
    public ResponseEntity<?> createItem(@RequestBody @Validated CreateItemRq createItemRq) {

        CreateItemRs response = itemService.createItem(createItemRq);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiPath.ITEM_CATEGORY_ID)
    public ResponseEntity<?> findItemByCategory(@PathVariable("category-id") Long categoryId) {

        FindItemByCategoryRs response = findItemService.findItemByCategory(categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiPath.ITEM)
    public ResponseEntity<?> findItemDetails(@RequestParam Long itemId) {

        FindItemDetailsRs response = findItemService.findItemDetails(itemId);
        return ResponseEntity.ok(response);
    }

}
