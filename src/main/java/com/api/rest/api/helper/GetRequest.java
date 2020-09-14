package com.api.rest.api.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class GetRequest {

	/*@param arguments
	step 1 : Create the Http Get method
	step 2 : Create the Http Client
	step 3 : Execute the Http method using the Client
	step 4 : Catch the response of execution
	step 5 : Display the response a the console
	 */

	public static void main(String[] args) {

		/*try {
			//step 1
			HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/api/ping/hello");

			// Step 2 - HttpClient Builder allow to create customized client to us.
			CloseableHttpClient client = HttpClientBuilder.create().build();

			//step 3
			CloseableHttpResponse response = client.execute(get);
			StatusLine statusLine=response.getStatusLine();

			System.out.println(statusLine.getStatusCode());
			System.out.println(statusLine.getProtocolVersion());

			client.close();
			response.close();

		}*/


		// Its java 8 feature for auto closable interface

		//step 1
		HttpGet get = new HttpGet("http://localhost:8087/laptop-bag/webapi/api/all");
		try(// Step 2 - HttpClient Builder allow to create customized client to us.
				CloseableHttpClient client = HttpClientBuilder.create().build();
				//step 3
				CloseableHttpResponse response = client.execute(get)
				) {
			/*StatusLine statusLine=response.getStatusLine();

			System.out.println(statusLine.getStatusCode());
			System.out.println(statusLine.getProtocolVersion());
			 */

			//Print response body
			ResponseHandler<String> body= new BasicResponseHandler();
			RestResponse rs=new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			//System.out.println(rs.toString());


			//Call to RestAPIHelper.java class
			//RestAPIHelper.performGetRequest("http://localhost:8087/laptop-bag/webapi/api/all",null);


			//Call to RestReponse class from RestAPIHelper.java class
			RestResponse rs1=RestAPIHelper.performGetRequest("http://localhost:8087/laptop-bag/webapi/api/all",null);
			System.out.println(rs1.toString());
			

			/*String getBody=body.handleResponse(response);

			System.out.println("Body of API : "+getBody);*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
