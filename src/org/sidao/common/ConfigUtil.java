package org.sidao.common;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class ConfigUtil {
	private static Configuration config = null;
	private static String CONFIG_FILE="MQTT.properties";
	
	static{
		try{
			config= new PropertiesConfiguration(CONFIG_FILE);
		}
		catch(ConfigurationException e){
			e.printStackTrace();
		}
	}
	
	public static String getStringProperty(String name){
		return config.getString(name);
	}
	
	public static int getIntProperty(String name){
		return config.getInt(name);
	}
}
