package com.api.rest.api.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.api.rest.api.model.RestResponse;
import com.ctc.wstx.shaded.msv_core.util.Uri;

public class RestAPIHelper {


	/*public static void performGetRequest(String url) {
		HttpGet get=new HttpGet(url);

		try (CloseableHttpClient client=HttpClientBuilder.create().build();
				CloseableHttpResponse response=client.execute(get)){ 
			ResponseHandler<String> body=new BasicResponseHandler(); 
			RestResponse rs=new RestResponse(response.getStatusLine().getStatusCode(),body.handleResponse(response)); 
			System.out.println(rs.toString());
		} catch(Exception e) { 
			// TODO: handle exception 
		}
	}*/



	// THis method for calling RestResponse class
	/*
	 * public static RestResponse performGetRequest(String url) { HttpGet get=new
	 * HttpGet(url);
	 * 
	 * try (CloseableHttpClient client=HttpClientBuilder.create().build();
	 * CloseableHttpResponse response=client.execute(get)){ ResponseHandler<String>
	 * body=new BasicResponseHandler(); RestResponse rs=new
	 * RestResponse(response.getStatusLine().getStatusCode(),
	 * body.handleResponse(response)); return rs; } catch (Exception e) { throw new
	 * RuntimeException(e.getMessage(),e); }
	 * 
	 * }
	 */

	// ============================= overloaded version ========================================= //

	private static RestResponse performRequest(HttpUriRequest method) {
		CloseableHttpResponse response = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			response = client.execute(method);

			ResponseHandler<String> body = new BasicResponseHandler();
			RestResponse rs = new RestResponse(response.getStatusLine().getStatusCode(), body.handleResponse(response));
			return rs;
		} catch (Exception e) {
			if (e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
			// handle runtime exception
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static RestResponse performGetRequest(URI uri, Map<String, String> headers) {
		HttpGet get = new HttpGet(uri);

		//Making more genric for GET and POST , we need to use getCustomHeaders

		if (headers != null) {
			/*for (String str : headers.keySet()) {
				get.addHeader(str, headers.get(str));
			}*/

			get.setHeaders(getCustomHeaders(headers));
		}
		return performRequest(get);
	}

	public static RestResponse performGetRequest(String url, Map<String, String> headers) {
		try {
			return performGetRequest(new URI(url), headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}


	/*
	 * public static RestResponse performPostRequest(String url, String content,
	 * Map<String,String> headers) { CloseableHttpResponse response=null; HttpPost
	 * post =new HttpPost(url);
	 * 
	 * if (headers !=null) { for (String key : headers.keySet()) {
	 * post.addHeader(key,headers.get(key)); } }
	 * 
	 * post.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
	 * 
	 * try(CloseableHttpClient client=HttpClientBuilder.create().build()) {
	 * response=client.execute(post); ResponseHandler<String> handler=new
	 * BasicResponseHandler(); return new
	 * RestResponse(response.getStatusLine().getStatusCode(),
	 * handler.handleResponse(response)); } catch (Exception e) { if (e instanceof
	 * HttpResponseException) return new
	 * RestResponse(response.getStatusLine().getStatusCode(), ""); throw new
	 * RuntimeException(e.getMessage(),e); } }
	 */

	// =================================== Creating the generic method for post request ==================================================//



	private static Header[] getCustomHeaders(Map<String, String> headers) {
		Header[] customHeaders = new Header[headers.size()];
		int i=0;
		for (String key : headers.keySet()) {
			customHeaders[i++] = new BasicHeader(key, headers.get(key));
		}
		return customHeaders;
	}

	public static HttpEntity geHttpEntity(Object content, ContentType type) {
		if (content instanceof String)
			return new StringEntity((String) content, type);
		else if (content instanceof File)
			return new FileEntity((File) content, type);
		else
			throw new RuntimeException("Entity Type not found");
	}


	public static RestResponse performPostRequestGeneric(String url, Object content, ContentType type,Map<String, String> headers) {
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);

		if (headers != null) {
			/*for (String key : headers.keySet()) {
				post.addHeader(key, headers.get(key));
			}*/

			post.setHeaders(getCustomHeaders(headers));
		}


		post.setEntity(geHttpEntity(content, type));

		/*try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			response = client.execute(post);
			ResponseHandler<String> handler = new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		} catch (Exception e) {
			if (e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), "");
			throw new RuntimeException(e.getMessage(), e);
		}*/

		return performRequest(post);
	}

	// =================================== Creating the generic method for delete request ==================================================//

	public static RestResponse performDeleteRequest(String url,Map<String, String> headers) {
		try {
			return performDeleteRequest(new URI(url), headers);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}

	}

	public static RestResponse performDeleteRequest(URI uri,Map<String, String> headers) {

		HttpUriRequest delete=RequestBuilder.delete(uri).build();

		if (null!=headers) {
			delete.setHeaders(getCustomHeaders(headers));
		}
		return performRequest(delete);

	}


	// =================================== Creating the generic method for put request ==================================================//

	public static RestResponse performPutRequest(URI uri,Object content,ContentType type, Map<String, String> headers) {

		HttpUriRequest put=RequestBuilder.put(uri).setEntity(geHttpEntity(content, type)).build();

		if (null!=headers)
			put.setHeaders(getCustomHeaders(headers));

		return performRequest(put);
	}

	public static RestResponse performPutRequest(String url,Object content,ContentType type, Map<String, String> headers) {

		try {
			return performPutRequest(new URI(url),content,type,headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(),e);
		}

	}

}
