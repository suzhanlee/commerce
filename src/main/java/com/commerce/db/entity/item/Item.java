package com.commerce.db.entity.item;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.commerce.db.entity.BaseTimeEntity;
import com.commerce.db.entity.category.Category;
import com.commerce.db.entity.member.Member;
import com.commerce.db.enums.item.Book;
import com.commerce.db.enums.item.Laptop;
import com.commerce.db.enums.item.Vegetable;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private Long price;
    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // 관리자, 구매자 구분
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Book book;

    @Embedded
    private Laptop laptop;

    @Embedded
    private Vegetable vegetable;

    // 매개변수로 rq 받기 vs 아래처럼 하나씩 넣기 뭐가 다르지?
    public static Item create(String name, Long price, String description, Category category,
        Member member, Book book, Laptop laptop, Vegetable vegetable) {
        Item item = new Item();
        item.name = name;
        item.price = price;
        item.description = description;
        item.category = category;
        item.member = member;
        item.book = book;
        item.laptop = laptop;
        item.vegetable = vegetable;

        return item;
    }
}