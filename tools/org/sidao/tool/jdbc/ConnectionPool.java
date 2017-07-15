/**
 * connection操作
 */
package org.sidao.tool.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.configuration.ConfigurationException;
import org.sidao.jdbc.ConfigFactory;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * @author xcwang
 *
 */
public class ConnectionPool {
	private static BoneCPDataSource connectionPool = null;
	/**
	 * 初始化连接池
	 * @throws ConfigurationException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static void initialize() throws ConfigurationException, SQLException, ClassNotFoundException{
		connectionPool= new BoneCPDataSource(ConfigFactory.getBoneCpConfig());
	}
	
	/**
	 * 获得数据库连接
	 * @return
	 * @throws ConfigurationException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws ConfigurationException, SQLException, ClassNotFoundException{
		if(connectionPool==null){
			connectionPool= new BoneCPDataSource(ConfigFactory.getBoneCpConfig());
		}
		return connectionPool.getConnection();
	}
	
	/**
	 * 关闭连接池
	 */
	public static void shutdown(){
		if(connectionPool!=null){
			connectionPool.close();
		}
	}

	public static BoneCPDataSource getConnectionPool() {
		if(connectionPool==null){
			try {
				initialize();
			} catch (ConfigurationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connectionPool;
	}
}
