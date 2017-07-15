package org.sidao.tool;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import freemarker.template.Configuration;

public class ConfigurationHelper {

	private static Configuration cfg = null;
	private static BoneCP connectionPool=null;
	public static Configuration getConfiguration(String templateDir) throws IOException {
		if (null == cfg) {
			cfg = new Configuration();
	        File templateDirFile = new File(templateDir);
	        cfg.setDirectoryForTemplateLoading(templateDirFile);
	        cfg.setLocale(Locale.CHINA);
	        cfg.setDefaultEncoding("UTF-8");
		}
		return cfg;
	}
	public static int getTypeCode(String type) {
		if(type.equals("varchar")){
			return Types.VARCHAR;
		}else if(type.equals("datetime")){
			return Types.TIMESTAMP;
		}else if(type.equals("int")){
			return Types.INTEGER;
		}else if(type.equals("double")){
			return Types.DOUBLE;
		}else if(type.equals("timestamp")){
			return Types.TIMESTAMP;
		}
		return Types.VARCHAR;
	}
	public static BoneCPConfig buildConfig(org.apache.commons.configuration.Configuration dbConfig) throws ConfigurationException{
		Map data=new HashMap();
		BoneCPConfig bonecpConfig = new BoneCPConfig();
		bonecpConfig.setJdbcUrl(dbConfig.getString("jdbc.url"));
		bonecpConfig.setUsername(dbConfig.getString("jdbc.username"));
		bonecpConfig.setPassword(dbConfig.getString("jdbc.password"));
		
		return bonecpConfig;
	}
	
	public static Connection getConnection(org.apache.commons.configuration.Configuration dbConfig) throws ConfigurationException, SQLException{
		if (connectionPool==null) {
			connectionPool = new BoneCP(buildConfig(dbConfig));
		}
		return connectionPool.getConnection();
	}

	public static void shutdown() {
		if (connectionPool!=null) {
			connectionPool.shutdown();
		}
	}
}
