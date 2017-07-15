package org.sidao.tool.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;


/**
 * @author xcwang
 *
 */
public class JdbcTemplate {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JdbcTemplate.class);
	private static final String GET_LAST_ID="select last_insert_id() as ID from usertype limit 1";
	
	public List<Map<String,Object>> query(String sql,Object... args) throws SQLException, ConfigurationException, ClassNotFoundException{
		Connection con=ConnectionPool.getConnection();
		return query(con,sql,args);
	}
	
	public List<Map<String,Object>> query(Connection con,String sql,Object... args) throws SQLException{
		if (logger.isDebugEnabled()) {
			logger.debug("query(Connection, String, Object) - start"); //$NON-NLS-1$
		}

		ResultSetHandler<List<Map<String,Object>>> h = ResultHandlers.getMapListHandler();
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner();
		List<Map<String, Object>> results=new ArrayList<Map<String, Object>>();
		if (logger.isDebugEnabled()) {
			logger.debug("执行SQL语句："+sql);
			logger.debug("参数："+ JSON.toJSON(args)); //$NON-NLS-1$
		}
		results = run.query(con, sql, h, args);
		DbUtils.closeQuietly(con);

		if (logger.isDebugEnabled()) {
			logger.debug("query(Connection, String, Object) - end"); //$NON-NLS-1$
		}
		return results;
	}
	public static Connection getConnection() throws ConfigurationException, SQLException, ClassNotFoundException{
		return ConnectionPool.getConnection();
	}
	/**
	 * 获得一个事例
	 * @return
	 */
	public static JdbcTemplate getInstance(){
		return new JdbcTemplate();
	}

	/**
	 * 插入数据，获取ID依赖mysql
	 * @param sb
	 * @return 所插入数据的ID
	 * @throws ConfigurationException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Object insertGid(SqlBox sb) throws ConfigurationException, SQLException, ClassNotFoundException {
		ResultSetHandler<List<Map<String,Object>>> h = ResultHandlers.getMapListHandler();
		QueryRunner run = new QueryRunner();
		Connection con=getConnection();
		int i =run.update(con, sb.getSql(), sb.getObjs());
		if(i>0){
			List<Map<String, Object>> ls=run.query(GET_LAST_ID, h);
			if(ls.size()>0){
				DbUtils.close(con);
				return ls.get(0).get("ID");
			}
		}
		DbUtils.close(con);
		return null;
	}
	/**
	 * 插入,更新,删除数据
	 * @param sb
	 * @return 操作数据的数量
	 * @throws ConfigurationException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int update(SqlBox sb) throws ConfigurationException, SQLException, ClassNotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("update(SqlBox) - start"); //$NON-NLS-1$
		}

		QueryRunner run = new QueryRunner();
		Connection con=getConnection();
		if (logger.isDebugEnabled()) {
			logger.debug("执行SQL语句："+sb.getSql()); //$NON-NLS-1$
			logger.debug("参数："+JSON.toJSON(sb.getObjs())); //$NON-NLS-1$
		}
		int i= run.update(con, sb.getSql(), sb.getObjs());
		DbUtils.close(con);

		if (logger.isDebugEnabled()) {
			logger.debug("update(SqlBox) - end"); //$NON-NLS-1$
		}
		return i;
	}
	/**
	 * 插入,更新,删除数据
	 * @param sql
	 * @return 操作数据的数量
	 * @throws ConfigurationException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int update(String sql,Object... args) throws ConfigurationException, SQLException, ClassNotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("update(SqlBox) - start"); //$NON-NLS-1$
		}

		QueryRunner run = new QueryRunner();
		Connection con=getConnection();
		if (logger.isDebugEnabled()) {
			logger.debug("执行SQL语句："+sql); //$NON-NLS-1$
			logger.debug("参数："+JSON.toJSON(args)); //$NON-NLS-1$
		}
		int i= run.update(con, sql, args);
		DbUtils.close(con);

		if (logger.isDebugEnabled()) {
			logger.debug("update(SqlBox) - end"); //$NON-NLS-1$
		}
		return i;
	}
	public long countQuery(String sql,Object... args) throws Exception{
		Connection con=ConnectionPool.getConnection();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) TOTAL FROM(").append(sql).append(") T");
		List<Map<String, Object>> rs= query(con,sb.toString(),args);
		long total=(Long)(rs.get(0).get("TOTAL"));
		return total;
	}

	/**
	 * 依赖mysql
	 * @param start
	 * @param limit
	 * @param sql
	 * @param parms
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws ConfigurationException 
	 */
	public List<Map<String,Object>> query(int start, int limit, String sql,
			Object[] parms) throws ConfigurationException, SQLException, ClassNotFoundException {
		Connection con=ConnectionPool.getConnection();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM(").append(sql).append(") T LIMIT ").append(start).append(",").append(limit);
		List<Map<String, Object>> rs= query(con,sb.toString(),parms);
		return rs;
	}
	
	public Map queryFirst(String sql,Object... args) throws Exception{
		List<Map<String,Object>> res=this.query(sql,args);
		if(res.isEmpty()){
			return null;
		}else{
			return res.get(0);
		}
	}
}
