package com.promotionengine.rule;

import com.promotionengine.dto.Cart;
import com.promotionengine.dto.CartItem;

public class NitemForFixedPricePromotion {
    public String sku;
    public int numberOfItems;
    public int fixedPrice;

    public NitemForFixedPricePromotion(String sku, int numberOfItems, int fixedPrice) {
        this.sku = sku;
        this.numberOfItems = numberOfItems;
        this.fixedPrice = fixedPrice;
    }

    public String getString() {
        return numberOfItems + " of " + sku + " for " + fixedPrice;
    }
}
