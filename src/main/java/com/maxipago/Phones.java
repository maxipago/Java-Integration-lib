package com.maxipago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Phones {
    @XmlElement
    public ArrayList<Phone> phone;

    public Phones() {
        this.phone = new ArrayList<Phone>();
    }
}
