/**
 * 
 */
package org.sidao.tool.jdbc;

/**
 * @author xcwang
 *
 */
public class SqlBox {
	
	String sql;
	Object[] objs;
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Object[] getObjs() {
		return objs;
	}
	public void setObjs(Object[] objs) {
		this.objs = objs;
	}
	
}
