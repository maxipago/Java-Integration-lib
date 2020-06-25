package com.maxipago;

public class CardInfo {
    public String creditCardNumber;
    public String expirationMonth;
    public String expirationYear;
    public Double chargeTotal;

    public CardInfo setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public CardInfo setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
        return this;
    }

    public CardInfo setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }

    public CardInfo setChargeTotal(Double chargeTotal) {
        this.chargeTotal = chargeTotal;
        return this;
    }
}
