package com.commerce.web.domain.item.repository;

import static com.commerce.db.entity.item.QItem.item;

import com.commerce.db.entity.item.Item;
import com.commerce.web.domain.category.model.rs.FindItemByCategoryRs;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public ItemQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Item> findItemList(Long category) {

        List<Long> itemIds = findItemIds(category);

        return queryFactory
            .selectFrom(item)
            .where(item.id.in(itemIds))
            .fetch();
    }

    private List<Long> findItemIds(Long categoryId) {

        return queryFactory
            .select(item.id)
            .from(item)
            .where(item.category.id.eq(categoryId))
            .fetch();

    }

}
