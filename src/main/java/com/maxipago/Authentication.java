package com.maxipago;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Authentication {
    public static String DECLINE = "decline";
    public static String CONTINUE = "continue";

    @XmlElement
    public String mpiProcessorID;

    @XmlElement
    public String onFailure;

    public Authentication() {
    }

    public Authentication(String mpiProcessorID, String onFailure) {
        this.mpiProcessorID = mpiProcessorID;
        this.onFailure = onFailure;
    }

    public Authentication setMpiProcessorID(String mpiProcessorID) {
        this.mpiProcessorID = mpiProcessorID;
        return this;
    }

    public Authentication setOnFailure(String onFailure) {
        this.onFailure = onFailure;
        return this;
    }
}
