package com.maxipago.request;

import com.maxipago.Environment;
import com.maxipago.Order;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

@XmlRootElement(name = "transaction-request")
public class TransactionRequest extends AbstractRequest<TransactionRequest, TransactionResponse> {
    @XmlElement
    public String version = "3.1.1.15";

    private Order order;

    public TransactionRequest() {
    }

    public TransactionRequest(Environment environment) {
        super(environment);

        order = new Order();
    }

    public Order getOrder() {
        return order;
    }

    public TransactionRequest setOrder(Order order) {
        this.order = order;
        return this;
    }

    protected Class<TransactionRequest> getContext() {
        return TransactionRequest.class;
    }
    protected TransactionResponse getResponseObject() {
        return new TransactionResponse();
    }
}
