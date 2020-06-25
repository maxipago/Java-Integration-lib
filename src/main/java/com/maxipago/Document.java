package com.maxipago;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Document {
    @XmlElement
    public String documentType;

    @XmlElement
    public String documentValue;

    public Document() {}

    public Document(String documentType, String documentValue) {
        this.documentType = documentType;
        this.documentValue = documentValue;
    }

    public Document setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public Document setDocumentValue(String documentValue) {
        this.documentValue = documentValue;
        return this;
    }
}
