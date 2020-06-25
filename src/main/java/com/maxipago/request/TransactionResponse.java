package com.maxipago.request;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class TransactionResponse {
    public String authCode;
    public String orderID;
    public String referenceNum;
    public String transactionID;
    public String transactionTimestamp;
    public String responseCode;
    public String responseMessage;
    public String avsResponseCode;
    public String processorCode;
    public String processorMessage;
    public String processorName;
    public String creditCardBin;
    public String creditCardLast4;
    public String errorMessage;
    public String processorTransactionID;
    public String processorReferenceNumber;
    public String creditCardScheme;
    public String cvvResponseCode;
    public String authenticationURL;
    public String fraudScore;
    public String onlineDebitUrl;
}
