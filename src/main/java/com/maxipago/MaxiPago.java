package com.maxipago;

import com.maxipago.request.ApiRequest;
import com.maxipago.request.RApiRequest;
import com.maxipago.request.Request;
import com.maxipago.request.TransactionRequest;

public class MaxiPago {
    private TransactionRequest transactionRequest;
    private ApiRequest apiRequest;
    private final Environment environment;
    private Order order;
    private Request request;
    private RApiRequest rApiRequest;

    public MaxiPago(Environment environment) {
        this.environment = environment;
    }

    private void createTransactionRequest() {
        order = new Order();

        environment.setTransaction(true);

        transactionRequest = new TransactionRequest(environment);
        transactionRequest.setOrder(order);
    }

    private void createApiRequest(String command) {
        request = new Request();

        environment.setTransaction(false);

        apiRequest = new ApiRequest(command, environment);
        apiRequest.setRequest(request);
    }

    private void createRApiRequest(String command) {
        request = new Request();
        request.filterOptions = new FilterOptions();

        environment.setReports(true);

        rApiRequest = new RApiRequest(command, environment);
        rApiRequest.setRequest(request);
    }

    public Request consultTransaction(String transactionId) {
        createRApiRequest("transactionDetailReport");

        request.setTransactionId(transactionId);

        return request;
    }

    public Request consultOrder(String orderId) {
        createRApiRequest("transactionDetailReport");

        request.filterOptions.setOrderId(orderId);

        return request;
    }

    public FilterOptions consultOrderList(String period) {
        createRApiRequest("transactionDetailReport");

        request.filterOptions.setPeriod(period);

        return request.filterOptions;
    }

    public Transaction auth() {
        createTransactionRequest();

        return order.createAuth();
    }

    public Transaction sale() {
        createTransactionRequest();

        return order.createSale();
    }

    public Transaction capture() {
        createTransactionRequest();

        return order.createCapture();
    }

    public Transaction cancel() {
        createTransactionRequest();

        return order.createVoid();
    }

    public Transaction refund() {
        createTransactionRequest();

        return order.createRefund();
    }

    public Transaction zeroDollar() {
        createTransactionRequest();

        Transaction zeroDollar = order.createZeroDollar();
        zeroDollar.setPayment(new Payment(0.0));

        return zeroDollar;
    }

    public Transaction recurringPayment() {
        createTransactionRequest();

        return order.createRecurringPayment();
    }

    public Request modifyRecurring() {
        createApiRequest("modify-recurring");

        return request;
    }

    public Request cancelRecurring() {
        createApiRequest("cancel-recurring");

        return request;
    }

    public Transaction boleto() {
        createTransactionRequest();

        return order.createSale();
    }

    public Transaction onlineDebit() {
        createTransactionRequest();

        return order.createSale();
    }

    public Request addConsumer() {
        createApiRequest("add-consumer");

        return request;
    }

    public Request updateConsumer() {
        createApiRequest("update-consumer");

        return request;
    }

    public Request deleteConsumer() {
        createApiRequest("delete-consumer");

        return request;
    }

    public Request addCardOnFile() {
        createApiRequest("add-card-onfile");

        return request;
    }

    public Request deleteCardOnFile() {
        createApiRequest("delete-card-onfile");

        return request;
    }

    public TransactionRequest transactionRequest() {
        return transactionRequest;
    }

    public ApiRequest apiRequest() {
        return apiRequest;
    }

    public RApiRequest rapiRequest() {
        return rApiRequest;
    }

}
