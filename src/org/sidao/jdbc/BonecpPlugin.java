package org.sidao.jdbc;

import javax.sql.DataSource;

import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;
import com.jolbox.bonecp.BoneCPDataSource;

public class BonecpPlugin implements IPlugin, IDataSourceProvider {
	BoneCPDataSource datesource;
	
	public BonecpPlugin() {
//		ConfigFactory.setDbConfig(dbConfig);
	}
	public DataSource getDataSource() {
		return datesource;
	}

	public boolean start() {
		datesource=ConnectionPool.getConnectionPool();
		return true;
	}

	public boolean stop() {
		datesource.close();
		return true;
	}

}
