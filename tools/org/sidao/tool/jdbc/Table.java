package org.sidao.tool.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Table implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7088131246435328369L;

	private String schema;

	private String tableName;
	
	private String tableNameCN;
	private Map<String,Column> columnsMap=new LinkedHashMap<String,Column>();
	private Map<String,Column> pkMap=new LinkedHashMap<String,Column>();
	private String comment;
	
	public Table() {
	}
	
	public Table(String tableName){
		this.tableName=tableName;
	}
	
	public Table(String schema, String tableName) {
		this.schema = schema;
		this.tableName = tableName;
	}

	public void addColumn(Column column){
		columnsMap.put(column.getColumnName(), column);
		if(column.isPrimaryKey()){
			pkMap.put(column.getColumnName(), column);
		}
	}
	/**
	 * @param key 列名
	 * @return
	 */
	public Column getColumn(String key){
		return columnsMap.get(key);
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("schema=");
		if(schema==null)
			builder.append("null");
		else
			builder.append(schema);
		builder.append(";");
		builder.append("tableName=");
		if(tableName==null)
			builder.append("null");
		else
			builder.append(tableName);
		builder.append(";");
		builder.append("tableNameCN=");
		if(tableNameCN==null)
			builder.append("null");
		else
			builder.append(tableNameCN);
		builder.append(";");
		builder.append("columns=");
		if(columnsMap==null)
			builder.append("null");
		else
			builder.append(columnsMap.values());
		return builder.toString();
	}

	public String getTableNameCN() {
		return tableNameCN;
	}

	public void setTableNameCN(String tableNameCN) {
		this.tableNameCN = tableNameCN;
	}

	public Collection<Column> getColumns() {
		return this.columnsMap.values();
	}

	public Map<String, Column> getColumnsMap() {
		return columnsMap;
	}

	public void setColumnsMap(Map<String, Column> columnsMap) {
		this.columnsMap = columnsMap;
	}

	public Map<String, Column> getPkMap() {
		return pkMap;
	}

	public void setPkMap(Map<String, Column> pkMap) {
		this.pkMap = pkMap;
	}
	/**
	 * 从数据库构建
	 */
	public void buildMe(Connection conn){
		try {
			String sql = forTableBuilderDoBuild(this.getTableName());
			Statement stm = conn.createStatement();
			DatabaseMetaData dbMeta=conn.getMetaData(); 
			ResultSet rs = stm.executeQuery(sql);
			String currentCatalog=conn.getCatalog(); 
			String[] types={"TABLE","VIEW"};
	        ResultSet resultset=dbMeta.getColumns(currentCatalog, null,this.getTableName(), null);
	        Column column;
	        while(resultset.next()){
        		column=new Column();
        		column.setTableName(this.getTableName());
        		column.setColumnName(resultset.getString("COLUMN_NAME"));
        		column.setNullable(resultset.getString("IS_NULLABLE").equals("YES")?true:false);
    			//column.setComment();
    			column.setColumnSize(resultset.getInt("CHAR_OCTET_LENGTH"));
    			String comment=resultset.getString("REMARKS")==null?"":resultset.getString("REMARKS");
    			int end=comment.indexOf("@");
    			if(end>0){
    				column.setComment(comment.substring(0, end));
    				if(comment.indexOf("@search")>0){
    					column.setSearchField(true);
    				}
    				if(comment.indexOf("@combo")>0){
    					column.setCombo(true);
    				}
    				if(comment.indexOf("@hidden")>0){
    					column.setHidden(true);
    				}
    			}else{
    				column.setComment(comment);
    			}
    			column.setTypeCode(resultset.getInt("DATA_TYPE"));
    			addColumn(column);
            }
        	resultset =dbMeta.getPrimaryKeys(currentCatalog, getSchema(), getTableName());
        	while(resultset.next()){
        		String pkstring=resultset.getString("COLUMN_NAME");
        		column=getColumn(pkstring);
        		column.setPrimaryKey(true);
        		getPkMap().put(column.getColumnName(), column);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String forTableBuilderDoBuild(String tableName) {
		return "select * from " + tableName + " where 1 = 2";
	}
}
