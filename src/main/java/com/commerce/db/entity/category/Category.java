package com.commerce.db.entity.category;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.commerce.db.entity.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> itemList = new ArrayList<>();

    public static Category createCategoryByName(String name) {
        Category category = new Category();
        category.name = name;
        return category;
    }

}
