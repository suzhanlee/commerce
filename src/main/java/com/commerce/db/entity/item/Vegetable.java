package com.commerce.db.entity.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Vegetable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private String origin;  // 원산지

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static Vegetable create(String origin, Item item) {
        Vegetable vegetable = new Vegetable();
        vegetable.origin = origin;
        vegetable.item = item;
        return vegetable;
    }
}
