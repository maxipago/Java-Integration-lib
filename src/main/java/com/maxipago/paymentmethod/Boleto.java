package com.maxipago.paymentmethod;

public class Boleto {
    public static String BancoDoBrasil = "001";
    public static String Bradesco = "237";
    public static String Itau = "341";
    public static String CaixaEconomicaFederal = "104";
    public static String Santander = "033";
    public static String HSBC = "399";

    public String expirationDate;
    public String number;
    public String instructions;

    public Boleto setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Boleto setNumber(String number) {
        this.number = number;
        return this;
    }

    public Boleto setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }
}
