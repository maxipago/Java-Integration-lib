package com.maxipago;

import com.maxipago.paymentmethod.Boleto;
import com.maxipago.paymentmethod.Card;
import com.maxipago.paymentmethod.OnlineDebit;
import com.maxipago.paymentmethod.Token;
import com.maxipago.request.RApiResponse;
import com.maxipago.request.TransactionResponse;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import javax.xml.bind.PropertyException;

public class MaxiPagoTest {
    String merchantId = "";
    String merchantKey = "";

    public MaxiPagoTest() {
    }

    void shouldCreateAuth() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.auth()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setPayment(new Payment(100.0));

        TransactionResponse response = maxiPago.transactionRequest().execute();

        System.out.println(response.processorCode);
        System.out.println(response.orderID);
        System.out.println(response.transactionID);
    }

    void shouldCreateAuthWith3DS() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.auth()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setAuthentication("41", Authentication.DECLINE)
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCaptureAuth() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.capture()
                .setOrderId("0A010492:0172EB977CBC:8CDA:22FB4887")
                .setReferenceNum("Teste 123")
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCancelTransaction() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.cancel()
                .setTransactionId("9044089");

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateZeroDollarTransaction() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.zeroDollar()
                .setProcessorId("1")
                .setReferenceNum("123")
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateSaleWithCredit() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.sale()
                .setProcessorId("5")
                .setReferenceNum("Teste 012")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateSaleWithCredit3DS() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.sale()
                .setProcessorId("5")
                .setReferenceNum("Teste 012")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setAuthentication("41", Authentication.DECLINE)
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateSaleWithDebit() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.sale()
                .setProcessorId("1")
                .setReferenceNum("Teste 012")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setAuthentication("41", Authentication.DECLINE)
                .setDebitCard(
                        (new Card()).setNumber("4000000000000002")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateAuthWithAntiFraud() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.auth()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .setFraudCheck("Y")
                .setBilling(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setCompanyName("Uma empresa qualquer")
                                .setEmail("fulano@de.tal")
                                .addPhone(
                                        (new Phone()).setPhoneCountryCode("55")
                                                .setPhoneAreaCode("16")
                                                .setPhoneExtension("123")
                                                .setPhoneType("Residential")
                                                .setPhoneNumber("99999999"))
                                .addPhone(
                                        (new Phone()).setPhoneCountryCode("55")
                                                .setPhoneAreaCode("16")
                                                .setPhoneExtension("123")
                                                .setPhoneType("Commercial")
                                                .setPhoneNumber("99999999"))
                                .addDocument(
                                        (new Document()).setDocumentType("CPF")
                                                .setDocumentValue("11111111111")))

                .setShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal")
                                .addPhone(
                                        (new Phone()).setPhoneCountryCode("55")
                                                .setPhoneAreaCode("16")
                                                .setPhoneExtension("123")
                                                .setPhoneType("Residential")
                                                .setPhoneNumber("99999999"))
                                .addDocument(
                                        (new Document()).setDocumentType("CPF")
                                                .setDocumentValue("11111111111")))
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setPayment(new Payment(100.0))
                .addItem(1, "123", "Um item qualquer", 2, 10.0, 5.0)
                .addItem(2, "456", "Outro item qualquer", 2, 10.0, 5.0);

        TransactionResponse transactionResponse = maxiPago.transactionRequest().execute();

        System.out.println(transactionResponse.fraudScore);
    }

    void shouldCreateSaleWithAntiFraud() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.sale()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .setFraudCheck("Y")
                .setBilling(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setCompanyName("Uma empresa qualquer")
                                .setEmail("fulano@de.tal")
                                .addPhone(
                                        (new Phone()).setPhoneCountryCode("55")
                                                .setPhoneAreaCode("16")
                                                .setPhoneExtension("123")
                                                .setPhoneType("Residential")
                                                .setPhoneNumber("99999999"))
                                .addPhone(
                                        (new Phone()).setPhoneCountryCode("55")
                                                .setPhoneAreaCode("16")
                                                .setPhoneExtension("123")
                                                .setPhoneType("Commercial")
                                                .setPhoneNumber("99999999"))
                                .addDocument(
                                        (new Document()).setDocumentType("CPF")
                                                .setDocumentValue("11111111111")))

                .setShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal")
                                .addPhone(
                                        (new Phone()).setPhoneCountryCode("55")
                                                .setPhoneAreaCode("16")
                                                .setPhoneExtension("123")
                                                .setPhoneType("Residential")
                                                .setPhoneNumber("99999999"))
                                .addDocument(
                                        (new Document()).setDocumentType("CPF")
                                                .setDocumentValue("11111111111")))
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setPayment(new Payment(100.0))
                .addItem(1, "123", "Um item qualquer", 2, 10.0, 5.0)
                .addItem(2, "456", "Outro item qualquer", 2, 10.0, 5.0);

        TransactionResponse transactionResponse = maxiPago.transactionRequest().execute();

        System.out.println(transactionResponse.fraudScore);
    }

    void shouldCreateBoleto() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.boleto()
                .setProcessorId("12")
                .setReferenceNum("Teste 456")
                .setIpAddress("127.0.0.1")
                .setCustomerIdExt("999")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setBoleto(
                        (new Boleto()).setNumber("1212")
                                .setExpirationDate("2020-05-09")
                                .setInstructions("Algumas instruções aleatórias"))
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateOnlineDebit() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        // 17 - BRADESCO
        // 18 - ITAU

        maxiPago.onlineDebit()
                .setProcessorId("17")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setOnlineDebit(
                        (new OnlineDebit()).setParametersURL("param=123"))
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldRefundTransaction() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.refund()
                .setTransactionId("9044118");

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateRecurringPayment() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.recurringPayment()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setCreditCard(
                        (new Card()).setNumber("5448280000000007")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .setPayment(new Payment(100.0))
                .setRecurring((new Recurring()).setAction("new")
                        .setStartDate("2020-05-01")
                        .setPeriod(Recurring.MONTHLY)
                        .setFrequency(1)
                        .setLastDate("2020-12-31")
                        .setLastAmount(22.0)
                        .setFailureThreshold(15)
                        .setInstallments(7));

        maxiPago.transactionRequest().execute();
    }

    void shouldModifyRecurringPayment() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.modifyRecurring()
                .setOrderId("0A01048D:01717A02CF80:7FC7:5639916D")
                .setPaymentInfo("4716870704643843", "07", "2021", 50.0)
                .setRecurring((new Recurring()).setAction("enable")
                        .setProcessorID("1")
                        .setNextFireDate("2020-06-01")
                        .setPeriod(Recurring.QUARTERLY)
                        .setLastDate("2020-12-31")
                        .setLastAmount(22.0)
                        .setInstallments(10))
                .setBillingInfo((new Customer()).setName("Fulano de Tal")
                        .setAddress1("Rua dos bobos")
                        .setAddress2("0")
                        .setCity("Cidade")
                        .setState("SP")
                        .setZip("11111111")
                        .setCountry("BR")
                        .setPhone("11111111111")
                        .setEmail("fulano@de.tal"))
                .setShippingInfo((new Customer()).setName("Fulano de Tal")
                        .setAddress1("Rua dos bobos")
                        .setAddress2("0")
                        .setCity("Cidade")
                        .setState("SP")
                        .setZip("11111111")
                        .setCountry("BR")
                        .setPhone("11111111111")
                        .setEmail("fulano@de.tal"));

        maxiPago.apiRequest().execute();
    }

    void shouldCancelRecurringPayment() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.cancelRecurring()
                .setOrderId("0A01048D:01717A02CF80:7FC7:5639916D");

        maxiPago.apiRequest().execute();
    }

    void shouldCreateConsumer() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.addConsumer()
                .setCustomerIdExt("123")
                .setFirstName("Fulano")
                .setLastName("de Tal")
                .setAddress1("Rua dos bobos")
                .setAddress2("0")
                .setCity("Cidade")
                .setState("Estado")
                .setZip("11111111")
                .setCountry("BR")
                .setPhone("11111111111")
                .setEmail("fulano@de.tal")
                .setDob("01/01/1900")
                .setSex("M");

        maxiPago.apiRequest().execute();
    }

    void shouldUpdateConsumer() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.updateConsumer()
                .setCustomerId("3039749")
                .setCustomerIdExt("123")
                .setFirstName("Felisbino")
                .setLastName("de Lima")
                .setAddress1("Rua dos bobos")
                .setAddress2("0")
                .setCity("Cidade")
                .setState("Estado")
                .setZip("11111111")
                .setCountry("BR")
                .setPhone("11111111111")
                .setEmail("fulano@de.tal")
                .setDob("01/01/1900")
                .setSex("M");

        maxiPago.apiRequest().execute();
    }

    void shouldAddCardOnFile() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.addCardOnFile()
                .setCustomerId("3039749")
                .setCreditCardNumber("5448280000000007")
                .setExpirationMonth("12")
                .setExpirationYear("2025")
                .setBillingName("Fulano de tal")
                .setBillingAddress1("Av. Marcos Penteado de Ulhoa Rodrigues, 939")
                .setBillingAddress2("11 Andar")
                .setBillingCity("Barueri")
                .setBillingState("SP")
                .setBillingZip("06360040")
                .setBillingCountry("BR")
                .setBillingPhone("01121218536")
                .setBillingEmail("fulano@dominio.com")
                .setOnFilePermissions("ongoing")
                .setOnFileMaxChargeAmount(1000.0);

        maxiPago.apiRequest().execute();
    }

    void shouldCreateAuthWithToken() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.auth()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setToken(new Token("3039749", "35IbiFgNfJQ=", "123"))
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateSaleWithToken() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.sale()
                .setProcessorId("5")
                .setReferenceNum("Teste 012")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setToken(new Token("3039749", "35IbiFgNfJQ=", "123"))
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateZeroDollarTransactionAndSaveOnFile() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.zeroDollar()
                .setProcessorId("5")
                .setReferenceNum("123")
                .setCreditCard(
                        (new Card()).setNumber("4235647728025682")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .saveOnFile("3039749", "01/06/2021");

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateAuthAndSaveOnFile() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.auth()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setCreditCard(
                        (new Card()).setNumber("4235647728025682")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .saveOnFile("3039749", "01/06/2021")
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateSaleAndSaveOnFile() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.sale()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setCreditCard(
                        (new Card()).setNumber("4235647728025682")
                                .setExpMonth("12")
                                .setExpYear("2028")
                                .setCvvNumber("123"))
                .saveOnFile("3039749", "01/06/2021")
                .setPayment(new Payment(100.0));

        maxiPago.transactionRequest().execute();
    }

    void shouldCreateRecurringPaymentWithToken() throws PropertyException {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.recurringPayment()
                .setProcessorId("5")
                .setReferenceNum("Teste 123")
                .setIpAddress("127.0.0.1")
                .billingAndShipping(
                        (new Customer()).setName("Fulano de Tal")
                                .setAddress("Rua dos bobos")
                                .setAddress2("0")
                                .setDistrict("District")
                                .setCity("Cidade")
                                .setState("Estado")
                                .setPostalCode("11111111")
                                .setCountry("BR")
                                .setPhone("11111111111")
                                .setEmail("fulano@de.tal"))
                .setToken(new Token("3039749", "35IbiFgNfJQ=", "123"))
                .setPayment(new Payment(100.0))
                .setRecurring((new Recurring()).setAction("new")
                        .setStartDate("2020-05-01")
                        .setPeriod(Recurring.MONTHLY)
                        .setFrequency(1)
                        .setFirstAmount(10.0)
                        .setLastDate("2020-12-31")
                        .setLastAmount(22.0)
                        .setFailureThreshold(15)
                        .setInstallments(7));

        maxiPago.transactionRequest().execute();
    }

    void shouldDeleteCardOnFile() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.deleteCardOnFile()
                .setCustomerId("3039749")
                .setToken("35IbiFgNfJQ=");

        maxiPago.apiRequest().execute();
    }

    void shouldDeleteConsumer() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.deleteConsumer()
                .setCustomerId("3039749");

        maxiPago.apiRequest().execute();
    }

    void shouldConsultTransaction() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.consultTransaction("8704233");

        RApiResponse response = maxiPago.rapiRequest().execute();

        for (Record record : response.records) {
            System.out.println(record.transactionId);
        }
    }

    void shouldConsultOrder() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.consultOrder("0A0104BA:017270E43DE2:ACBF:0B4E365C");

        RApiResponse response = maxiPago.rapiRequest().execute();

        for (Record record : response.records) {
            System.out.println(record.orderId);
        }
    }

    @Test
    void shouldConsultOrderList() {
        MaxiPago maxiPago = new MaxiPago(Environment.sandbox(
                merchantId, merchantKey
        ));

        maxiPago.consultOrderList("lastmonth")
                .setPageSize(5)
                .setPageNumber(1);

        RApiResponse response = maxiPago.rapiRequest().execute();

        maxiPago.consultOrderList("lastmonth")
                .setPageSize(5)
                .setPageNumber(2)
                .setPageToken(response.resultSetInfo.pageToken);

        response = maxiPago.rapiRequest().execute();

        Assert.assertEquals(2, (int) response.resultSetInfo.pageNumber);
    }
}
