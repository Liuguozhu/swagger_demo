package org.sidao.common;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class CommonConfigUtils {

    private static Configuration config=null;
    
    static{
    	try {
    		config=new PropertiesConfiguration("config.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
    }

	public static Configuration getConfig() {
		return config;
	}
    
    
	
}
