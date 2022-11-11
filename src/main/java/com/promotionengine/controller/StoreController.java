package com.promotionengine.controller;

import com.promotionengine.dto.*;
import com.promotionengine.exceptions.ArgumentException;
import com.promotionengine.store.IStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    IStore iStore;

    @GetMapping("/items")
    public ResponseEntity<List<CartItemDTO>> getAllItems() {
        return ResponseEntity.ok(iStore.getCart().items.stream().map(Mappers::toDTO).toList());
    }

    @GetMapping("/items/{sku}")
    public ResponseEntity<SKUitemDTO> getItem(String sku) throws ArgumentException {
        return ResponseEntity.ok(Mappers.toDTO(iStore.getItem(sku)));
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> getCart() {
        return ResponseEntity.ok(iStore.getCart());
    }

    @PostMapping("/cart/{sku}")
    public ResponseEntity<List<CartItemDTO>> addItemToCart(String sku) {
        iStore.addItemToCart(sku);
        return ResponseEntity.ok(iStore.getCart().items.stream().map(Mappers::toDTO).toList());
    }

    @GetMapping("/cart/total")
    public ResponseEntity<Double> getCartTotal() {
        return ResponseEntity.ok(iStore.getCartTotal());
    }

    @GetMapping("/cart/checkout")
    public ResponseEntity<Map<String, Integer>> checkout() {
        Map<String, Integer> checkoutItems = new HashMap<>();
        Cart cart = iStore.checkout();
        for (CartItem ci : cart.items) {
            if (!checkoutItems.containsKey(ci.item.id)) {
                checkoutItems.put(ci.item.id, 1);
            } else {
                checkoutItems.put(ci.item.id, checkoutItems.get(ci.item.id) + 1);
            }
        }
        return ResponseEntity.ok(checkoutItems);
    }
}
