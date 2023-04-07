package com.commerce.web.domain.item.model.rs;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FindItemDetailsRs {

    private Long itemId;
    private String categoryName;
    private String name;

    private int price;
    private String description;
    private String image;
    private String author;
    private String isbn;
    private int ram;
    private String cpu;
    private int ssd;
    private String origin;

}
