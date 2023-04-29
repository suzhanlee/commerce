package com.commerce.web.domain.item.service;

import com.commerce.db.entity.category.Category;
import com.commerce.db.entity.item.Item;
import com.commerce.web.domain.category.service.FindCategoryService;
import com.commerce.web.domain.item.model.dto.ItemDto;
import com.commerce.web.domain.item.model.rq.DeleteItemRq;
import com.commerce.web.domain.item.repository.ItemRepository;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.global.exception.CannotFindItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final FindMemberService findMemberService;
    private final FindCategoryService findCategoryService;

//    public void saveBook(SaveBookRq rq) {
////        String fileUid = rq.getFileUid();
////        // TODO: 2023-04-11 Attachfile 찾는 로직
////        AttachFile attachFile = new AttachFile();
//
//        Long categoryId = rq.getCategoryId();
//
//        Category category = findCategoryService.findByIdOrElseThrow(rq.getCategoryId());
//        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());
//        Member2 member2 = findMember2Service.findByIdOrElseThrow(rq.getMemberId());
//
//        Item item = Item.create(rq.getName(), rq.getPrice(), rq.getDescription(),
//            category, member, member2);
//        itemRepository.save(item);
//
////        bookService.saveBook(rq, item);
//    }

    public Long saveItem(ItemDto dto) {

        Category findCategory = findCategoryService.findByIdOrElseThrow(dto.getCategoryId());

        Item item = Item.create(dto.getName(), dto.getPrice(), dto.getDescription(),
            findCategory);

        itemRepository.save(item);

        return item.getId();
    }

    public Item findItemOrElseThrow(Long itemId) {

        return itemRepository.findById(itemId).orElseThrow(CannotFindItemException::new);
    }


    public void deleteItem(DeleteItemRq rq) {
        Item item = findItemOrElseThrow(rq.getItemId());
        itemRepository.delete(item);
    }

}
