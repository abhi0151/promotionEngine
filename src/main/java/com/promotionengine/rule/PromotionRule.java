package com.promotionengine.rule;

import com.promotionengine.dto.Cart;

public abstract class PromotionRule {

    public abstract String getString();

    public abstract boolean isApplicable(Cart cart);

    public abstract void execute(Cart cart);

    protected static boolean isEmptyCart(Cart cart) {
        return (cart == null) || cart.items == null || cart.items.size() == 0;
    }
}
