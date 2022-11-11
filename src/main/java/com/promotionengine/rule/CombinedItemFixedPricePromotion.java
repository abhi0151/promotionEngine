package com.promotionengine.rule;

import com.promotionengine.dto.Cart;
import com.promotionengine.dto.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CombinedItemFixedPricePromotion extends PromotionRule {
    public List<String> SKUs;
    public int fixedPrice;

    public CombinedItemFixedPricePromotion(List<String> skus, int fixedPrice) {
        this.SKUs = skus;
        this.fixedPrice = fixedPrice;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        if (isEmptyCart(cart)) return false;
        List<CartItem> cartItemsWithoutPromotion = cart.items.stream().filter(i->!i.isPromotionApplied()).toList();
        boolean applicable = true;
        for (String sk : SKUs) {
            applicable &= cartItemsWithoutPromotion.stream().anyMatch(i -> sk.equals(i.getItem().id));
        }
        return applicable;
    }

    @Override
    public void execute(Cart cart) {
        while (isApplicable(cart)) {
            List<String> unusedSKUs = new ArrayList<>(SKUs);
            for (CartItem ci : cart.items.stream().filter(i->!i.isPromotionApplied()).toList()) {
                if (unusedSKUs.contains(ci.item.id)) {
                    ci.setFinalPrice(fixedPrice / SKUs.size());
                    ci.promotionApplied = true;
                    unusedSKUs.remove(ci.item.id);
                }
            }
        }
    }

    public String getString() {
        return String.join(" & ", SKUs) + " for " + fixedPrice;
    }
}
