package com.maxipago.request;

import com.maxipago.Environment;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.zip.GZIPInputStream;

@XmlSeeAlso({TransactionRequest.class, ApiRequest.class, RApiRequest.class})
public abstract class AbstractRequest<A, B> {
    @XmlElement(name = "verification")
    public Environment verification;

    public AbstractRequest() {
    }

    public AbstractRequest(Environment verification) {
        this.verification = verification;
    }

    protected abstract Class<A> getContext();

    protected abstract B getResponseObject();

    protected void specialCase(B response, Field field, Document document) {
    }

    protected B parse(InputStream inputStream) {
        B response = getResponseObject();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            Field[] fields = response.getClass().getDeclaredFields();

            for (Field field : fields) {
                try {
                    if (!field.getType().getName().equals("java.lang.String")) {
                        specialCase(response, field, document);
                        continue;
                    }

                    field.setAccessible(true);
                    field.set(response, getTextContent(document, field.getName()));
                } catch (IllegalAccessException e) {
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        return response;
    }

    public B execute() {
        StringWriter writer = new StringWriter();

        try {
            JAXBContext requestContext = JAXBContext.newInstance(getContext());

            Marshaller marshaller = requestContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "iso-8859-1");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(this, new StreamResult(writer));

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(verification.getEndpoint());

            final RequestConfig params = RequestConfig
                    .custom()
                    .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                    .build();

            httpPost.setEntity(new StringEntity(writer.toString()));
            httpPost.setConfig(params);

            httpPost.addHeader("Content-Type", "text/xml; charset=ISO-8859-1");
            httpPost.addHeader("User-Agent", "MaxiPago-SDK/1.0 (Java; Linux x86_64)");

            HttpResponse response = null;

            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();
            InputStream responseEntityContent = null;

            responseEntityContent = responseEntity.getContent();

            Header contentEncoding = response.getFirstHeader("Content-Encoding");

            if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                responseEntityContent = new GZIPInputStream(responseEntityContent);
            }

            return parse(responseEntityContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String getTextContent(Document document, String elementName) {
        Node element = document.getElementsByTagName(elementName).item(0);

        if (element != null) {
            return element.getTextContent();
        }

        return null;
    }
}
