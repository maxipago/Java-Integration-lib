package com.maxipago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Documents {
    @XmlElement
    public ArrayList<Document> document;

    public Documents() {
        this.document = new ArrayList<Document>();
    }
}
