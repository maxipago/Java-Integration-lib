package com.maxipago;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
    @XmlElement
    public Integer itemIndex;

    @XmlElement
    public String itemProductCode;

    @XmlElement
    public String itemDescription;

    @XmlElement
    public Integer itemQuantity;

    @XmlElement
    public Double itemTotalAmount;

    @XmlElement
    public Double itemUnitCost;

    public Item() {}

    public Item(Integer itemIndex, String itemProductCode, String itemDescription, Integer itemQuantity, Double itemTotalAmount, Double itemUnitCost) {
        this.itemIndex = itemIndex;
        this.itemProductCode = itemProductCode;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemTotalAmount = itemTotalAmount;
        this.itemUnitCost = itemUnitCost;
    }

    public Item setItemIndex(Integer itemIndex) {
        this.itemIndex = itemIndex;
        return this;
    }

    public Item setItemProductCode(String itemProductCode) {
        this.itemProductCode = itemProductCode;
        return this;
    }

    public Item setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public Item setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
        return this;
    }

    public Item setItemTotalAmount(Double itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
        return this;
    }

    public Item setItemUnitCost(Double itemUnitCost) {
        this.itemUnitCost = itemUnitCost;
        return this;
    }
}
