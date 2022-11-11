package com.promotionengine.dto;

public class SKUitem {
    public String getId() {
        return id;
    }

    public String id;
    public double unitPrice;

    public SKUitem(String id, double unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
    }

}
