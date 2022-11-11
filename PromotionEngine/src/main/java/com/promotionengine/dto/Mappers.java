package com.promotionengine.dto;

import java.util.List;
import java.util.stream.Collectors;

public class Mappers {
    public static SKUItemDTO toDTO(SKUitem skuItem) {
        return new SKUItemDTO(skuItem.id, skuItem.unitPrice);
    }

    public static SKUitem toDAO(SKUItemDTO sKUitemDTO) {
        return new SKUitem(sKUitemDTO.id, sKUitemDTO.unitPrice);
    }

    public static CartDTO toDTO(Cart cart) {
        List<CartItemDTO> cartItemDTOS = cart.items.stream().map(Mappers::toDTO).collect(Collectors.toList());
        return new CartDTO(cartItemDTOS, cart.totalPrice());
    }

    public static CartItemDTO toDTO(CartItem cartItem) {
        return new CartItemDTO(toDTO(cartItem.item), cartItem.finalPrice, cartItem.promotionApplied);
    }
}
