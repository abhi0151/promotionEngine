package com.promotionengine.store;

import com.promotionengine.exceptions.ArgumentException;
import com.promotionengine.dto.Cart;
import com.promotionengine.dto.SKUitem;
import com.promotionengine.dto.SKUitemDTO;
import com.promotionengine.rule.PromotionRule;

import java.util.List;

public interface IStore {
    public SKUitemDTO addSKUitem(SKUitemDTO item);

    public void addPromotion(PromotionRule promotion);

    public void addPromotion(String promotion);

    public void addItemToCart(String itemSKU);


    public Cart checkout();

    public double getCartTotal();

    public List<SKUitemDTO> getSKUitems();

    public SKUitemDTO getSKUItem(String id) ;

    public List<PromotionRule> getPromotions();

    public List<SKUitem> getAllItems();

    public SKUitem getItem(String sku) throws ArgumentException;

    public Cart getCart();
}
