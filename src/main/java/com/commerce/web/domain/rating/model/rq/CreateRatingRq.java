package com.commerce.web.domain.rating.model.rq;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateRatingRq {

    @Size(min = 0, max = 5)
    private Integer rating;
    private Long itemId;
    private Long memberId;

}
