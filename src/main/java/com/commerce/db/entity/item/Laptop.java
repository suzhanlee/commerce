package com.commerce.db.entity.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Laptop extends Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer ram;

    private String cpu;

    private Integer ssd;

    public static Laptop create(Integer ram, String cpu, Integer ssd) {
        Laptop laptop = new Laptop();
        laptop.ram = ram;
        laptop.cpu = cpu;
        laptop.ssd = ssd;
        return laptop;
    }
}
