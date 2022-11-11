package com.promotionengine.controller;

import com.promotionengine.store.IStore;
import com.promotionengine.rule.PromotionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/promotions")
public class PromotionsController {
    @Autowired
    IStore iStore;

    @GetMapping("/getPromotions")
    public ResponseEntity<List<String>> getAllPromotions() {
        return ResponseEntity.ok(iStore.getPromotions().stream().map(PromotionRule::getString).toList());
    }

    @PostMapping("/{promotion}")
    public ResponseEntity<List<String>> addPromotion(String promotion) {
        iStore.addPromotion(promotion);
        return ResponseEntity.ok(iStore.getPromotions().stream().map(PromotionRule::getString).toList());
    }
}
