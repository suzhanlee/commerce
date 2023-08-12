package com.commerce.web.domain.item.service;

import com.commerce.db.entity.Category;
import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.payment.Payment;
import com.commerce.web.domain.category.service.FindCategoryService;
import com.commerce.web.domain.item.model.rq.CreateItemRq;
import com.commerce.web.domain.item.model.rq.FindItemRq;
import com.commerce.web.domain.item.model.rq.ItemRq;
import com.commerce.web.domain.item.model.rq.PurchaseItemRq;
import com.commerce.web.domain.item.model.rs.FindItemRs;
import com.commerce.web.domain.item.repository.ItemRepository;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.payment.repository.PaymentRepository;
import com.commerce.web.domain.recommend.service.RecommendationService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final FindCategoryService findCategoryService;
    private final FindMemberService findMemberService;
    private final FindItemService findItemService;
    private final PaymentRepository paymentRepository;
    private final RecommendationService recommendationService;

    public void createItem(CreateItemRq rq) {

        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());
        Category category = findCategoryService.findByIdOrElseThrow(rq.getCategoryId());

        Item item;

        if (ObjectUtils.isEmpty(rq.getBook())) {
            item = Item.create(rq.getItemName(), rq.getPrice(), rq.getDescription(), category,
                member,
                rq.getVegetable(), null, null);

        } else if (ObjectUtils.isEmpty(rq.getLaptop())) {
            item = Item.create(rq.getItemName(), rq.getPrice(), rq.getDescription(), category,
                member,
                null, rq.getLaptop(), null);

        } else if (ObjectUtils.isEmpty(rq.getVegetable())) {
            item = Item.create(rq.getItemName(), rq.getPrice(), rq.getDescription(), category,
                member,
                null, null, rq.getBook());

        } else {
            throw new RuntimeException("상품을 만들 수 없습니다!");
        }

        itemRepository.save(item);

    }

    public void purchaseItem(PurchaseItemRq rq) {

        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());

        int totalPrice = 0;

        for (ItemRq itemRq : rq.getItemRqList()) {
            totalPrice += findItemService.findItemByIdOrElseThrow(itemRq.getItemId()).getPrice() * itemRq.getQuantity();
        }

        if (member.getBalance() < totalPrice) {
            throw new RuntimeException("잔액이 부족합니다");
        } else {
            for (ItemRq itemRq : rq.getItemRqList()) {
                Long quantity = itemRq.getQuantity();
                Item item = findItemService.findItemByIdOrElseThrow(
                    itemRq.getItemId());
                Payment payment = Payment.createPayment(member, item, quantity);
                paymentRepository.save(payment);
            }

        }

    }

    public FindItemRs findItem(FindItemRq rq) {

        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());
        Item item = findItemService.findItemByIdOrElseThrow(rq.getItemId());

        List<Item> items = recommendationService.recommendItems(member);

        return new FindItemRs(item, items);

    }

    public void saveItem1() {

        List<Item> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {

            Item item = Item.create("책" + i, 10000L);
            list.add(item);

        }

        long before = System.currentTimeMillis();
        list.parallelStream().forEach(itemRepository::save);

        long after = System.currentTimeMillis();

        System.out.println("소요시간1 " + String.valueOf(after - before));
    }

    public void saveItem2() {

        List<Item> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {

            Item item = Item.create("책" + i, 10000L);
            list.add(item);
        }

        long before = System.currentTimeMillis();
        itemRepository.saveAll(list);
        long after = System.currentTimeMillis();

        System.out.println("소요시간2 " + String.valueOf(after - before));


    }




}
