package com.maxipago.request;

import com.maxipago.*;

import javax.xml.bind.annotation.XmlElement;

public class Request {
    @XmlElement
    public String customerId;

    @XmlElement
    public String creditCardNumber;

    @XmlElement
    public String expirationMonth;

    @XmlElement
    public String expirationYear;

    @XmlElement
    public String token;

    @XmlElement
    public String billingName;

    @XmlElement
    public String billingAddress1;

    @XmlElement
    public String billingAddress2;

    @XmlElement
    public String billingCity;

    @XmlElement
    public String billingState;

    @XmlElement
    public String billingZip;

    @XmlElement
    public String billingCountry;

    @XmlElement
    public String billingPhone;

    @XmlElement
    public String billingEmail;

    @XmlElement
    public String onFilePermissions;

    @XmlElement
    public Double onFileMaxChargeAmount;

    @XmlElement
    public String customerIdExt;

    @XmlElement
    public String firstName;

    @XmlElement
    public String lastName;

    @XmlElement
    public String address1;

    @XmlElement
    public String address2;

    @XmlElement
    public String city;

    @XmlElement
    public String state;

    @XmlElement
    public String zip;

    @XmlElement
    public String country;

    @XmlElement
    public String phone;

    @XmlElement
    public String email;

    @XmlElement
    public String dob;

    @XmlElement
    public String sex;

    @XmlElement
    public String ssn;

    @XmlElement(name = "orderID")
    public String orderId;

    @XmlElement
    public PaymentInfo paymentInfo;

    @XmlElement
    public Recurring recurring;

    @XmlElement
    public Customer billingInfo;

    @XmlElement
    public Customer shippingInfo;

    @XmlElement
    public FilterOptions filterOptions;

    public Request setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Request setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public Request setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
        return this;
    }

    public Request setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }

    public Request setToken(String token) {
        this.token = token;
        return this;
    }

    public Request setBillingName(String billingName) {
        this.billingName = billingName;
        return this;
    }

    public Request setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
        return this;
    }

    public Request setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
        return this;
    }

    public Request setBillingCity(String billingCity) {
        this.billingCity = billingCity;
        return this;
    }

    public Request setBillingState(String billingState) {
        this.billingState = billingState;
        return this;
    }

    public Request setBillingZip(String billingZip) {
        this.billingZip = billingZip;
        return this;
    }

    public Request setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
        return this;
    }

    public Request setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
        return this;
    }

    public Request setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
        return this;
    }

    public Request setOnFilePermissions(String onFilePermissions) {
        this.onFilePermissions = onFilePermissions;
        return this;
    }

    public Request setOnFileMaxChargeAmount(Double onFileMaxChargeAmount) {
        this.onFileMaxChargeAmount = onFileMaxChargeAmount;
        return this;
    }

    public Request setCustomerIdExt(String customerIdExt) {
        this.customerIdExt = customerIdExt;
        return this;
    }

    public Request setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Request setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Request setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public Request setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public Request setCity(String city) {
        this.city = city;
        return this;
    }

    public Request setState(String state) {
        this.state = state;
        return this;
    }

    public Request setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public Request setCountry(String country) {
        this.country = country;
        return this;
    }

    public Request setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Request setEmail(String email) {
        this.email = email;
        return this;
    }

    public Request setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public Request setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Request setSsn(String ssn) {
        this.ssn = ssn;
        return this;
    }

    public Request setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Request setOrderId(String orderId, Boolean reports) {
        if (!reports) {
            return setOrderId(orderId);
        }

        filterOptions = new FilterOptions();
        filterOptions.orderId = orderId;

        return this;
    }

    public Request setTransactionId(String transactionId) {
        filterOptions = new FilterOptions();
        filterOptions.transactionId = transactionId;

        return this;
    }

    public Request setPaymentInfo(String creditCardNumber, String expirationMonth, String expirationYear, Double chargeTotal) {
        this.paymentInfo = new PaymentInfo();
        this.paymentInfo.cardInfo = new CardInfo();
        this.paymentInfo.cardInfo.setCreditCardNumber(creditCardNumber)
                .setExpirationMonth(expirationMonth)
                .setExpirationYear(expirationYear);

        return this;
    }

    public Request setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
        return this;
    }

    public Request setRecurring(Recurring recurring) {
        this.recurring = recurring;
        return this;
    }

    public Request setBillingInfo(Customer billingInfo) {
        this.billingInfo = billingInfo;
        return this;
    }

    public Request setShippingInfo(Customer shippingInfo) {
        this.shippingInfo = shippingInfo;
        return this;
    }
}
