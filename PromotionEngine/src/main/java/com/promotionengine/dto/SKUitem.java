package com.promotionengine.dto;

public class SKUitem {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String id;
    public double unitPrice;

    public SKUitem(String id, double unitPrice) {
        this.id = id;
        this.unitPrice = unitPrice;
    }

    public void updateUnitPrice(double price) {
        unitPrice = price;
    }
}
