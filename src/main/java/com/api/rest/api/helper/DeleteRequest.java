package com.api.rest.api.helper;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class DeleteRequest {

	public static void main(String[] args) throws IOException {

		/*@param arguments
		step 1 : Create the Http Delete method
		step 2 : Create the Http Client
		step 3 : Execute the Http method using the Client
		step 4 : Catch the response of execution
		step 5 : Display the response a the console
		 */


		//Step 1- we use RequestBuilder because it have all type requests - get, post, delete, put etc.
		/*HttpUriRequest delete=RequestBuilder.delete("http://localhost:8087/laptop-bag/webapi/api/delete/821").build();

		HttpGet get = new HttpGet("http://localhost:8087/laptop-bag/webapi/api/all");
		try(// Step 2 - HttpClient Builder allow to create customized client to us.
				CloseableHttpClient client = HttpClientBuilder.create().build();
				//step 3
				CloseableHttpResponse response = client.execute(delete)) {
			StatusLine statusLine=response.getStatusLine();

					System.out.println(statusLine.getStatusCode());
					System.out.println(statusLine.getProtocolVersion());


			//Print response body
			ResponseHandler<String> body= new BasicResponseHandler();
			RestResponse restresponse=new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			System.out.println(restresponse.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		 */


		RestResponse response=RestAPIHelper.performDeleteRequest("http://localhost:8087/laptop-bag/webapi/api/delete/331", null);
		System.out.println(response.toString());
	}
}