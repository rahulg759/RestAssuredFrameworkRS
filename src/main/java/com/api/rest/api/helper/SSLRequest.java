package com.api.rest.api.helper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;

import com.api.rest.api.model.RestResponse;

public class SSLRequest {

	public static void main(String[] args) {

		//=========================== This is a post request ===============================//

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


		Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");

		System.out.println("This is a post request : ");
		RestResponse postresponse = HttpsClientHelper.performPostRequest("https://localhost:8443/laptop-bag/webapi/sslres/add", jsonBody, ContentType.APPLICATION_JSON, headers);
		System.out.println(postresponse.toString());
		Assert.assertEquals(HttpStatus.SC_OK,postresponse.getStatusCode());

		//=========================== This is a get request ===============================//

		System.out.println("\nThis is a get request : ");
		RestResponse getresponse = HttpsClientHelper.performGetRequestWithSSL("http://localhost:8087/laptop-bag/webapi/sslres/all", null);
		System.out.println(getresponse.toString());
		Assert.assertEquals(HttpStatus.SC_OK,postresponse.getStatusCode());

		//=========================== This is a get request based on id ===============================//

		System.out.println("\nThis is a get request based on id : ");
		RestResponse getresponseid = HttpsClientHelper.performGetRequestWithSSL("http://localhost:8087/laptop-bag/webapi/sslres/find/"+id, null);
		System.out.println(getresponseid.toString());
		Assert.assertEquals(HttpStatus.SC_OK,postresponse.getStatusCode());

		//=========================== This is a put request ===============================//

		System.out.println("\nThis is a put request : ");
		File f=new File("D:\\My_Workspace\\projectwork\\RestAssuredFrameworkRS\\TestDataFile");

		RestResponse putresponse=HttpsClientHelper.performPutRequest("https://localhost:8443/laptop-bag/webapi/sslres/update",f, ContentType.APPLICATION_JSON, headers);
		System.out.println(putresponse.toString());
		Assert.assertEquals(HttpStatus.SC_OK,postresponse.getStatusCode());

		//=========================== This is a get request based on id ===============================//

		System.out.println("\nThis is a get request based on id : ");
		RestResponse getresponseid1 = HttpsClientHelper.performGetRequestWithSSL("http://localhost:8087/laptop-bag/webapi/sslres/find/"+id, null);
		System.out.println(getresponseid1.toString());
		Assert.assertEquals(HttpStatus.SC_OK,postresponse.getStatusCode());

		//=========================== This is a delete request based on id ===============================//

		//In delete request we should not pass Accept parameter
		Map<String,String> header = new LinkedHashMap<String,String>();
		header.put("Content-Type", "application/json");

		System.out.println("\nThis is a delete request based on id : ");
		RestResponse deleteresponse = HttpsClientHelper.performDeleteRequest("https://localhost:8443/laptop-bag/webapi/sslres/delete/"+id, header);
		System.out.println(deleteresponse.toString());
		Assert.assertEquals(HttpStatus.SC_OK,postresponse.getStatusCode());

		//=========================== This is a get request based on id after deletion ===============================//

		System.out.println("\nThis is a get request based on id : ");
		RestResponse getrequestidafterdelete = HttpsClientHelper.performGetRequestWithSSL("https://localhost:8443/laptop-bag/webapi/sslres/find/"+id, null);
		System.out.println(getrequestidafterdelete.toString());
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND,postresponse.getStatusCode());
	}
}
