package com.api.rest.api.helper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;

import com.api.rest.api.model.RestResponse;


public class PostRequest {

	public static void main(String[] args) {

		/*@param arguments
		step 1 : Create the Http Post method
		step 2 : Create the Http Client
		step 3 : Execute the Http method using the Client
		step 4 : Catch the response of execution
		step 5 : Display the response a the console
		 */

		int id =(int)(1000*(Math.random()));
		
		String jsonBody="{\r\n" + 
				" \"BrandName\": \"AcerTest\",\r\n" + 
				" \"Features\": {\r\n" + 
				"  \"Feature\": [\"16GB RAM\",\r\n" + 
				"  \"2TB Hard Drive\"]\r\n" + 
				" },\r\n" + 
				" \"Id\": "+id+",\r\n" + 
				" \"LaptopName\": \"Acer Laptops\"\r\n" + 
				"}";

		/*
		    HttpPost post = new HttpPost("http://localhost:8087/laptop-bag/webapi/api/add");
		    try(CloseableHttpClient client=HttpClientBuilder.create().build()) {
			post.addHeader("Content-Type", "application/json");
			post.addHeader("Accept", "application/json");

			//Add request body in String Entity Type
			StringEntity data = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
			post.setEntity(data);

			//Add the request body in File Entity object type (TestDataFile)
			File f=new File("D:\\My_Workspace\\projectwork\\RestAssuredFrameworkRS\\TestDataFile");
			FileEntity file=new FileEntity(f,ContentType.APPLICATION_JSON);
			post.setEntity(file);

			CloseableHttpResponse response=client.execute(post);
			ResponseHandler<String> handler=new BasicResponseHandler();
			RestResponse restResponse=new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));

			//Print only response body
			//System.out.println(restResponse.getResponseBody());

			System.out.println(restResponse.toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
		 */

		/*Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		RestAPIHelper.performPostRequest("http://localhost:8087/laptop-bag/webapi/api/add", jsonBody, headers);*/


		// One way to pass content in Object form
		/*Map<String,String> headers1 = new LinkedHashMap<String,String>();
		headers1.put("Content-Type", "application/json");
		headers1.put("Accept", "application/json");
		RestAPIHelper.performPostRequestGeneric("http://localhost:8087/laptop-bag/webapi/api/add", jsonBody,ContentType.APPLICATION_JSON, headers1);*/


		// Two way pass the content from file
		Map<String,String> headers1 = new LinkedHashMap<String,String>();
		headers1.put("Content-Type", "application/json");
		headers1.put("Accept", "application/json");

		File f=new File("D:\\My_Workspace\\projectwork\\RestAssuredFrameworkRS\\TestDataFile");
		//FileEntity file=new FileEntity(f,ContentType.APPLICATION_JSON);

		RestResponse response=	RestAPIHelper.performPostRequest("http://localhost:8087/laptop-bag/webapi/api/add", f, ContentType.APPLICATION_JSON, headers1);
		System.out.println(response.getStatusCode());
		System.out.println(response.getResponseBody());
	}
}
