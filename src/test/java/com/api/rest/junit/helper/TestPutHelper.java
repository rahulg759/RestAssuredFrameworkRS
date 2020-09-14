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

public class TestPutHelper {


	/*Step 1 - Call the Post method, validation will be 200 OK
	Step 2 - Call the PUT method which will update the content, 200 OK
	Step 3 - Call the Get endpoint to validate the output, content validation*/

	@Test 
	public void testPutXMLJSON() throws IOException {

		String id = (int)(1000*(Math.random())) + "";

		String jsonBody="{\r\n" + 
				" \"BrandName\": \"Apple\",\r\n" + 
				" \"Features\": {\r\n" + 
				"  \"Feature\": [\"16GB RAM\",\r\n" + 
				"  \"5TB Hard Drive\"]\r\n" + 
				" },\r\n" + 
				" \"Id\": "+id+",\r\n" + 
				" \"LaptopName\": \"Macbook Laptops\"\r\n" + 
				"}";

		String xmlBody="<Laptop>\r\n" + 
				"    <BrandName>Dell</BrandName>\r\n" + 
				"    <Features>\r\n" + 
				"        <Feature>8GB RAM</Feature>\r\n" + 
				"        <Feature>1TB Hard Drive</Feature>\r\n" + 
				"        <Feature>This is Put</Feature>\r\n" + 
				"        <Feature>2 GB SSD</Feature>\r\n" + 
				"        <Feature>Latest Model</Feature>\r\n" + 
				"    </Features>\r\n" + 
				"    <Id>"+id+"</Id>\r\n" + 
				"    <LaptopName>Macbook L Series</LaptopName>\r\n" + 
				"</Laptop>";

		Map<String,String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type", "application/json"); 
		headers.put("Accept","application/json"); 
		RestResponse response= RestAPIHelper.performPostRequestGeneric("http://localhost:8087/laptop-bag/webapi/api/add", jsonBody,ContentType.APPLICATION_JSON, headers); 
		Assert.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
		headers.clear();
		headers.put("Content-Type", "application/xml"); 
		headers.put("Accept","application/xml"); 
		response=RestAPIHelper.performPutRequest("http://localhost:8087/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_OK,response.getStatusCode());
		response=RestAPIHelper.performGetRequest("http://localhost:8087/laptop-bag/webapi/api/find/"+id, headers);

		// Added jackson-dataformat-xml for xml desirialization
		XmlMapper xml=new XmlMapper();

		//Parameter in xml request in list form
		xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		ResponseBody body=xml.readValue(response.getResponseBody(), ResponseBody.class);

		Assert.assertEquals("Dell", body.BrandName);
		Assert.assertEquals("Macbook L Series", body.LaptopName);
	}



	@Test 
	public void testPutNotFound() throws IOException {

		String id = (int)(1000*(Math.random())) + "";

		String xmlBody="<Laptop>\r\n" + 
				"    <BrandName>Dell</BrandName>\r\n" + 
				"    <Features>\r\n" + 
				"        <Feature>8GB RAM</Feature>\r\n" + 
				"        <Feature>1TB Hard Drive</Feature>\r\n" + 
				"        <Feature>This is Put</Feature>\r\n" + 
				"        <Feature>2 GB SSD</Feature>\r\n" + 
				"        <Feature>Latest Model</Feature>\r\n" + 
				"    </Features>\r\n" + 
				"    <Id>"+id+"</Id>\r\n" + 
				"    <LaptopName>Macbook L Series</LaptopName>\r\n" + 
				"</Laptop>";

		Map<String,String> headers = new LinkedHashMap<String,String>();

		headers.put("Content-Type", "application/xml"); 
		headers.put("Accept","application/xml"); 
		RestResponse response=RestAPIHelper.performPutRequest("http://localhost:8087/laptop-bag/webapi/api/update", xmlBody, ContentType.APPLICATION_XML, headers);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND,response.getStatusCode());

	}
}