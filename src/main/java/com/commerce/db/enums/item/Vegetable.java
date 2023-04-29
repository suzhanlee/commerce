package com.commerce.db.enums.item;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Vegetable {

    private String origin;

    public Vegetable() {

    }

    public static Vegetable createVegetable(String origin) {
        Vegetable vegetable = new Vegetable();
        vegetable.origin = origin;
        return vegetable;
    }


}
