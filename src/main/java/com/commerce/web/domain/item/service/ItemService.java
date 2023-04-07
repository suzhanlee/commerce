package com.commerce.web.domain.item.service;

import com.commerce.db.entity.Category;
import com.commerce.db.entity.Member;
import com.commerce.db.entity.item.Item;
import com.commerce.web.domain.category.repository.CategoryRepository;
import com.commerce.web.domain.item.model.rq.CreateItemRq;
import com.commerce.web.domain.item.model.rs.CreateItemRs;
import com.commerce.web.domain.item.repository.ItemRepository;
import com.commerce.web.domain.member.repository.MemberRepository;
import com.commerce.web.global.exception.CannotFindCategoryException;
import com.commerce.web.global.exception.CannotFindMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public CreateItemRs createItem(CreateItemRq createItemRq) {

        Member findMember = memberRepository.findById(createItemRq.getMemberId()).orElseThrow(
            CannotFindMemberException::new);

        Item item = Item.toEntity(createItemRq);

        item.addMember(findMember);

        Category findCategory = categoryRepository.findById(createItemRq.getCategoryId())
            .orElseThrow(CannotFindCategoryException::new);

        item.addCategory(findCategory);
        itemRepository.save(item);

        return Item.toCreateItemRs(item, findMember.getId());
    }
}
