package com.promotionengine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {

    @GetMapping("/items")
    public void getAllItems() {
    }

    @GetMapping("/items/{sku}")
    public void getItem(String sku)  {
    }

    @GetMapping("/cart")
    public void getCart() {

    }

    @PostMapping("/cart/{sku}")
    public void addItemToCart(String sku) {
    }

    @GetMapping("/cart/total")
    public void getCartTotal()
    {
    }

    @GetMapping("/cart/checkout")
    public void checkout() {
    }
}
