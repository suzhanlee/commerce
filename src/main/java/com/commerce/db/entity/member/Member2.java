package com.commerce.db.entity.member;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.commerce.db.entity.item.Item;
import com.commerce.db.enums.member.MemberRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Member2 {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(fetch = LAZY, mappedBy = "member2", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Item> itemList = new ArrayList<>();

    public static Member2 createSeller(String name) {
        Member2 member = new Member2();
        member.name = name;
        return member;
    }

}
