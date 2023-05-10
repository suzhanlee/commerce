package com.commerce.db.entity.member;

import com.commerce.db.entity.item.Item;
import com.commerce.db.enums.auth.ClientType;
import com.commerce.db.enums.member.MemberRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(nullable = false)
    @Enumerated(STRING)
    private ClientType clientType;

    @Column(nullable = false)
    @Enumerated(STRING)
    private MemberRole memberRole;

    private String password;

    @OneToMany(fetch = LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemList = new ArrayList<>();

    public static Member createSeller(String name, String email, String phone) {
        Member member = new Member();
        member.name = name;
        member.email = email;
        member.phone = phone;
        member.memberRole = MemberRole.ROLE_SELLER;
        return member;
    }

    public static Member createCustomer(String name, String email, ClientType clientType) {
        Member member = new Member();
        member.name = name;
        member.email = email;
        member.clientType = clientType;
        member.memberRole = MemberRole.ROLE_CUSTOMER;
        return member;
    }

    public static Member createMemberByUsernameAndEmail(String username, String email) {
        Member member = new Member();
        member.name = username;
        member.email = email;
        member.memberRole = MemberRole.ROLE_SELLER;
        return member;
    }

}
