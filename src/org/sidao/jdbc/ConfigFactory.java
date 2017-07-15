package org.sidao.jdbc;

import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.jolbox.bonecp.BoneCPConfig;

/**
 * @author xcwang
 *
 */
public class ConfigFactory {
	private static Configuration dbConfig = null;
	private static BoneCPConfig bonecpConfig = null;
	private static BoneCPConfig bonecpConfig2 = null;
	private static String DBCONFIG_FILE="dbconfig.properties";
	/**
	 * 获取数据库配置文件
	 * @return
	 * @throws ConfigurationException
	 */
	public static Configuration getDbConfig() throws ConfigurationException{
		if(dbConfig==null){
			dbConfig= new PropertiesConfiguration(DBCONFIG_FILE);
		}
		return dbConfig;
	}
	/**
	 * 获取bonecp配置信息
	 * @return
	 * @throws ConfigurationException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public static BoneCPConfig getBoneCpConfig() throws ConfigurationException, ClassNotFoundException{
		if(bonecpConfig==null){
			bonecpConfig = new BoneCPConfig();
			dbConfig=getDbConfig();
			CipherUtils cu=new CipherUtils();
			Class.forName(dbConfig.getString("jdbc.driverClassName"));
			bonecpConfig.setJdbcUrl(dbConfig.getString("jdbc.url"));
			try {
				bonecpConfig.setUsername(cu.decrypt(dbConfig.getString("jdbc.username")));
				bonecpConfig.setPassword(cu.decrypt(dbConfig.getString("jdbc.password")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//bonecpConfig.setIdleMaxAge(dbConfig.getLong("boneCP.idleMaxAge"));
			//bonecpConfig.setIdleConnectionTestPeriod(dbConfig.getLong("boneCP.idleConnectionTestPeriod"));
			bonecpConfig.setPartitionCount(dbConfig.getInt("boneCP.partitionCount"));
			bonecpConfig.setAcquireIncrement(dbConfig.getInt("boneCP.acquireIncrement"));
			bonecpConfig.setMaxConnectionsPerPartition(dbConfig.getInt("boneCP.maxConnectionsPerPartition"));
			bonecpConfig.setMinConnectionsPerPartition(dbConfig.getInt("boneCP.minConnectionsPerPartition"));
			bonecpConfig.setStatementsCacheSize(dbConfig.getInt("boneCP.statementsCachedPerConnection"));
			bonecpConfig.setReleaseHelperThreads(dbConfig.getInt("boneCP.releaseHelperThreads"));

			Properties driverProperties = new Properties();
			driverProperties.put("driver", dbConfig.getString("driver"));
			bonecpConfig.setDriverProperties(driverProperties);
		}
		return bonecpConfig;
	}
	@SuppressWarnings({ "deprecation", "static-access" })
	public static BoneCPConfig getBoneCpConfig2() throws ConfigurationException, ClassNotFoundException{
		if(bonecpConfig2==null){
			bonecpConfig2 = new BoneCPConfig();
			dbConfig=getDbConfig();
			CipherUtils cu=new CipherUtils();
			Class.forName(dbConfig.getString("jdbc.driverClassName"));
			bonecpConfig2.setJdbcUrl(dbConfig.getString("wosdk.url"));
			try {
				bonecpConfig2.setUsername(cu.decrypt(dbConfig.getString("wosdk.username")));
				bonecpConfig2.setPassword(cu.decrypt(dbConfig.getString("wosdk.password")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//bonecpConfig.setIdleMaxAge(dbConfig.getLong("boneCP.idleMaxAge"));
			//bonecpConfig.setIdleConnectionTestPeriod(dbConfig.getLong("boneCP.idleConnectionTestPeriod"));
			bonecpConfig2.setPartitionCount(dbConfig.getInt("boneCP.partitionCount"));
			bonecpConfig2.setAcquireIncrement(dbConfig.getInt("boneCP.acquireIncrement"));
			bonecpConfig2.setMaxConnectionsPerPartition(dbConfig.getInt("boneCP.maxConnectionsPerPartition"));
			bonecpConfig2.setMinConnectionsPerPartition(dbConfig.getInt("boneCP.minConnectionsPerPartition"));
			bonecpConfig2.setStatementsCacheSize(dbConfig.getInt("boneCP.statementsCachedPerConnection"));
			bonecpConfig2.setReleaseHelperThreads(dbConfig.getInt("boneCP.releaseHelperThreads"));

			Properties driverProperties = new Properties();
			driverProperties.put("driver", dbConfig.getString("driver"));
			bonecpConfig2.setDriverProperties(driverProperties);
		}
		return bonecpConfig2;
	}
	
	public static boolean getSwitch(){
			try {
				Configuration switchConfig =new PropertiesConfiguration("switch.properties");
				return switchConfig.getBoolean("interface.switch");
			} catch (ConfigurationException e) {
				e.printStackTrace();
				return false;
			}
	}
}
