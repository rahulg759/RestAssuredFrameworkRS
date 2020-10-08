package com.api.rest.junit.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.api.rest.api.helper.HttpAsyncClientHelper;
import com.api.rest.api.helper.HttpsClientHelper;
import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAsyncRequest {

	/**
	 * @param args
	 * Step 1:- Create the HTTP Get/ HTTP Post / HTTP Delete / HTTP Put method
	 * Step 2:- Create the HTTP Client --> HttpAsyncClientBuilder Class
	 * Step 3:- Execute the HTTP method using the client
	 * Step 4:- Catch the response of execution 
	 * Step 5:- Display the response at the console 
	 */
	

	static int id =(int)(1000*(Math.random()));

	String jsonBody="{\r\n" + 
			" \"BrandName\": \"AcerTest\",\r\n" + 
			" \"Features\": {\r\n" + 
			"  \"Feature\": [\"16GB RAM\",\r\n" + 
			"  \"2TB Hard Drive\"]\r\n" + 
			" },\r\n" + 
			" \"Id\": "+id+",\r\n" + 
			" \"LaptopName\": \"Acer Laptops\"\r\n" + 
			"}";

	@Test
	public void a_testPostAsyncRequest() {		

		System.out.println("This is a post request : ");

		Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");

		RestResponse postresponse = HttpAsyncClientHelper.performPostRequestAsync("http://localhost:8087/laptop-bag/webapi/api/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		System.out.println(postresponse.toString());
		Assert.assertEquals(HttpStatus.SC_OK,postresponse.getStatusCode());
	}

	@Test
	public void b_testGetAsyncRequest() {
		System.out.println("\nThis is a get request : ");
		RestResponse getresponse = HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8087/laptop-bag/webapi/api/all", null);
		System.out.println(getresponse.toString());
		Assert.assertEquals(HttpStatus.SC_OK,getresponse.getStatusCode());
	}

	@Test
	public void c_testGetAsyncRequestID() {
		//this.id=id;
		System.out.println("\nThis is a get request based on id : ");
		RestResponse getresponseid = HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8087/laptop-bag/webapi/api/find/"+id, null);
		System.out.println(getresponseid.toString());
		Assert.assertEquals(HttpStatus.SC_OK,getresponseid.getStatusCode());
	}

	@Test
	public void d_testPutAsyncRequest() {
		
		String updatejsonBody="{\r\n" + 
				" \"BrandName\": \"Dell\",\r\n" + 
				" \"Features\": {\r\n" + 
				"  \"Feature\": [\"32GB RAM\",\r\n" + 
				"  \"8TB Hard Drive\"]\r\n" + 
				" },\r\n" + 
				" \"Id\": "+id+",\r\n" + 
				" \"LaptopName\": \"Dell Laptops\"\r\n" + 
				"}";
		
		System.out.println("\nThis is a put request : ");
		//File f=new File("D:\\My_Workspace\\projectwork\\RestAssuredFrameworkRS\\TestDataFile");

		Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");

		RestResponse putresponse=HttpAsyncClientHelper.performPutRequestAsync("http://localhost:8087/laptop-bag/webapi/api/update",updatejsonBody, ContentType.APPLICATION_JSON, headers);
		System.out.println(putresponse.toString());
		Assert.assertEquals(HttpStatus.SC_OK,putresponse.getStatusCode());
		
		//Parameters verification from respose body using Gson
		GsonBuilder builder = new GsonBuilder();
		Gson gson=builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body=gson.fromJson(putresponse.getResponseBody(), ResponseBody.class);
		Assert.assertEquals("Dell Laptops", body.LaptopName);
	}


	@Test
	public void e_testGetAsyncRequestID1() {
		System.out.println("\nThis is a get request based on id : ");
		RestResponse getresponseid1 = HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8087/laptop-bag/webapi/api/find/"+id, null);
		System.out.println(getresponseid1.toString());
		Assert.assertEquals(HttpStatus.SC_OK,getresponseid1.getStatusCode());
	}

	@Test
	public void f_testDeleteAsyncRequestID() {
		System.out.println("\nThis is a delete request based on id : ");

		Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/json");

		RestResponse getresponseid2 = HttpAsyncClientHelper.performDeleteRequestAsync("http://localhost:8087/laptop-bag/webapi/api/delete/"+id, headers);
		System.out.println(getresponseid2.toString());
		Assert.assertEquals(HttpStatus.SC_OK,getresponseid2.getStatusCode());
	}

	@Test
	public void g_testGetAsyncRequestID2() {
		System.out.println("\nThis is a get request based on id, verifying after deletion : ");
		RestResponse getresponseid3 = HttpAsyncClientHelper.performGetRequestAsync("http://localhost:8087/laptop-bag/webapi/api/find/"+id, null);
		System.out.println(getresponseid3.toString());
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND,getresponseid3.getStatusCode());
	}
}
