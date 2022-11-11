package com.promotionengine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SKUItemDTO {
    public String id;
    public double unitPrice;

    public SKUItemDTO(String id, double unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
    }
}
