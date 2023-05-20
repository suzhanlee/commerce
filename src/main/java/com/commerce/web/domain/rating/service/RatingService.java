package com.commerce.web.domain.rating.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.rating.Rating;
import com.commerce.web.domain.item.service.FindItemService;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.rating.model.rq.CreateRatingRq;
import com.commerce.web.domain.rating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
    private RatingRepository ratingRepository;
    private FindMemberService findMemberService;
    private FindItemService findItemService;

    public void createRating(CreateRatingRq rq) {

        Member member = findMemberService.findByIdOrElseThrow(rq.getMemberId());
        Item item = findItemService.findItemByIdOrElseThrow(rq.getItemId());

        // 이거랑 rating으로만 Rating을 만들고 member와 item을 set해주는거랑 어떤게 더 좋을까?
        Rating rating = Rating.createRating(member, item, rq.getRating());

        ratingRepository.save(rating);
    }




}
