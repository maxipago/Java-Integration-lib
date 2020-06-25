package com.maxipago;

public class CreditInstallment {
    private Integer numberOfInstallments = 1;

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public CreditInstallment setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
        return this;
    }

    public String getChargeInterest() {
        return chargeInterest;
    }

    public CreditInstallment setChargeInterest(String chargeInterest) {
        this.chargeInterest = chargeInterest;
        return this;
    }

    private String chargeInterest = "N";

    public CreditInstallment() {
        this(1, "N");
    }

    public CreditInstallment(Integer numberOfInstallments) {
        this(numberOfInstallments, "N");
    }

    public CreditInstallment(Integer numberOfInstallments, String chargeInterest) {
        this.numberOfInstallments = numberOfInstallments;
        this.chargeInterest = chargeInterest;
    }
}
