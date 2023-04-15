package com.commerce.web.domain.vegetable.conroller;

import com.commerce.web.domain.item.service.ItemService;
import com.commerce.web.domain.vegetable.model.CreateVegetableRq;
import com.commerce.web.global.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "채소 컨트롤러")
public class VegetableController {

    private final ItemService itemService;

    @Operation(summary = "채소 생성", description = "채소 생성")
    @PostMapping(ApiPath.VEGETABLE)
    public void createVegetable(@Validated @RequestBody CreateVegetableRq rq) {

//        itemService.createVegetable(rq);

    }
}
