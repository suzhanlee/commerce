package com.commerce.db.entity.rating;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.item.Laptop;
import com.commerce.db.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Getter
public class Rating {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Size(min = 0, max = 5)
    private Integer rating;

    public static Rating createRating(Member member, Item item, Integer rating) {
        Rating instance = new Rating();
        instance.member = member;
        instance.item = item;
        instance.rating = rating;
        return instance;
    }
}
