package com.api.rest.api.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.api.rest.api.model.RestResponse;

public class HttpsClientHelper {

	public static HttpEntity geHttpEntity(Object content, ContentType type) {
		if (content instanceof String)
			return new StringEntity((String) content, type);
		else if (content instanceof File)
			return new FileEntity((File) content, type);
		else
			throw new RuntimeException("Entity Type not found");
	}

	//========================================= This is for adding the headers in request =========================================//

	private static Header[] getCustomHeaders(Map<String, String> headers) {
		Header[] customHeaders = new Header[headers.size()];
		int i=0;
		for (String key : headers.keySet()) {
			customHeaders[i++] = new BasicHeader(key, headers.get(key));
		}
		return customHeaders;
	}

	//========================================= This is for handling of SSL Key =========================================//

	public static SSLContext getSSLContext() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		TrustStrategy trust=new TrustStrategy() {

			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		};
		return SSLContextBuilder.create().loadTrustMaterial(trust).build();
	}

	public static CloseableHttpClient getHttpClient(SSLContext sslContext) {
		return HttpClientBuilder.create().setSSLContext(sslContext).build();
	}

	//========================================= This is for perform generic request =========================================//

	private static RestResponse performRequest(HttpUriRequest method) {

		CloseableHttpResponse response=null;

		try(CloseableHttpClient client = getHttpClient(getSSLContext())) {
			response=client.execute(method);
			System.out.println(response.toString());
			ResponseHandler<String> handler=new BasicResponseHandler();
			return new RestResponse(response.getStatusLine().getStatusCode(), handler.handleResponse(response));
		} catch (Exception e) {
			if (e instanceof HttpResponseException)
				return new RestResponse(response.getStatusLine().getStatusCode(), e.getMessage());
			// handle runtime exception
			throw new RuntimeException(e.getMessage(), e);
		}	
	}

	//========================================= This is for Get generic request =========================================//

	public static RestResponse performGetRequestWithSSL(String uri,Map<String, String> headers) {

		try {
			return performGetRequestWithSSL(new URI(uri), headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

	public static RestResponse performGetRequestWithSSL(URI uri,Map<String, String> headers) {
		HttpGet get=new HttpGet(uri);

		if (null!=headers) 
			get.setHeaders(getCustomHeaders(headers));	
		return performRequest(get);
	}


	//========================================= This is for Post generic request =========================================//	

	public static RestResponse performPostRequest(String url, Object content, ContentType type,Map<String, String> headers) {
		try {
			return performPostRequest(new URI(url), content, type, headers);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

	public static RestResponse performPostRequest(URI uri, Object content, ContentType type,Map<String, String> headers) {
		HttpUriRequest post = RequestBuilder.post(uri).setEntity(geHttpEntity(content,type)).build();

		if (null!=headers) 
			post.setHeaders(getCustomHeaders(headers));	
		return performRequest(post);
	}


	//========================================= This is for Put generic request =========================================//	

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

	//========================================= This is for delete generic request =========================================//	

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
}

