package org.sidao.common;

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import org.apache.http.conn.params.ConnRouteParams;

public class HttpClientUtils {
	/**
	 * @param client
	 * @param url
	 * @return
	 */
	public static String httpGet(HttpClient client,String url){
		String resonposeBody=null;
		try {
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Content-Type", "text/plain; charset=ISO-8859-1");
			getMethod.setRequestHeader("Accept","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
//			int status = client.executeMethod(getMethod);
			resonposeBody = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resonposeBody;
	}
	/**
	 * @param client
	 * @param url
	 * @return
	 */
	public static String httpGet(HttpClient client,String url,NameValuePair[] params){
		String resonposeBody=null;
        if(StringUtils.isBlank(url)){
            return "";
        }
		try {
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Content-Type", "text/plain; charset=ISO-8859-1");
			getMethod.setRequestHeader("Accept","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
			getMethod.setQueryString(params);
//			int status = client.executeMethod(getMethod);
			resonposeBody = getMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resonposeBody;
	}
	/**
	 * @param client
	 * @param url
	 * @param requestEntity
	 * @return
	 */
	public static String httpPost(HttpClient client,String url,RequestEntity requestEntity){
		String resonposeBody=null;
		try {
			PostMethod httppost = new PostMethod(url);
			httppost.setRequestHeader("Content-Type", "text/plain; charset=ISO-8859-1");
			httppost.setRequestHeader("Accept","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
			httppost.setRequestEntity(requestEntity);
//			int status = client.executeMethod(httppost);
			resonposeBody = httppost.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resonposeBody;
	}
	/**
	 * @param client
	 * @param url
	 * @param requestEntity
	 * @return
	 */
	public static String httpPost(HttpClient client,String url,NameValuePair[] params){
		String resonposeBody=null;
		try {
			PostMethod httppost = new PostMethod(url);
			httppost.getParams().setContentCharset("UTF-8");
			httppost.setRequestBody(params);
//			int status = client.executeMethod(httppost);
			resonposeBody = httppost.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resonposeBody;
	}
	
	/**
	 * @param charset
	 * @param url
	 * @param requestEntity
	 * @return
	 */
	public static String httpPost(String charset,String url,RequestEntity requestEntity){
		String resonposeBody=null;
		HttpClient httpclient = new HttpClient();
        PostMethod post  = new PostMethod(url);
        try {  
            post.setRequestEntity(requestEntity); 
            post.getParams().setContentCharset(charset);
            httpclient.executeMethod(post); 
            resonposeBody = post.getResponseBodyAsString();
        } catch (Exception ex) {  
            ex.printStackTrace();  
        } finally {  
            post.releaseConnection();  
        }  
		return resonposeBody;
	}
	/**
	 * @param charset
	 * @param url
	 * @param requestEntity
	 * @return
	 */
	public static String httpPostWithVirtualHead(String charset,String url,RequestEntity requestEntity,String virtualIp){
		String resonposeBody=null;
		HttpClient httpclient = new HttpClient();
        PostMethod post  = new PostMethod(url);
        post.setRequestHeader("X-Forwarded-For",virtualIp);
        post.setRequestHeader("Proxy-Client-IP",virtualIp);
        post.setRequestHeader("WL-Proxy-Client-IP",virtualIp);
        post.setRequestHeader("HTTP_CLIENT_IP",virtualIp);
        post.setRequestHeader("HTTP_X_FORWARDED_FOR",virtualIp);
//        post.getParams().setVirtualHost("218.245.3.67");
        try {  
            post.setRequestEntity(requestEntity); 
            post.getParams().setContentCharset(charset);
            httpclient.executeMethod(post); 
            resonposeBody = post.getResponseBodyAsString();
        } catch (Exception ex) {  
            ex.printStackTrace();  
        } finally {  
            post.releaseConnection();
        }  
		return resonposeBody;
	}
	/**
	 * @param httpClient
	 * @param url1
	 * @param data
	 * @return
	 */
	public static String httpPostBodyData(HttpClient httpClient, String url1,String data) {
		try {
			return httpPost("UTF-8",url1,new StringRequestEntity(data, "application/json", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
