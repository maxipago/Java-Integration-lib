package com.maxipago.request;

import com.maxipago.Environment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;

@XmlRootElement(name = "api-request")
public class ApiRequest extends AbstractRequest<ApiRequest, ApiResponse> {
    public String command;

    @XmlElement
    public Request request;

    public ApiRequest() {}

    public ApiRequest(String command, Environment environment) {
        super(environment);

        this.command = command;
    }

    public ApiRequest setRequest(Request request) {
        this.request = request;
        return this;
    }

    protected Class<ApiRequest> getContext() {
        return ApiRequest.class;
    }

    protected ApiResponse getResponseObject() {
        return new ApiResponse();
    }
}
