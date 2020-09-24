package com.api.rest.api.helper;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

public class PromptAuth {

	public static void main(String[] args) {

		CredentialsProvider provider=new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("admin","welcome"));
		HttpClientContext context=HttpClientContext.create();
		context.setCredentialsProvider(provider);

		HttpGet get=new HttpGet("http://localhost:8087/laptop-bag/webapi/prompt/all");

		try (CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(get,context)){
			ResponseHandler<String> handler=new BasicResponseHandler();
			RestResponse rs=new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
			System.out.println(rs.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
