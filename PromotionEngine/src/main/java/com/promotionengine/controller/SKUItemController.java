package com.promotionengine.controller;

import com.promotionengine.dto.SKUItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skuItems")
public class SKUItemController {

    @GetMapping("/all")
    public void getAllSKUItems() {
    }

    @GetMapping("{id}")
    public void getSKUItem(String id) {
    }

    @PostMapping("/add")
    public void addSKUItem(SKUItemDTO item) {
    }
}
