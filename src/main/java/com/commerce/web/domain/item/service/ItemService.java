package com.commerce.web.domain.item.service;

import com.commerce.db.entity.Category;
import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.member.Member2;
import com.commerce.web.domain.book.model.rq.DeleteBookRq;
import com.commerce.web.domain.book.model.rq.SaveBookRq;
import com.commerce.web.domain.category.service.FindCategoryService;
import com.commerce.web.domain.item.repository.ItemRepository;
import com.commerce.web.domain.member.model.rs.FindMember2ByIdRs;
import com.commerce.web.domain.member.service.FindMember2Service;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.vegetable.service.VegetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final FindCategoryService findCategoryService;
    private final FindMemberService findMemberService;
//    private final BookService bookService;
    private final VegetableService vegetableService;
    private final FindMember2Service findMember2Service;

    public void saveBook(SaveBookRq rq) {
//        String fileUid = rq.getFileUid();
//        // TODO: 2023-04-11 Attachfile 찾는 로직
//        AttachFile attachFile = new AttachFile();

        Long categoryId = rq.getCategoryId();

        Category category = findCategoryService.findByIdOrElseThrow(rq.getCategoryId());
        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());
        Member2 member2 = findMember2Service.findByIdOrElseThrow(rq.getMemberId());

        Item item = Item.create(rq.getName(), rq.getPrice(), rq.getDescription(),
            category, member, member2);
        itemRepository.save(item);

//        bookService.saveBook(rq, item);
    }

//    public void createVegetable(CreateVegetableRq rq) {
//
//        String fileUid = rq.getFileUid();
//
//        AttachFile attachFile = new AttachFile();
//
//        Category category = findCategoryService.findByIdOrElseThrow(rq.getCategoryId());
//        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());
//
//        Item item = Item.create(rq.getName(), rq.getPrice(), rq.getDescription(), attachFile,
//            category, member);
//
//        itemRepository.save(item);
//
//        vegetableService.createVegetable(rq, item);
//
//
//    }

//    public void deleteBook(DeleteBookRq rq) {
//        bookService.deleteBook(rq);
//    }

}
