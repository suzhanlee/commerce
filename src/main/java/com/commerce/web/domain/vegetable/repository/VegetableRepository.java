package com.commerce.web.domain.vegetable.repository;

import com.commerce.db.entity.item.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VegetableRepository extends JpaRepository<Vegetable, Long> {

}
