package maxiPago.Gateway;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import maxiPago.DataContract.ResponseBase;
import maxiPago.DataContract.NonTransactional.ApiRequest;
import maxiPago.DataContract.NonTransactional.ApiResponse;
import maxiPago.DataContract.Reports.RapiRequest;
import maxiPago.DataContract.Reports.RapiResponse;
import maxiPago.DataContract.Reports.Record;
import maxiPago.DataContract.Reports.Records;
import maxiPago.DataContract.Transactional.ErrorResponse;
import maxiPago.DataContract.Transactional.Item;
import maxiPago.DataContract.Transactional.Order;
import maxiPago.DataContract.Transactional.Phone;
import maxiPago.DataContract.Transactional.TransactionRequest;
import maxiPago.DataContract.Transactional.TransactionResponse;
import maxiPago.DataContract.Transactional.customerDocument;

class Utils {
	
	//Envia Requisicao
    public ResponseBase SendRequest(Object request, String environment) throws Exception {

		if(request == null)
			throw new Exception("The Request can not be null or empty");
		
		String xml = ToXml(request);		
		
		//Pega a URL de acordo com o que foi passado.
		String url = GetUrl(request, environment);
					
		String responseContent = Post(xml, url); //Salvar XML de retorno
		
		SaveRequest.log(xml, responseContent, url);

		if(environment.toLowerCase().equals("test")){
			System.out.println("Post: " + url);
			System.out.println("\nRequisicao: " + xml);
			System.out.println("\nResponse: " + responseContent);
		}

		return ParseResponse(responseContent);
	}
    
    /*
     * 
     */
    private String Post(String xml, String url) throws IOException {
    	
    	boolean bHTTPS = false;
		String sResponse = "";

		System.setProperty("java.protocol.handler.pkgs", "javax.net.ssl");

		if (url.startsWith("https:"))
			bHTTPS = true;

		HttpURLConnection httpConn = null;
		HttpsURLConnection httpsConn = null;

		DataOutputStream dos = null;
		InputStream istr = null;
		BufferedReader reader = null;

		try {
			URL ul = new URL(url);
			if (bHTTPS) {
				httpsConn = (HttpsURLConnection) ul.openConnection();
				httpsConn.setDoOutput(true);
				httpsConn.setDoInput(true);
				httpsConn.setUseCaches(false);

				httpsConn.setRequestMethod("POST");
				httpsConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
				httpsConn.setRequestProperty("Content-Length", Integer.toString(xml.getBytes("UTF-8").length));
				dos = new DataOutputStream(httpsConn.getOutputStream());
			} else {
				httpConn = (HttpURLConnection) ul.openConnection();
				httpConn.setDoOutput(true);
				httpConn.setDoInput(true);
				httpConn.setRequestMethod("POST");
				httpConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
				httpConn.setRequestProperty("Content-Length", Integer.toString(xml.getBytes("UTF-8").length));
				dos = new DataOutputStream(httpConn.getOutputStream());
			}

			dos.write(xml.getBytes("UTF-8"), 0, xml.getBytes("UTF-8").length);
			dos.flush();

			if (bHTTPS)
				istr = httpsConn.getInputStream();
			else
				istr = httpConn.getInputStream();

			reader = new BufferedReader(new InputStreamReader(istr, "UTF-8"));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			sResponse = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (istr != null)
				istr.close();
			if (reader != null)
				reader.close();
			if (dos != null)
				dos.close();

			if (httpsConn != null)
				httpsConn.disconnect();
			else if (httpConn != null)
				httpConn.disconnect();
		}
		return sResponse;    	
    }
    
    /*
     * 
     */
	private String ToXml(Object request) {

		XStream xstream = new XStream(new DomDriver());
        
		xstream.autodetectAnnotations(true);
		
		xstream.alias("transaction-request", TransactionRequest.class);
		xstream.aliasField("void", Order.class, "_void");
		xstream.aliasField("return", Order.class, "_return");
		xstream.alias("api-request", ApiRequest.class);
		xstream.alias("item", Item.class);
		xstream.alias("phone", Phone.class);
		xstream.alias("document", customerDocument.class);
		xstream.alias("rapi-request", RapiRequest.class);
		
		String xml = xstream.toXML(request);
		
		return xml;
	}
	
	/*
	 * 
	 */
	private String GetUrl(Object request, String environment) throws Exception {

		int type = 0;
		
		if("LIVE".equals(environment))
			type = 1;
		else if("TEST".equals(environment))
			type = 2;
		
        switch (type) {
            case 1:
                if(request instanceof TransactionRequest)
                    return "https://api.maxipago.net/UniversalAPI/postXML";
                else if (request instanceof ApiRequest)
                    return "https://api.maxipago.net/UniversalAPI/postAPI";
                else if (request instanceof RapiRequest)
                    return "https://api.maxipago.net/ReportsAPI/servlet/ReportsAPI";
                break;
            case 2:

                if(request instanceof TransactionRequest)
                    return "https://testapi.maxipago.net/UniversalAPI/postXML";
                else if (request instanceof ApiRequest)
                    return "https://testapi.maxipago.net/UniversalAPI/postAPI";
                else if (request instanceof RapiRequest)
                    return "https://testapi.maxipago.net/ReportsAPI/servlet/ReportsAPI";
                break;
            
        }

        throw new Exception("You must to inform the environment. (TEST or LIVE)");
    }
		
	/*
	 * 
	 */
	private ResponseBase ParseResponse(String responseContent) throws Exception {

		if(responseContent == null)
			return null;		
		
		XStream xstream = new XStream(new DomDriver()) {
			   @Override
			    protected MapperWrapper wrapMapper(MapperWrapper next) {
			        return new MapperWrapper(next) {
			            @Override
			            public boolean shouldSerializeMember(@SuppressWarnings("rawtypes") Class definedIn, String fieldName) {
			             if (definedIn == Object.class) {
			               try {
			                 return this.realClass(fieldName) != null;
			               } catch(Exception e) {
			                 return false;
			               }
			             } else {
			                    return super.shouldSerializeMember(definedIn, fieldName);
			                }
			            }
			         };
			      }
			   };
		
		TransactionResponse response = new TransactionResponse();
		long transactionTimestampLong = 0;
		long parseTransactionTimestamp = 0;
		
        if (responseContent.contains("transaction-response")) {
        	
        	xstream.alias("transaction-response", TransactionResponse.class);
        	xstream.aliasField("save-on-file", TransactionResponse.class, "saveOnFile");
        	    	
        	response = (TransactionResponse)xstream.fromXML(responseContent);
        	
        	String transactionTimestampStr = response.getTransactionTimestamp();
        	
        	//Hack to correct the TransactionTimestamp if multiplied by 1000
        	if(transactionTimestampStr.length() > 10){
        		
        		transactionTimestampLong = new Long(transactionTimestampStr);
        		parseTransactionTimestamp = transactionTimestampLong / 1000;
        		response.setTransactionTimestamp(String.valueOf(parseTransactionTimestamp));	
        	}
        	        	
			return response;	
        }
        else if (responseContent.contains("rapi-response")) {
        	
        	String [] teste = responseContent.split("<record>");
        	
        	String correctResponse = null;
        	
        	//The code below inside "for" is necessary to hack the response error when the response came more than one field customerId in the same xml node.
        	for(int i = 0; i<teste.length; i ++) {
        		
        		if(i==0) {
        			correctResponse = teste[i];
        			continue;
        		}
        		
        		String record = "<record>" + teste[i].replaceFirst("<customerId>", "<customerId1>");
        		record = record.replaceFirst("</customerId>", "</customerId1>");
            	record = record.replaceFirst("<customerId/>", "<customerId1/>");
            	
            	correctResponse += record;        		
        	}
        	
        	xstream.alias("rapi-response", RapiResponse.class);
        	xstream.addImplicitCollection(Records.class, "record", "record", Record.class);
			return (RapiResponse) xstream.fromXML(correctResponse);			
        }
        else if (responseContent.contains("api-error")) {
        	
        	xstream.alias("api-error", ErrorResponse.class);
			return (ErrorResponse) xstream.fromXML(responseContent);
			
        }
        else if (responseContent.contains("api-response")) {
        	
        	xstream.alias("api-response", ApiResponse.class);
			return (ApiResponse) xstream.fromXML(responseContent);
			
        }
        else
            throw new Exception("Unexpected response was received.");
    }
}