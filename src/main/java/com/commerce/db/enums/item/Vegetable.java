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

    public Vegetable(String origin) {
        this.origin = origin;
    }
}
