package com.api.rest.junit.helper;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import com.api.rest.api.helper.RestAPIHelper;
import com.api.rest.api.model.RestResponse;

public class TestQueryParameter {

	//http://localhost:8087/laptop-bag/webapi/api/query?id="1" & laptopName="Dell"

	@Test
	public void testQuery() throws URISyntaxException {
		URI uri = new URIBuilder()
				.setScheme("http") //https
				.setHost("localhost:8087")
				.setPath("laptop-bag/webapi/api/query")
				.setParameter("id","130")
				.setParameter("laptopName","Macbook Laptops").build();

		RestResponse response = RestAPIHelper.performGetRequest(uri, null);
		System.out.println(response.toString());
	}
}
