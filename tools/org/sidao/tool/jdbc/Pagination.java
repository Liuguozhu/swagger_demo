package org.sidao.tool.jdbc;

import java.util.List;

/**
 * for Pagination
 * 
 * @author rickie_hsu
 * 
 * @since 2011-7-14
 */
public class Pagination {
	public final static String TOTAL = "total";
	public final static String OFFSET = "offset";
	private int total;
	private int offset;
	private List<? extends Object> data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public List<? extends Object> getData() {
		return data;
	}

	public void setData(List<? extends Object> data) {
		this.data = data;
	}

}
