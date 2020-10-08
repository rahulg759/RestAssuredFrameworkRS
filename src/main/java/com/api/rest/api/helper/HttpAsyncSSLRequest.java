package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;

import com.api.rest.api.model.RestResponse;

public class HttpAsyncSSLRequest {


	/**
	 * @param args
	 * Step 1:- Create the HTTP Get/ HTTP Post / HTTP Delete / HTTP Put method
	 * Step 2:- Create the HTTP Client --> HttpAsyncClientBuilder Class
	 * Step 3:- Execute the HTTP method using the client
	 * Step 4:- Catch the response of execution 
	 * Step 5:- Display the response at the console 
	 */

	static int id =(int)(1000*(Math.random()));

	public static void main(String[] args) {		

		String jsonBody="{\r\n" + 
				" \"BrandName\": \"Acer\",\r\n" + 
				" \"Features\": {\r\n" + 
				"  \"Feature\": [\"16GB RAM\",\r\n" + 
				"  \"2TB Hard Drive\"]\r\n" + 
				" },\r\n" + 
				" \"Id\": "+id+",\r\n" + 
				" \"LaptopName\": \"Acer Laptops\"\r\n" + 
				"}";

		Map<String,String> headers=new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Accept", "application/json");

		RestResponse postresponse=	HttpAsyncClientHelper.performPostRequestAsync("http://localhost:8087/laptop-bag/webapi/sslres/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		System.out.println(postresponse.getStatusCode());
		System.out.println(postresponse.getResponseBody());


		RestResponse getresponse=HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8087/laptop-bag/webapi/sslres/all", headers);
		System.out.println(getresponse.toString());


		//put method
		String updateJsonBody="{\r\n" + 
				" \"BrandName\": \"Dell\",\r\n" + 
				" \"Features\": {\r\n" + 
				"  \"Feature\": [\"8GB RAM\",\r\n" + 
				"  \"1TB Hard Drive\"]\r\n" + 
				" },\r\n" + 
				" \"Id\": "+id+",\r\n" + 
				" \"LaptopName\": \"Latitude\"\r\n" + 
				"}";

		RestResponse putresponse=HttpAsyncClientHelper.performPutRequestAsync("http://localhost:8087/laptop-bag/webapi/sslres/update", updateJsonBody, ContentType.APPLICATION_JSON, headers);
		System.out.println(putresponse.toString());

		RestResponse getresponse1=HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8087/laptop-bag/webapi/sslres/find/"+id, headers);
		System.out.println(getresponse1.toString());

		RestResponse deleteresponse=HttpAsyncClientHelper.performDeleteRequestAsync("http://localhost:8087/laptop-bag/webapi/sslres/delete/"+id, null);
		System.out.println(deleteresponse.toString());

		RestResponse getresponse2=HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8087/laptop-bag/webapi/sslres/find/"+id, headers);
		System.out.println(getresponse2.toString());

	}
}
