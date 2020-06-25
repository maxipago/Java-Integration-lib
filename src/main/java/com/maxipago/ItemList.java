package com.maxipago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class ItemList {
    @XmlAttribute
    public Integer itemCount = 0;

    @XmlElement
    public ArrayList<Item> item;

    public ItemList() {
        this.item = new ArrayList<Item>();
    }
}
