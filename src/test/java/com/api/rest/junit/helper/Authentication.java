package com.api.rest.junit.helper;

import org.apache.commons.codec.binary.*;
import org.apache.http.entity.ContentType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.api.rest.api.helper.RestAPIHelper;
import com.api.rest.api.model.RestResponse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Authentication {

	@Test
	public void b_testSecureGet() {
		System.out.println("This is a get request");
		Map<String,String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		//headers.put("Authorization", "Basic YWRtaW46d2VsY29tZQ==");

		//send credentials in encoded64 form
		headers.put("Authorization",Base64.encodeBase64String("admin:welcome".getBytes()));

		RestResponse response=RestAPIHelper.performGetRequest("http://localhost:8087/laptop-bag/webapi/secure/all", headers);
		System.out.println(response.toString());
	}
	
	@Test
	public void c_testSecurePut() {
		System.out.println("This is a put request");
		Map<String,String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("Authorization", "Basic YWRtaW46d2VsY29tZQ==");

		File f=new File("D:\\My_Workspace\\projectwork\\RestAssuredFrameworkRS\\TestFileUpdate");

		RestResponse response=RestAPIHelper.performPutRequest("http://localhost:8087/laptop-bag/webapi/secure/update",f,ContentType.APPLICATION_JSON,headers);
		System.out.println(response.toString());
	}

	@Test
	public void a_testSecurePost() {
		System.out.println("This is a post request");
		Map<String,String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("Authorization", "Basic YWRtaW46d2VsY29tZQ==");

		File f=new File("D:\\My_Workspace\\projectwork\\RestAssuredFrameworkRS\\TestDataFile");

		RestResponse response=RestAPIHelper.performPostRequest("http://localhost:8087/laptop-bag/webapi/secure/add",f,ContentType.APPLICATION_JSON,headers);
		System.out.println(response.toString());
	}

	@Test
	public void d_testSecureGetUsingID() {
		System.out.println("This is a get request based on id request");
		Map<String,String> headers=new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("Authorization", "Basic YWRtaW46d2VsY29tZQ==");
		RestResponse response=RestAPIHelper.performGetRequest("http://localhost:8087/laptop-bag/webapi/secure/find/130", headers);
		System.out.println(response.toString());
	}

	@Test
	public void e_testSecureDeleteUsingID() {
		System.out.println("This is a delete request");
		Map<String,String> headers=new HashMap<String, String>();
		//Don't send Accept parameter in Delete request, send only Authorization and Content-Type
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Basic YWRtaW46d2VsY29tZQ==");
		RestResponse response=RestAPIHelper.performDeleteRequest("http://localhost:8087/laptop-bag/webapi/secure/delete/130", headers);
		System.out.println(response.toString());
		d_testSecureGetUsingID();
	}

}

