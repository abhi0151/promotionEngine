package com.promotionengine.store;

import com.promotionengine.exceptions.ArgumentException;
import com.promotionengine.rule.PromotionRuleExtensions;
import com.promotionengine.dto.Cart;
import com.promotionengine.dto.Mappers;
import com.promotionengine.dto.SKUitem;
import com.promotionengine.dto.SKUitemDTO;
import com.promotionengine.rule.PromotionRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Store implements IStore {
    public Cart cart;
    public List<PromotionRule> promotions;
    public List<SKUitem> items;

    public Store() {
        cart = new Cart();
        promotions = new ArrayList<>();
        items = new ArrayList<>();
    }

    public SKUitemDTO addSKUitem(SKUitemDTO item) {
        SKUitem skUItem = null;
        if (item != null) skUItem = Mappers.toDAO(item);
        items.add(skUItem);
        return item;
    }

    public void addPromotion(PromotionRule promotion) {
        if (promotion != null) promotions.add(promotion);
    }

    public void addPromotion(String promotion) {
        if (null != promotion) {
            if (promotion.contains("of")) {
                addPromotion(PromotionRuleExtensions.ToNitemForFixedPricePromotion(promotion));
            } else {
                addPromotion(PromotionRuleExtensions.ToCombinedItemFixedPricePromotion(promotion));
            }
        }
    }

    public void addItemToCart(String itemSKU) {
        if (!itemSKU.isEmpty()) {
            cart.addItem(items.stream().filter(i -> itemSKU.equals(i.id)).findFirst().get());
        }
    }

    public Cart checkout() {
        for (PromotionRule p : promotions) {
            if (p.isApplicable(cart)) {
                p.execute(cart);
            }
        }
        return cart;
    }

    public List<SKUitemDTO> getSKUitems() {
        return items.stream().map(Mappers::toDTO).toList();
    }

    public SKUitemDTO getSKUItem(String id) {
        return items.stream().filter(i -> i.getId().equals(id)).map(Mappers::toDTO).findFirst().orElse(null);
    }

    public List<PromotionRule> getPromotions() {
        return promotions;
    }

    public List<SKUitem> getAllItems() {
        return items;
    }

    public SKUitem getItem(String sku) throws ArgumentException {
        if (!IsValidSKU(sku)) throw new ArgumentException("SKU not found!");
        return items.stream().filter(i -> sku.equals(i.id)).findFirst().orElse(null);
    }

    public double getCartTotal() {
        return cart.totalPrice();
    }

    public Cart getCart() {
        return cart;
    }

    private boolean IsValidSKU(String sku) {
        return items.stream().anyMatch(i -> sku.equals(i.id));
    }
}
