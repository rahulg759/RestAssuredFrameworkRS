package com.api.rest.api.model;

public class RestResponse {

	private int statusCode;
	private String responseBody;

	//generate getter method
	public int getStatusCode() {
		return statusCode;
	}
	public String getResponseBody() {
		return responseBody;
	}

	public RestResponse(int statusCode, String responseBody ) {
		this.statusCode=statusCode;
		this.responseBody=responseBody;
	}

	
	//toString() is a overriden method from Object class and here we are representing the string representation of object. 
	@Override
	public String toString() {
		return String.format("Status Code : %1s Body : %2s",this.statusCode,this.responseBody);
	}
}
