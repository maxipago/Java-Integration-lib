package com.maxipago;

import javax.xml.bind.annotation.XmlElement;

public class SaveOnFile {
    @XmlElement
    public String customerToken;

    @XmlElement
    public String onFileEndDate;

    public SaveOnFile() {
    }

    public SaveOnFile(String customerToken, String onFileEndDate) {
        this.customerToken = customerToken;
        this.onFileEndDate = onFileEndDate;
    }

    public SaveOnFile setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
        return this;
    }

    public SaveOnFile setOnFileEndDate(String onFileEndDate) {
        this.onFileEndDate = onFileEndDate;
        return this;
    }
}
