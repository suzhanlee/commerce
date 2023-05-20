package com.commerce.web.domain.item.model.rq;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchaseItemRq {

    private Long memberId;
    private List<ItemRq> itemRqList = new ArrayList<>();



}
