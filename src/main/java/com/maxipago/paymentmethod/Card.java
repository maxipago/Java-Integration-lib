package com.maxipago.paymentmethod;

import javax.xml.bind.annotation.XmlElement;

public class Card {
    public static String CIELO = "CIELO¨;";
    public static String REDE = "REDE";
    public static String STONE = "STONE";
    public static String GETNET = "GETNET";

    @XmlElement(name = "number")
    private String number;

    @XmlElement(name = "expMonth")
    private String expMonth;

    @XmlElement(name = "expYear")
    private String expYear;

    @XmlElement(name = "cvvNumber")
    private String cvvNumber;

    public Card setNumber(String number) {
        this.number = number;
        return this;
    }

    public Card setExpMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public Card setExpYear(String expYear) {
        this.expYear = expYear;
        return this;
    }

    public Card setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
        return this;
    }
}
