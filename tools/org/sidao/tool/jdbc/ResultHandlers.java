package org.sidao.tool.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * @author xcwang
 *
 */
public class ResultHandlers {
	public static ResultSetHandler<List<Map<String,Object>>> getMapListHandler(){
		return new ResultSetHandler<List<Map<String,Object>>>() {
		    public List<Map<String,Object>> handle(ResultSet rs) throws SQLException {
		        ResultSetMetaData meta = rs.getMetaData();
		        int cols = meta.getColumnCount();
		        List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		        while(rs.next()){
			        Map<String, Object> result = new HashMap<String, Object>();
			        for (int i = 1; i <= cols; i++) {
			            result.put(meta.getColumnName(i), rs.getObject(i));
			        }
			        results.add(result);
		        }
		        return results;
		    }
		};
	}
}
