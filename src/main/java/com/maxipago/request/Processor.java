package com.maxipago.request;

import javax.xml.bind.annotation.XmlAttribute;

public class Processor {
    @XmlAttribute
    public String name;

    @XmlAttribute
    public String type;

    public String code;
    public String message;
    public String score;
    public String riskLevel;
}