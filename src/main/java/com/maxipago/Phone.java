package com.maxipago;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Phone {
    @XmlElement
    public String phoneCountryCode;

    @XmlElement
    public String phoneType;

    @XmlElement
    public String phoneAreaCode;

    @XmlElement
    public String phoneNumber;

    @XmlElement
    public String phoneExtension;

    public Phone() {
    }

    public Phone(String phoneType, String phoneCountryCode, String phoneAreaCode, String phoneNumber) {
        this(phoneType, phoneCountryCode, phoneAreaCode, phoneNumber, null);
    }

    public Phone(String phoneType, String phoneCountryCode, String phoneAreaCode, String phoneNumber, String phoneExtension) {
        this.phoneType = phoneType;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneAreaCode = phoneAreaCode;
        this.phoneNumber = phoneNumber;
        this.phoneExtension = phoneExtension;
    }

    public Phone setPhoneType(String phoneType) {
        this.phoneType = phoneType;
        return this;
    }

    public Phone setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
        return this;
    }

    public Phone setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
        return this;
    }

    public Phone setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Phone setPhoneExtension(String phoneExtension) {
        this.phoneExtension = phoneExtension;
        return this;
    }
}
