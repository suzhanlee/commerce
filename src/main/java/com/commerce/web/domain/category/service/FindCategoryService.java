package com.commerce.web.domain.category.service;

import com.commerce.db.entity.Category;
import com.commerce.web.domain.category.repository.CategoryRepository;
import com.commerce.web.global.exception.CannotFindCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindCategoryService {

    private final CategoryRepository categoryRepository;


    public Category findByIdOrElseThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CannotFindCategoryException::new);
    }
}
