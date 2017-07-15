package org.sidao.common;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * @author xcwang
 *
 */
public class HttpClientFactory {

	/**
	 * 
	 */
	public HttpClientFactory() {
	}
	
	public static HttpClient getNewHttpClient(){
		HttpClient client=new HttpClient();
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
		managerParams.setConnectionTimeout(25000);
		managerParams.setSoTimeout(20000);
		return client;			
	}
}
