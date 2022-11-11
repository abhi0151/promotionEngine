package com.promotionengine.rule;

import com.promotionengine.dto.Cart;
import com.promotionengine.dto.CartItem;

public class NItemForFixedPricePromotion extends PromotionRule {
    public String sku;
    public int numberOfItems;
    public double fixedPrice;

    public NItemForFixedPricePromotion(String sku, int numberOfItems, int fixedPrice) {
        this.sku = sku;
        this.numberOfItems = numberOfItems;
        this.fixedPrice = fixedPrice;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return !isEmptyCart(cart) &&
                cart.items.stream().filter(i -> !i.isPromotionApplied() && sku.equals(i.item.id)).count() >= numberOfItems;
    }

    @Override
    public void execute(Cart cart) {
        int count = numberOfItems;
        double discountItemPrice = fixedPrice / numberOfItems;
        while (isApplicable(cart)) {
            for (CartItem cartItem : cart.items.stream().filter(i -> !i.isPromotionApplied() && sku.equals(i.item.id)).toList()) {
                cartItem.finalPrice = discountItemPrice;
                cartItem.promotionApplied = true;
                if (count == 1)
                    break;
                count--;
            }
        }
    }

    public String getString() {
        return numberOfItems + " of " + sku + " for " + fixedPrice;
    }
}
