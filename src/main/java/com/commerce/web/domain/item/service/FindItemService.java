package com.commerce.web.domain.item.service;

import com.commerce.db.entity.Category;
import com.commerce.db.entity.item.Item;
import com.commerce.web.domain.category.model.rs.FindItemByCategoryRs;
import com.commerce.web.domain.category.repository.CategoryRepository;
import com.commerce.web.domain.item.model.rs.FindItemDetailsRs;
import com.commerce.web.domain.item.repository.ItemQueryRepository;
import com.commerce.web.domain.item.repository.ItemRepository;
import com.commerce.web.global.exception.CannotFindCategoryException;
import com.commerce.web.global.exception.CannotFindItemException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemQueryRepository itemQueryRepository;

    public FindItemByCategoryRs findItemByCategory(Long categoryId) {

        Category findCategory = categoryRepository.findById(categoryId)
            .orElseThrow(CannotFindCategoryException::new);

        List<Item> itemList = itemQueryRepository.findItemList(categoryId);

        return new FindItemByCategoryRs(findCategory.getName(), itemList);

    }

    public FindItemDetailsRs findItemDetails(Long itemId) {

        Item findItem = itemRepository.findById(itemId).orElseThrow(CannotFindItemException::new);
        return Item.toFindItemDetailsRs(findItem);
    }
}
