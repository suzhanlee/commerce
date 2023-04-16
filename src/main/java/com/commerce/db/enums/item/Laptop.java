package com.commerce.db.enums.item;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Laptop {

    private String cpu;
    private Long ram;
    private Long ssd;

    public Laptop() {
    }

    public Laptop(String cpu, Long ram, Long ssd) {
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
    }
}
