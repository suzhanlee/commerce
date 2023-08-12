package com.commerce.web.domain.recommend.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.member.Member;
import com.commerce.db.entity.rating.Rating;
import com.commerce.web.domain.member.service.FindMemberService;
import com.commerce.web.domain.rating.repository.RatingRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendationService {
    private final RatingRepository ratingRepository;
    private final FindMemberService findMemberService;

    public List<Item> recommendItems(Member member) {

        List<Rating> userRatings = ratingRepository.findByMember(member);

        // 1. 모든 사용자의 평가를 수집
        Map<Member, Map<Item, Integer>> userRatingsMap = new HashMap<>();
        for (Rating rating : ratingRepository.findAll()) {
            Member currentMember = rating.getMember();
            Item currentItem = rating.getItem();
            int currentRating = rating.getRating();

            userRatingsMap.computeIfAbsent(currentMember, k -> new HashMap<>())
                .put(currentItem, currentRating);
        }

        // 2. 코사인 유사도를 이용하여 사용자 간의 유사도를 계산
        Map<Member, Double> userSimilarities = new HashMap<>();
        for (Member otherUser : userRatingsMap.keySet()) {
            if (!otherUser.equals(member)) {
                double similarity = calculateCosineSimilarity(userRatingsMap.get(member), userRatingsMap.get(otherUser));
                userSimilarities.put(otherUser, similarity);
            }
        }

        // 3. 유사한 사용자를 기반으로 아이템 추천을 생성
        Map<Item, Double> itemRecommendations = new HashMap<>();
        for (Member otherUser : userSimilarities.keySet()) {
            double similarity = userSimilarities.get(otherUser);

            for (Item item : userRatingsMap.get(otherUser).keySet()) {
                if (!userRatingsMap.get(member).containsKey(item)) { // 이미 사용자가 평가한 아이템은 제외
                    double rating = userRatingsMap.get(otherUser).get(item);
                    itemRecommendations.merge(item, rating * similarity, Double::sum);
                }
            }
        }

        // 4. 추천된 아이템을 정렬하여 반환
        List<Item> recommendedItems = itemRecommendations.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        return recommendedItems;
    }

    // 5. 구체적인 코드
    private double calculateCosineSimilarity(Map<Item, Integer> ratings1, Map<Item, Integer> ratings2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (Item item : ratings1.keySet()) {
            if (ratings2.containsKey(item)) {
                int rating1 = ratings1.get(item);
                int rating2 = ratings2.get(item);

                dotProduct += rating1 * rating2;
                norm1 += rating1 * rating1;
                norm2 += rating2 * rating2;
            }
        }

        if (norm1 == 0.0 || norm2 == 0.0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}