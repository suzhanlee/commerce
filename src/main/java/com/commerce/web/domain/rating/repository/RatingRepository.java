package com.commerce.web.domain.rating.repository;

import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.rating.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMember(Member member);

}
