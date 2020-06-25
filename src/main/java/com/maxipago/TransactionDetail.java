package com.maxipago;

import com.maxipago.paymentmethod.Boleto;
import com.maxipago.paymentmethod.Card;
import com.maxipago.paymentmethod.PayType;

import javax.xml.bind.annotation.XmlElement;

public class TransactionDetail {
    @XmlElement(name = "payType")
    private PayType payType;

    public Card creditCard() {
        payType = new PayType();
        payType.creditCard = new Card();

        return payType.creditCard;
    }

    public Boleto boleto() {
        payType = new PayType();
        payType.boleto = new Boleto();

        return payType.boleto;
    }

    public TransactionDetail setPayType(PayType payType) {
        this.payType = payType;
        return this;
    }
}
