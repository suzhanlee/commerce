package com.commerce.web.domain.category.repository;

import com.commerce.db.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
