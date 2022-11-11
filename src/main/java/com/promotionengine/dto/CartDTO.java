package com.promotionengine.dto;

import java.util.List;
public class CartDTO {
    public List<CartItemDTO> items;
    public double totalPrice;

    public CartDTO(List<CartItemDTO> toList, double totalPrice) {
        this.items = toList;
        this.totalPrice = totalPrice;

    }
}
