package com.commerce.web.domain.category.service;

import com.commerce.db.entity.category.Category;
import com.commerce.web.domain.category.model.rq.SaveCategoryRq;
import com.commerce.web.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void saveCategory(SaveCategoryRq rq) {

        Category category = Category.createCategoryByName(rq.getCategoryName());

        categoryRepository.save(category);

    }
}
