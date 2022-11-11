package com.promotionengine.dto;

public class CartItem {

    public void setPromotionApplied(boolean promotionApplied) {
        this.promotionApplied = promotionApplied;
    }

    public SKUitem item;
    public boolean promotionApplied;
    public double finalPrice;

    public SKUitem getItem() {
        return item;
    }

    public boolean isPromotionApplied() {
        return promotionApplied;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public CartItem(SKUitem item, double unitPrice, boolean b) {
        this.item = item;
        this.finalPrice = unitPrice;
        this.promotionApplied = b;
    }
}
