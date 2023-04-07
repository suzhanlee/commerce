package com.commerce.db.entity.item;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.commerce.db.entity.BaseTimeEntity;
import com.commerce.db.entity.Category;
import com.commerce.db.entity.attachfile.AttachFile;
import com.commerce.db.entity.member.Member;
import com.commerce.web.domain.item.model.rq.CreateItemRq;
import com.commerce.web.domain.item.model.rs.CreateItemRs;
import com.commerce.web.domain.item.model.rs.FindItemDetailsRs;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Item extends BaseTimeEntity {

    //
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

    // 저자, 책번호
    private String author;
    private String isbn;

    // 컴퓨터
    private int ram;
    private String cpu;
    private int ssd;

    // 원산지
    private String origin;

    public void addCategory(Category category) {
        this.category = category;
    }

    public void addMember(Member member) {
        this.member = member;
    }


    public static Item toEntity(CreateItemRq createItemRq) {

        Item item = new Item();
        item.name = createItemRq.getItemName();
        item.price = createItemRq.getPrice();
        item.description = createItemRq.getDescription();
        item.author = createItemRq.getAuthor();
        item.isbn = createItemRq.getIsbn();
        item.cpu = createItemRq.getCpu();
        item.ram = createItemRq.getRam();
        item.ssd = createItemRq.getSsd();
        item.origin = createItemRq.getOrigin();

        return item;
    }

    // of => dto
    // from => entity

    public static FindItemDetailsRs toFindItemDetailsRs(Item item) {

        return FindItemDetailsRs
            .builder()
            .itemId(item.getId())
            .categoryName(item.getCategory().getName())
            .name(item.getName())
            .price(item.getPrice())
            .description(item.getDescription())
            .author(item.getAuthor())
            .isbn(item.getIsbn())
            .ram(item.getRam())
            .cpu(item.getCpu())
            .ssd(item.getSsd())
            .origin(item.getOrigin())
            .build();

    }

    public static CreateItemRs toCreateItemRs(Item item, Long memberId) {

        return CreateItemRs
            .builder()
            .memberId(memberId)
            .categoryName(item.getCategory().getName())
            .itemId(item.getId())
            .categoryName(item.getCategory().getName())
            .itemName(item.getName())
            .price(item.getPrice())
            .description(item.getDescription())
            .author(item.getAuthor())
            .isbn(item.getIsbn())
            .ram(item.getRam())
            .cpu(item.getCpu())
            .ssd(item.getSsd())
            .origin(item.getOrigin())
            .build();

    }


}