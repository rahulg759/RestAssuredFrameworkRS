package com.api.rest.junit.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.helper.RestAPIHelper;
import com.api.rest.api.model.RestResponse;

public class TestDeleteHelper {

	/*Step 1- I will Post the data and validate the as status 200 OK
	Step 2- Call delete point to delete the above posted data, validate for 200 OK
	Step 3- Call the get endpoint, it should return 404 not found
	Step 4 - Again call the delete end point with same id (one level of validation), expected will be 404 not found
	*/

	@Test
	public void testDelete() {

		int idDelete =  (int) (1000 * (Math.random()));

		String xmlBody = "<Laptop>\r\n" + "    <BrandName>Dell</BrandName>\r\n" + "    <Features>\r\n"
				+ "        <Feature>8GB RAM</Feature>\r\n" + "        <Feature>1TB Hard Drive</Feature>\r\n"
				+ "    </Features>\r\n" + "    <Id>"+idDelete+"</Id>\r\n" + "    <LaptopName>Macbook</LaptopName>\r\n"
				+ "</Laptop>";

		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "application/xml");
		headers.put("Accept", "application/xml");
		RestResponse response = RestAPIHelper.performPostRequestGeneric(
				"http://localhost:8087/laptop-bag/webapi/api/add", xmlBody, ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		System.out.println("Response body in XML : \n"+response.getResponseBody());

		//Delete id
		response=RestAPIHelper.performDeleteRequest("http://localhost:8087/laptop-bag/webapi/api/delete/"+idDelete, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		System.out.println(response.getStatusCode());

		//verify id
		response=RestAPIHelper.performDeleteRequest("http://localhost:8087/laptop-bag/webapi/api/find/"+idDelete, null);
		System.out.println(response.getStatusCode());
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
		System.out.println(response.getStatusCode());
	}
}
