package com.commerce.db.entity.member;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Member2 {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

//    @OneToMany(fetch = LAZY, mappedBy = "member2", cascade = CascadeType.PERSIST, orphanRemoval = true)
//    private List<Item> itemList = new ArrayList<>();

    public static Member2 createSeller(String name) {
        Member2 member = new Member2();
        member.name = name;
        return member;
    }

}
