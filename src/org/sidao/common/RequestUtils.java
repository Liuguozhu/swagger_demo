package org.sidao.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.sidao.jdbc.ConfigFactory;

import com.alibaba.fastjson.JSON;

public class RequestUtils {
	public static String filePath=null;
	/**
	 * @param request
	 * @return
	 */
	public static String getBodyString(HttpServletRequest request) {
		String inputLine;
		StringBuffer str = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							request.getInputStream()));
			while ((inputLine = reader.readLine()) != null) {
				str.append(inputLine);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return str.toString();
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		return JSON.toJSONString(map);
	}

	/**
	 * 将数据解析到map中
	 * @param map
	 * @param data
	 * @param encoding
	 * @throws UnsupportedEncodingException
	 */
	public static void parseParameters(Map<String, String> map, String data, String encoding)
			throws UnsupportedEncodingException {
		if ((data == null) || (data.length() <= 0)) {
			return;
		}

		byte[] bytes = null;
		try {
			if (encoding == null)
				bytes = data.getBytes();
			else
				bytes = data.getBytes(encoding);
		} catch (UnsupportedEncodingException uee) {
		}
		parseParameters(map, bytes, encoding);
	}

	/**
	 * 解析数据 
	 * @param map
	 * @param data
	 * @param encoding
	 * @throws UnsupportedEncodingException
	 */
	public static void parseParameters(Map<String, String> map, byte[] data, String encoding)
			throws UnsupportedEncodingException {
		if ((data != null) && (data.length > 0)) {
			int ix = 0;
			int ox = 0;
			String key = null;
			String value = null;
			while (ix < data.length) {
				byte c = data[(ix++)];
				switch ((char) c) {
				case '&':
					value = new String(data, 0, ox, encoding);
					if (key != null) {
						putMapEntry(map, key, value);
						key = null;
					}
					ox = 0;
					break;
				case '=':
					if (key == null) {
						key = new String(data, 0, ox, encoding);
						ox = 0;
					} else {
						data[(ox++)] = c;
					}
					break;
				case '+':
					data[(ox++)] = 32;
					break;
				case '%':
					data[(ox++)] = (byte) ((convertHexDigit(data[(ix++)]) << 4) + convertHexDigit(data[(ix++)]));

					break;
				default:
					data[(ox++)] = c;
				}
			}

			if (key != null) {
				value = new String(data, 0, ox, encoding);
				putMapEntry(map, key, value);
			}
		}
	}

	private static void putMapEntry(Map<String, String> map, String key, String value) {
		map.put(key, value);
	}

	/**
	 * 为数据赋值
	 * @param map
	 * @param name
	 * @param value
	 */
	@SuppressWarnings("unused")
	private static void putMapEntryArray(Map<String, String[]> map, String name, String value) {
		String[] newValues = null;
		String[] oldValues = (String[]) map.get(name);
		if (oldValues == null) {
			newValues = new String[1];
			newValues[0] = value;
		} else {
			newValues = new String[oldValues.length + 1];
			System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
			newValues[oldValues.length] = value;
		}
		map.put(name, newValues);
	}

	/**
	 * 匹配字符串
	 * @param b
	 * @return
	 */
	private static byte convertHexDigit(byte b) {
		if ((b >= 48) && (b <= 57))
			return (byte) (b - 48);
		if ((b >= 97) && (b <= 102))
			return (byte) (b - 97 + 10);
		if ((b >= 65) && (b <= 70))
			return (byte) (b - 65 + 10);
		return 0;
	}
	public static void main(String[] args){
		String url="type=pay&act=YiDong.mobilePay&tel=15811186102&consumecode=000082813006&user=gamedo&pw=123456&busiid=700219180000";
		Map<String, String> map=new HashMap<String, String>();
		try {
			parseParameters(map, url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(map));
	}

	public static void saveToFile(String header, String orderId, String datetime, String requestBody,Map<?, ?> paramap) {
		if(filePath==null){
			try {
				filePath=ConfigFactory.getDbConfig().getString("orderDateFilePath", "./orderFiles/");
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		Map<String, String> map=new HashMap<String, String>();
		map.put("header", header);
		map.put("allpara",JSON.toJSONString(paramap));
		map.put("orderId", orderId);
		map.put("datetime",datetime);
		map.put("requestBody", requestBody);
		String fileName=StringUtils.isBlank(orderId)?"wrongMessages.d":orderId+".d";
		File orderfile=FileUtils.getFile(new File(filePath),fileName);
		
		String xml=XmlConverUtil.maptoXml(map);
		try {
			FileUtils.writeStringToFile(orderfile, xml,orderfile.exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveToFile(HttpServletRequest request) {
		if(filePath==null){
			try {
				filePath=ConfigFactory.getDbConfig().getString("orderDateFilePath", "./orderFiles/");
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> map=new HashMap<String, Object>();
		String header = RequestUtils.getHeadersInfo(request);
		map.put("header", header);
		map.put("allpara",JSON.toJSONString(request.getParameterMap()));
		map.put("datetime",new Date());
		String userid=request.getParameter("userid");
		map.put("orderId", userid);
		map.put("requestBody",getBodyString(request));
		String fileName=StringUtils.isBlank(userid)?"wrongMessages.d":userid+".d";
		File orderfile=FileUtils.getFile(new File(filePath),fileName);
		
		String xml=XmlConverUtil.maptoXml(map);
		try {
			FileUtils.writeStringToFile(orderfile, xml,orderfile.exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
