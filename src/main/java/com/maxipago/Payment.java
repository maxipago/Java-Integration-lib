package com.maxipago;

import javax.xml.bind.annotation.XmlElement;

public class Payment {
    @XmlElement
    private Double chargeTotal;

    @XmlElement
    private String currencyCode = "BRL";

    @XmlElement
    public String softDescriptor;

    @XmlElement
    private CreditInstallment creditInstallment;

    public Payment(Double chargeTotal) {
        this.chargeTotal = chargeTotal;
    }

    public Payment(Double chargeTotal, String currencyCode, Integer numberOfInstallments, String chargeInterest) {
        this.chargeTotal = chargeTotal;
        this.currencyCode = currencyCode;

        this.creditInstallment = new CreditInstallment(numberOfInstallments, chargeInterest);
    }

    public Payment setChargeTotal(Double chargeTotal) {
        this.chargeTotal = chargeTotal;
        return this;
    }

    public Payment setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Payment setSoftDescriptor(String softDescriptor) {
        this.softDescriptor = softDescriptor;
        return this;
    }

    public Payment setCreditInstallment(CreditInstallment creditInstallment) {
        this.creditInstallment = creditInstallment;
        return this;
    }
}
