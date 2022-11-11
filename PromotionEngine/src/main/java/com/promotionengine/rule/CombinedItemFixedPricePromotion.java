package com.promotionengine.rule;

import com.promotionengine.dto.Cart;
import com.promotionengine.dto.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CombinedItemFixedPricePromotion {
    public List<String> SKUs;
    public int fixedPrice;

    public CombinedItemFixedPricePromotion(List<String> skus, int fixedPrice) {
        this.SKUs = skus;
        this.fixedPrice = fixedPrice;
    }

    public String getString() {
        return String.join(" & ", SKUs) + " for " + fixedPrice;
    }
}
