package com.commerce.web.domain.item.controller;

import com.commerce.web.domain.item.model.rq.DeleteItemRq;
import com.commerce.web.domain.item.model.rq.SaveItemRq;
import com.commerce.web.domain.item.service.ItemService;
import com.commerce.web.global.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Tag(name = "상품 컨트롤러")
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "상품 생성")
    @PostMapping(ApiPath.ITEM)
    public void saveItem(@Validated @RequestBody SaveItemRq rq) {
        itemService.saveItem(rq);
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping(ApiPath.ITEM)
    public void deleteItem(@Validated @RequestBody DeleteItemRq rq) {

        itemService.deleteItem(rq);

    }
}
