package com.web.soulyogaadmin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;





public class HttpXmlClient {
	private static Logger log = Logger.getLogger(HttpXmlClient.class);
	
	@SuppressWarnings("deprecation")
	public static String post(String url, Map<String, String> params,SSLConnectionSocketFactory sslsf) throws ParseException, IOException {
		@SuppressWarnings("deprecation")
		
		   Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
	           .register("http", PlainConnectionSocketFactory.getSocketFactory())
	           .register("https", sslsf)
	           .build();
		
		PoolingHttpClientConnectionManager cm= new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(200); 
		cm.setDefaultMaxPerRoute(20); 
		
		


		CloseableHttpClient httpclient = HttpClients.custom()
                //.setSSLSocketFactory(sslsf)
                 .setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                //.setHostnameVerifier(AllowAllHostnameVerifier.INSTANCE)
                 .setConnectionManager(cm)
                 .build();
		String body = null;
		
		log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);
		
		body = invoke(httpclient, post);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	}
	
	@SuppressWarnings("deprecation")
	public static String post(String url, String xmlData,SSLConnectionSocketFactory sslsf) throws ParseException, IOException {
		@SuppressWarnings("deprecation")
		//DefaultHttpClient httpclient = new DefaultHttpClient();
		
		
		   Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
	           .register("http", PlainConnectionSocketFactory.getSocketFactory())
	           .register("https", sslsf)
	           .build();
		
		PoolingHttpClientConnectionManager cm= new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(200); 
		cm.setDefaultMaxPerRoute(20); 
		
		CloseableHttpClient httpclient = HttpClients.custom()
	                        //.setSSLSocketFactory(sslsf)
	                         .setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
	                        //.setHostnameVerifier(AllowAllHostnameVerifier.INSTANCE)
	                         .setConnectionManager(cm)
	                         .build();
	        
		String body = null;
		
		log.info("create httppost: ");
		HttpPost post = postForm(url, xmlData);
		post.addHeader("Content-Type", "application/json");
		post.addHeader("Accept", "application/json");
		
		body = invoke(httpclient, post);
		
		httpclient.getConnectionManager().shutdown();
		
		return body;
	}
	public static String doGet(String uri,SSLConnectionSocketFactory sslsf) throws ParseException, IOException {
		 Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
		           .register("http", PlainConnectionSocketFactory.getSocketFactory())
		           .register("https", sslsf)
		           .build();
			
			PoolingHttpClientConnectionManager cm= new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			cm.setMaxTotal(200); 
			cm.setDefaultMaxPerRoute(20); 
			
			CloseableHttpClient httpclient = HttpClients.custom()
		                        //.setSSLSocketFactory(sslsf)
		                         .setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
		                        //.setHostnameVerifier(AllowAllHostnameVerifier.INSTANCE)
		                         .setConnectionManager(cm)
		                         .build();
			
			HttpGet get=new HttpGet();
			
			//TODO
			get.addHeader("Accept-Charset", "utf-8"); 
			get.addHeader("Content-Type", "application/x-www-form-urlencoded");
			String body= invoke(httpclient, get);
			httpclient.getConnectionManager().shutdown();
			
			return body;
	}
	private static String invoke(CloseableHttpClient httpclient,
			HttpUriRequest httpost) throws ParseException, IOException {
		
		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);
		
		return body;
	}

	@SuppressWarnings("deprecation")
	private static String paseResponse(HttpResponse response)throws ParseException,IOException {
		String errorCode=null;
		log.info("get response from http server..");
		for(Header header :response.getAllHeaders()){
			if(header.getName().equals("Location")){
				errorCode=header.getValue();
				break;
			}
		};
		/*HttpEntity entity = response.getEntity();
		
		log.info("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
		log.info(charset);
		
		String body = null;
		
		body = EntityUtils.toString(entity);
		log.info(body);*/
		
		return errorCode;
	}

	private static HttpResponse sendRequest(CloseableHttpClient httpclient,
			HttpUriRequest httpost)throws ParseException,IOException ,ClientProtocolException {
		log.info("execute post...");
		HttpResponse response = null;
		
	
		response = httpclient.execute(httpost);
		log.debug("response is...:" +response.toString());
		log.debug("response is...:" +response.getEntity().getContent().toString());
		return response;
	}

	@SuppressWarnings("deprecation")
	private static HttpPost postForm(String url, Map<String, String> params){
		
		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		Set<String> keySet = params.keySet();
		for(String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		
		try {
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpost;
		}
		@SuppressWarnings("deprecation")
		private static HttpPost postForm(String url, String xmlData){
			
			HttpPost httpost = new HttpPost(url);

			
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new StringEntity(xmlData,HTTP.UTF_8));
		
		return httpost;
	}
	
}

