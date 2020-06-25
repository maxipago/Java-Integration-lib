package com.maxipago;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Environment {
    private static final String SANDBOX = "https://testapi.maxipago.net";
    private static final String PRODUCTION = "https://api.maxipago.net";

    private String apiURL;

    private final String merchantId;
    private final String merchantKey;

    private Boolean transaction = true;
    private Boolean reports = false;

    public Environment setTransaction(Boolean transaction) {
        this.transaction = transaction;
        this.reports = !transaction;
        return this;
    }

    public Environment setReports(Boolean reports) {
        this.reports = reports;
        this.transaction = !reports;
        return this;
    }

    public Environment(String merchantId, String merchantKey, Boolean production) {
        this.merchantId = merchantId;
        this.merchantKey = merchantKey;
        this.apiURL = Environment.PRODUCTION;

        if (!production) {
            this.apiURL = Environment.SANDBOX;
        }
    }

    public static Environment sandbox(String merchantId, String merchantKey) {
        return new Environment(merchantId, merchantKey, false);
    }

    public static Environment production(String merchantId, String merchantKey) {
        return new Environment(merchantId, merchantKey, true);
    }

    @XmlElement(name = "merchantId")
    public String getMerchantId() {
        return merchantId;
    }

    @XmlElement(name = "merchantKey")
    public String getMerchantKey() {
        return merchantKey;
    }

    public String getEndpoint() {
        if (transaction) {
            return apiURL + "/UniversalAPI/postXML";
        }

        if (reports) {
            return apiURL + "/ReportsAPI/servlet/ReportsAPI";
        }

        return apiURL + "/UniversalAPI/postAPI";
    }
}
