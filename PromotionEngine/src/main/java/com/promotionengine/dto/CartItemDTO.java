package com.promotionengine.dto;

public class CartItemDTO {
    public SKUItemDTO item;
    public boolean promotionApplied;
    public double finalPrice;

    public CartItemDTO(SKUItemDTO toDTO, double finalPrice, boolean promotionApplied) {
        this.item = toDTO;
        this.finalPrice = finalPrice;
        this.promotionApplied = promotionApplied;
    }
}
