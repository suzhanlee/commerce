package com.commerce.web.domain.vegetable.service;

import com.commerce.db.entity.item.Item;
import com.commerce.db.entity.item.Vegetable;
import com.commerce.web.domain.vegetable.model.CreateVegetableRq;
import com.commerce.web.domain.vegetable.repository.VegetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VegetableService {

    private final VegetableRepository vegetableRepository;

    public void createVegetable(CreateVegetableRq rq, Item item) {

        Vegetable vegetable = Vegetable.create(rq.getOrigin(), item);
        vegetableRepository.save(vegetable);

    }
}
