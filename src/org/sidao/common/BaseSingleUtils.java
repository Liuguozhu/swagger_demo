package org.sidao.common;

import org.apache.commons.httpclient.HttpClient;

public class BaseSingleUtils {
	private static String BASE_SINGLE_URL="182.92.21.219:10789";
	/**
	 * 获取地址
	 * @return
	 */
	public static String getUrl(){
		return BASE_SINGLE_URL;
	}
	/**
	 * 从url处获取地址
	 */
	public static void setBaseSingleUrl(){
		BASE_SINGLE_URL=HttpClientUtils.httpGet(new HttpClient(), "http://pr.sppro.info:8887/sp?a=1");
	}
}
