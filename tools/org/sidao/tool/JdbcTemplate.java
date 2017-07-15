/**
 * 
 */
package org.sidao.tool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.sidao.jdbc.ConnectionPool;

/**
 * @author xcwang
 *
 */
public class JdbcTemplate {
	
	
	public List<Map<String,Object>> query(Connection con,String sql,Object... args) throws SQLException{
		ResultSetHandler<List<Map<String,Object>>> h = Handlers.getMapListHandler();

		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner();
		List<Map<String, Object>> results=new ArrayList<Map<String, Object>>();
		results = run.query(con, sql, h, args);
		DbUtils.closeQuietly(con);
		return results;
	}
	public List<Map<String,Object>> query(String sql,Object... args) throws SQLException, ConfigurationException, ClassNotFoundException{
		ResultSetHandler<List<Map<String,Object>>> h = Handlers.getMapListHandler();
		Connection con=ConnectionPool.getConnection();
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner();
		List<Map<String, Object>> results=new ArrayList<Map<String, Object>>();
		results = run.query(con, sql, h, args);
		DbUtils.closeQuietly(con);
		return results;
	}
}
