package com.commerce.db.entity.item;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.commerce.db.entity.BaseTimeEntity;
import com.commerce.db.entity.Category;
import com.commerce.db.entity.member.Member;
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
import org.springframework.lang.Nullable;

@Entity
@Getter
@NoArgsConstructor
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
    private Vegetable vegetable;
    @Embedded
    private Laptop laptop;
    @Embedded
    private Book book;

    public static Item create(String name, Long price, String description, Category category,
        Member member, @Nullable Vegetable vegetable, @Nullable Laptop laptop, @Nullable Book book) {
        Item item = new Item();
        item.name = name;
        item.price = price;
        item.description = description;
        item.category = category;
        item.member = member;
        item.vegetable = vegetable;
        item.laptop = laptop;
        item.book = book;
        return item;
    }
}