package com.promotionengine.rule;

import com.promotionengine.dto.Cart;

public abstract class PromotionRule {

    public abstract String getString();

    public abstract boolean isApplicable(Cart cart);

    public abstract void execute(Cart cart);

}
