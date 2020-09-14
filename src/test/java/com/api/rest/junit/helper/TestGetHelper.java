package com.api.rest.junit.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.helper.RestAPIHelper;
import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGetHelper {

	@Test
	public void testGetPingAlive() {
		String url = "http://localhost:8087/laptop-bag/webapi/api/ping/Rahul";
		RestResponse response = RestAPIHelper.performGetRequest(url, null);
		// System.out.println(response.toString());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		Assert.assertEquals("Hi! Rahul", response.getResponseBody());
		System.out.println(response.getResponseBody());
	}

	@Test
	public void testGetFindID() {
		String url = "http://localhost:8087/laptop-bag/webapi/api/find/127";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestAPIHelper.performGetRequest(url, headers);
		// System.out.println(response.toString());
		Assert.assertTrue("Expected status code is not present",
				HttpStatus.SC_OK == response.getStatusCode() || (HttpStatus.SC_NOT_FOUND == response.getStatusCode()));
		System.out.println(response.getResponseBody());
		// Assert.assertEquals("null", response.getResponseBody());

		// Serialization & Deserialization
		/*
		 * Step 1 - Use GSON builder class to get the instance of GSON class. 
		 * Step 2 - Use the GSON object to deserialize the json
		 */

		// If any parameter is coming as null by this serializeNulls() set as null
		//Note - the below code Gson put in testGetFindID() because it supports only JSON response
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();

		ResponseBody body=gson.fromJson(response.getResponseBody(),ResponseBody.class);
		System.out.println(body.BrandName);
		Assert.assertEquals("HP", body.BrandName);
	}

	@Test
	public void testGetAll() {
		String url = "http://localhost:8087/laptop-bag/webapi/api/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestAPIHelper.performGetRequest(url, headers);
		/*
		 * System.out.println(response.toString());
		 * Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		 */
		Assert.assertTrue("Expected status code is not present",
				HttpStatus.SC_OK == response.getStatusCode() || (HttpStatus.SC_NO_CONTENT == response.getStatusCode()));
		//System.out.println(response.getResponseBody());
		// Assert.assertEquals("null", response.getResponseBody());
	}

}
