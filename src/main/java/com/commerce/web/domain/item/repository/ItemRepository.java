package com.commerce.web.domain.item.repository;

import com.commerce.db.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
