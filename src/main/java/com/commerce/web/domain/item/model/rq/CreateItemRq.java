package com.commerce.web.domain.item.model.rq;

import com.commerce.db.entity.item.Book;
import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.item.Laptop;
import com.commerce.db.entity.item.Vegetable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateItemRq {

    @NotNull
    private Long memberId;

    @NotNull
    private Long categoryId;

    @NotEmpty
    private String itemName;

    @NotNull
    private Long price;

    @NotEmpty
    private String description;

    @NotEmpty
    private String image;

    private Vegetable vegetable;
    private Book book;
    private Laptop laptop;

//    public static Item createItemByRq(CreateItemRq rq) {
//
//        Item item = new Item();
//        item.description = rq.getDescription(); // 이런거 안되는데 그럼 entity쪽에 만들어야 할까? 아니면
//        // 예전에 만든거처럼 BUilder를 사용해야할까?
//
//
//    }

}
