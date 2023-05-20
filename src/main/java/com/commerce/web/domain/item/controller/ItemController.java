package com.commerce.web.domain.item.controller;

import com.commerce.web.domain.item.model.rq.CreateItemRq;
import com.commerce.web.domain.item.model.rq.FindItemRq;
import com.commerce.web.domain.item.model.rq.PurchaseItemRq;
import com.commerce.web.domain.item.model.rs.FindItemRs;
import com.commerce.web.domain.item.service.ItemService;
import com.commerce.web.global.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "아이템 생성")
    @PostMapping(ApiPath.ITEM)
    public void createItem(@Validated @RequestBody CreateItemRq rq){
        itemService.createItem(rq);
    }

    @Operation(summary = "아이템 구매")
    @PostMapping(ApiPath.ITEM_PURCHASE)
    public void purchaseItem(@Validated @RequestBody PurchaseItemRq rq){
        itemService.purchaseItem(rq);
    }

    @Operation(summary = "아이템 조회")
    @GetMapping(ApiPath.ITEM)
    public FindItemRs findItem(@Validated @RequestBody FindItemRq rq){
        return itemService.findItem(rq);
    }


}
