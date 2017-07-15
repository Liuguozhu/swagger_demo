package org.sidao.common;

import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;

/**
 * The type Mssql dialect. 适合sqlserver的适配器
 */
public class MsSqlDialect extends AnsiSqlDialect {
	public void forPaginate(StringBuilder sql, int pageNumber, int pageSize, String select, String sqlExceptSelect) {
		int offset = pageSize * (pageNumber - 1);
		sql.append(select).append(" ");
		sql.append(sqlExceptSelect);
		sql.append(" limit ").append(offset).append(", ").append(pageSize);
	}
}
