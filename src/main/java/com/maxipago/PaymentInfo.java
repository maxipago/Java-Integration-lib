package com.maxipago;

import javax.xml.bind.annotation.XmlElement;

public class PaymentInfo {
    @XmlElement
    public CardInfo cardInfo;

    public CardInfo cardInfo(Double chargeTotal) {
        cardInfo = new CardInfo();

        return cardInfo;
    }

    public PaymentInfo setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
        return this;
    }
}
