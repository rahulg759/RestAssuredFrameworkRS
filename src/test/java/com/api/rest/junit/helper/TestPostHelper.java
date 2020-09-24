package com.api.rest.junit.helper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.api.rest.api.helper.RestAPIHelper;
import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestPostHelper {


	@Test 
	public void testPostJSON() {

		String idJSON = (int)(1000*(Math.random())) + "";

		String jsonBody="{\r\n" + 
				" \"BrandName\": \"Apple\",\r\n" + 
				" \"Features\": {\r\n" + 
				"  \"Feature\": [\"16GB RAM\",\r\n" + 
				"  \"5TB Hard Drive\"]\r\n" + 
				" },\r\n" + 
				" \"Id\": "+idJSON+",\r\n" + 
				" \"LaptopName\": \"Macbook Laptops\"\r\n" + 
				"}";

		Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/json"); headers.put("Accept","application/json"); 
		RestResponse response= RestAPIHelper.performPostRequest("http://localhost:8087/laptop-bag/webapi/api/add", jsonBody,ContentType.APPLICATION_JSON, headers); 
		Assert.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
		System.out.println("Response body in JSON : \n"+response.getResponseBody());
		//validate the content 
		RestAPIHelper.performGetRequest("http://localhost:8087/laptop-bag/webapi/api/find/"+idJSON, headers);


		//create the instance of json 
		GsonBuilder builder=new GsonBuilder(); 
		Gson gson=builder.serializeNulls().create(); ResponseBody
		body=gson.fromJson(response.getResponseBody(), ResponseBody.class);

		Assert.assertEquals(idJSON, body.Id); Assert.assertEquals("Acer Laptops",
				body.LaptopName);

	}


	// Sending data into xml form and get the data into same format
	@Test
	public void testPostXML() throws IOException {

		int idXML =  (int) (1000 * (Math.random()));

		String xmlBody = "<Laptop>\r\n" + "    <BrandName>Dell</BrandName>\r\n" + "    <Features>\r\n"
				+ "        <Feature>8GB RAM</Feature>\r\n" + "        <Feature>1TB Hard Drive</Feature>\r\n"
				+ "    </Features>\r\n" + "    <Id>"+idXML+"</Id>\r\n" + "    <LaptopName>Macbook</LaptopName>\r\n"
				+ "</Laptop>";

		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Content-Type", "application/xml");
		headers.put("Accept", "application/xml");
		RestResponse response = RestAPIHelper.performPostRequest(
				"http://localhost:8087/laptop-bag/webapi/api/add", xmlBody, ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		System.out.println("Response body in XML : \n"+response.getResponseBody());
		// Added jackson-dataformat-xml for xml desirialization
		response=RestAPIHelper.performGetRequest("http://localhost:8087/laptop-bag/webapi/api/find/"+idXML, headers);

		XmlMapper xml=new XmlMapper();

		//Parameter in xml request in list form
		xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		ResponseBody body=xml.readValue(response.getResponseBody(), ResponseBody.class);

		Assert.assertEquals("Dell", body.BrandName);
		Assert.assertEquals("Macbook", body.LaptopName);

	}
}

