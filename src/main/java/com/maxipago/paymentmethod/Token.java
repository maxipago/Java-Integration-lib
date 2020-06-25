package com.maxipago.paymentmethod;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Token {
    @XmlElement
    public String customerId;

    @XmlElement
    public String token;

    @XmlElement
    public String cvvNumber;

    public Token() {
    }

    public Token(String customerId, String token, String cvvNumber) {
        this.customerId = customerId;
        this.token = token;
        this.cvvNumber = cvvNumber;
    }

    public Token setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    public Token setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
        return this;
    }
}
