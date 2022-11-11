package com.promotionengine.controller;

import com.promotionengine.store.IStore;
import com.promotionengine.dto.SKUitemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/skuItems")
public class SKUItemController {
    @Autowired
    IStore iStore;

    @GetMapping("/all")
    public ResponseEntity<List<SKUitemDTO>> getAllSKUItems() {
        return ResponseEntity.ok(iStore.getSKUitems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SKUitemDTO> getSKUItem(String id) {
        return ResponseEntity.ok(iStore.getSKUItem(id));
    }

    @PostMapping("/add")
    public ResponseEntity<List<SKUitemDTO>> addSKUItem(SKUitemDTO item) {
        iStore.addSKUitem(item);
        return ResponseEntity.ok(iStore.getSKUitems());
    }
}
