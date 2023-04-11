package com.commerce.db.entity.item;

import com.commerce.db.entity.BaseTimeEntity;
import com.commerce.db.entity.Category;
import com.commerce.db.entity.attachfile.AttachFile;
import com.commerce.db.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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

    @OneToOne(fetch = FetchType.LAZY)
    private AttachFile thumbNail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // 관리자, 구매자 구분
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Item create(String name, Long price, String description, AttachFile thumbNail, Category category, Member member) {
        Item item = new Item();
        item.name = name;
        item.price = price;
        item.description = description;
        item.thumbNail = thumbNail;
        item.category = category;
        item.member = member;
        return item;
    }
}