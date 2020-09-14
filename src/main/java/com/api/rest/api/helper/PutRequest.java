package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class PutRequest {

	public static void main(String[] args) {

		/*@param arguments
		step 1 : Create the Http Delete method
		step 2 : Create the Http Client
		step 3 : Execute the Http method using the Client
		step 4 : Catch the response of execution
		step 5 : Display the response a the console
		 */

		String xmlBody="<Laptop>\r\n" + 
				"    <BrandName>Dell</BrandName>\r\n" + 
				"    <Features>\r\n" + 
				"        <Feature>8GB RAM</Feature>\r\n" + 
				"        <Feature>1TB Hard Drive</Feature>\r\n" + 
				"        <Feature>This is Put</Feature>\r\n" + 
				"        <Feature>2 GB SSD</Feature>\r\n" + 
				"        <Feature>Latest Model</Feature>\r\n" + 
				"    </Features>\r\n" + 
				"    <Id>1880</Id>\r\n" + 
				"    <LaptopName>Macbook L Series</LaptopName>\r\n" + 
				"</Laptop>";

		/*RequestBuilder buildput=RequestBuilder.put("http://localhost:8087/laptop-bag/webapi/api/update").setHeader("Content-Type", "application/xml")
				.setHeader("Accept", "application/xml");
		HttpUriRequest put=buildput.setEntity(new StringEntity(xmlBody,ContentType.APPLICATION_XML)).build();

		try(CloseableHttpClient client=HttpClientBuilder.create().build();
				CloseableHttpResponse response=client.execute(put)) {

			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restresponse = new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(restresponse.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}*/

		Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/xml");
		headers.put("Accept", "application/xml");

		RestResponse response=RestAPIHelper.performPutRequest("http://localhost:8087/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		System.out.println(response.toString());
	}
}
