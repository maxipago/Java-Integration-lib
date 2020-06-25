package com.maxipago.paymentmethod;

public class OnlineDebit {
    public static String Itau = "18";

    public String parametersURL;

    public OnlineDebit setParametersURL(String parametersURL) {
        this.parametersURL = parametersURL;
        return this;
    }
}
