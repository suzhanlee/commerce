package com.commerce.web.domain.category.controller;

import com.commerce.web.domain.category.model.rq.SaveCategoryRq;
import com.commerce.web.domain.category.service.CategoryService;
import com.commerce.web.domain.category.service.FindCategoryService;
import com.commerce.web.global.path.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final FindCategoryService findCategoryService;
    private final CategoryService categoryService;

    @PostMapping(ApiPath.CATEGORY)
    public void saveCategory(@Validated @RequestBody SaveCategoryRq rq) {
        categoryService.saveCategory(rq);

    }


}
