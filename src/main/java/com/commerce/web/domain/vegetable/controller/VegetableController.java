package com.commerce.web.domain.vegetable.controller;

import com.commerce.web.domain.vegetable.model.CreateVegetableRq;
import com.commerce.web.domain.vegetable.service.VegetableService;
import com.commerce.web.global.path.ApiPath;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "채소 api")
public class VegetableController {

    private final VegetableService vegetableService;

    @Operation(summary = "채소 생성")
    @PostMapping(ApiPath.VEGETABLE)
    public void saveVegetable(CreateVegetableRq rq) {

        vegetableService.saveVegetable(rq);

    }

}
