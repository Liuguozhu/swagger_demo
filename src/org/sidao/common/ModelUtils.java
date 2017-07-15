package org.sidao.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Model;

public class ModelUtils {
	/**
	 * 将model参数到map,参数名称从表字段名称到Hibernate模式的bean字段名称
	 * @param m
	 * @return
	 */
	public static Map<String,Object> modelToBeanMap(Model<?> m){
		String[] attrs=m.getAttrNames();
		Map<String,Object> map=new HashMap<String,Object>();
		for(String attr:attrs){
			if(m.get(attr)!=null){
				map.put(column2Beanname(attr), m.get(attr));
			}
			
		}
		return map;
	}
	
	public static String column2Beanname(String columnName){
		StringBuffer sb=new StringBuffer();
		String[] cnames=columnName.split("_");
		for(int i=0;i<cnames.length;i++){
			if(i==0)
				sb.append(StringUtils.lowerCase(cnames[i]));
			else
				sb.append(StringUtils.capitalize(StringUtils.lowerCase(cnames[i])));
		}
		return sb.toString();
	}
}
