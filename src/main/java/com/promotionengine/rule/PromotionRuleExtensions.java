package com.promotionengine.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PromotionRuleExtensions {
    public static NItemForFixedPricePromotion ToNitemForFixedPricePromotion(String promotion) {
        //3 A for 130
        List<String> promotionDetails = Arrays.stream(promotion.split(" "))
                .filter(p -> !p.isEmpty() && !"for".equals(p) && !"of".equals(p)).toList();
        String sku = promotionDetails.get(1).replace("'s", "");
        int numberOfItems = Integer.parseInt(promotionDetails.get(0));

        int price = Integer.parseInt(promotionDetails.get(promotionDetails.size() - 1));

        return new NItemForFixedPricePromotion(sku, numberOfItems, price);
    }

    public static CombinedItemFixedPricePromotion ToCombinedItemFixedPricePromotion(String promotion) {
        //C D for 30
        List<String> promotionDetails = Arrays.stream(promotion.split(" "))
                .filter(p -> !p.isEmpty() && !"for".equals(p) && !"&".equals(p)).toList();
        List<String> skus = new ArrayList<>();
        for (int i = 0; i < promotionDetails.size() - 1; i++) {
            skus.add(promotionDetails.get(i));
        }
        int price = Integer.parseInt(promotionDetails.get(promotionDetails.size() - 1));

        return new CombinedItemFixedPricePromotion(skus, price);
    }
}
