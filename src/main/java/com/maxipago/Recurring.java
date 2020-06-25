package com.maxipago;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recurring")
public class Recurring {
    public static String DAILY = "daily";
    public static String WEEKLY = "weekly";
    public static String MONTHLY = "monthly";
    public static String BIMONTHLY = "bimonthly";
    public static String QUARTERLY = "quarterly";
    public static String SEMIANNUAL = "semiannual";
    public static String ANNUAL = "annual";

    @XmlElement
    public String processorID;

    @XmlElement
    public String action;

    @XmlElement
    public String startDate;

    @XmlElement
    public String period;

    @XmlElement
    public Integer frequency;

    @XmlElement
    public Integer installments;

    @XmlElement
    public Double firstAmount;

    @XmlElement
    public Double lastAmount;

    @XmlElement
    public String lastDate;

    @XmlElement
    public String nextFireDate;

    @XmlElement
    public String fireDay;

    @XmlElement
    public Integer failureThreshold = 1;

    public Recurring setAction(String action) {
        this.action = action;
        return this;
    }

    public Recurring setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public Recurring setPeriod(String period) {
        this.period = period;
        return this;
    }

    public Recurring setFrequency(Integer frequency) {
        this.frequency = frequency;
        return this;
    }

    public Recurring setInstallments(Integer installments) {
        this.installments = installments;
        return this;
    }

    public Recurring setFirstAmount(Double firstAmount) {
        this.firstAmount = firstAmount;
        return this;
    }

    public Recurring setLastAmount(Double lastAmount) {
        this.lastAmount = lastAmount;
        return this;
    }

    public Recurring setLastDate(String lastDate) {
        this.lastDate = lastDate;
        return this;
    }

    public Recurring setFailureThreshold(Integer failureThreshold) {
        this.failureThreshold = failureThreshold;
        return this;
    }

    public Recurring setProcessorID(String processorID) {
        this.processorID = processorID;
        return this;
    }

    public Recurring setNextFireDate(String nextFireDate) {
        this.nextFireDate = nextFireDate;
        return this;
    }

    public Recurring setFireDay(String fireDay) {
        this.fireDay = fireDay;
        return this;
    }
}
