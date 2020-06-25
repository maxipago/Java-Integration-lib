package com.maxipago.request;

import com.maxipago.Environment;
import com.maxipago.Record;
import com.maxipago.ResultSetInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.ArrayList;

@XmlRootElement(name = "rapi-request")
public class RApiRequest extends AbstractRequest<RApiRequest, RApiResponse> {
    public String command;

    @XmlElement
    public Request request;

    public RApiRequest() {
    }

    public RApiRequest(String command, Environment environment) {
        super(environment);

        this.command = command;
    }

    public RApiRequest setRequest(Request request) {
        this.request = request;
        return this;
    }

    protected Class<RApiRequest> getContext() {
        return RApiRequest.class;
    }

    protected RApiResponse getResponseObject() {
        return new RApiResponse();
    }

    protected void specialCase(RApiResponse response, Field field, Document document) {
        switch (field.getName()) {
            case "resultSetInfo":
                response.resultSetInfo = new ResultSetInfo();
                response.resultSetInfo.pageNumber = Integer.parseInt(getTextContent(document, "pageNumber"));
                response.resultSetInfo.totalNumberOfRecords = Integer.parseInt(getTextContent(document, "totalNumberOfRecords"));
                response.resultSetInfo.pageToken = getTextContent(document, "pageToken");

                break;

            case "records":
                NodeList recordList = document.getElementsByTagName("record");
                response.records = new ArrayList<Record>();

                for (int i = 0, j = recordList.getLength(); i < j; i++) {
                    Node recordNode = recordList.item(i);
                    NodeList elements = recordNode.getChildNodes();
                    Record record = new Record();
                    Field[] fields = record.getClass().getDeclaredFields();

                    for (int k = 0, l = elements.getLength(); k < l; k++) {
                        String elementName = elements.item(k).getNodeName();
                        String elementValue = elements.item(k).getTextContent();
                        Boolean found = false;

                        for (int m = 0, n = fields.length; m < n; m++) {
                            if (fields[m].getName().equals(elementName)) {
                                try {
                                    fields[m].setAccessible(true);
                                    fields[m].set(record, elementValue);

                                    found = true;
                                    break;
                                } catch (IllegalAccessException e) {
                                }
                            }
                        }
                    }

                    response.records.add(record);
                }

        }
    }

}
