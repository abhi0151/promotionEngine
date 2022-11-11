package com.promotionengine.dto;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public List<CartItem> items = new ArrayList<>();
    public double totalPrice() {
        return items.stream().mapToDouble(CartItem::getFinalPrice).sum();
    }

    public void addItems(SKUitem item, int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            items.add(new CartItem(item, item.unitPrice, false));
        }
    }

    public void addItem(SKUitem item) {
        items.add(new CartItem(item, item.unitPrice, false));
    }

    public void removeItem(String skuItemId) {
        items.remove(items.stream().filter(i -> skuItemId.equals(i.item.id)).findFirst().get());
    }
}
