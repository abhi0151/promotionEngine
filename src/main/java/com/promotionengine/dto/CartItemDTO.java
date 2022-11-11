package com.promotionengine.dto;

public class CartItemDTO {
    public SKUitemDTO item;
    public boolean promotionApplied;
    public double finalPrice;

    public CartItemDTO(SKUitemDTO toDTO, double finalPrice, boolean promotionApplied) {
        this.item = toDTO;
        this.finalPrice = finalPrice;
        this.promotionApplied = promotionApplied;
    }
}
