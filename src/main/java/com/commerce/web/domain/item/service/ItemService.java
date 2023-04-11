package com.commerce.web.domain.item.service;

import com.commerce.db.entity.Category;
import com.commerce.db.entity.attachfile.AttachFile;
import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.book.model.rq.SaveBookRq;
import com.commerce.web.domain.book.service.BookService;
import com.commerce.web.domain.category.service.FindCategoryService;
import com.commerce.web.domain.item.repository.ItemRepository;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.vegetable.model.CreateVegetableRq;
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
    private final BookService bookService;
    private final VegetableService vegetableService;

    public void saveBook(SaveBookRq rq) {
        String fileUid = rq.getFileUid();
        // TODO: 2023-04-11 Attachfile 찾는 로직
        AttachFile attachFile = new AttachFile();

        Category category = findCategoryService.findByIdOrElseThrow(rq.getCategoryId());
        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());

        Item item = Item.create(rq.getName(), rq.getPrice(), rq.getDescription(), attachFile,
            category, member);
        itemRepository.save(item);

        bookService.saveBook(rq, item);
    }

    public void createVegetable(CreateVegetableRq rq) {

        String fileUid = rq.getFileUid();

        AttachFile attachFile = new AttachFile();

        Category category = findCategoryService.findByIdOrElseThrow(rq.getCategoryId());
        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());

        Item item = Item.create(rq.getName(), rq.getPrice(), rq.getDescription(), attachFile,
            category, member);

        itemRepository.save(item);

        vegetableService.createVegetable(rq, item);


    }
}
