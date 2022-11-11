package com.promotionengine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SKUitemDTO {
    public String id;
    public double unitPrice;

    public SKUitemDTO(String id, double unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
    }
}
