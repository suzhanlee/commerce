package com.commerce.web.domain.rating.controller;

import com.commerce.web.domain.rating.model.rq.CreateRatingRq;
import com.commerce.web.domain.rating.service.RatingService;
import com.commerce.web.global.path.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping(ApiPath.RATING)
    public void createRating(CreateRatingRq rq) {

        ratingService.createRating(rq);

    }





}
