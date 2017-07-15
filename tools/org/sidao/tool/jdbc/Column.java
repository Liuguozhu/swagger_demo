package org.sidao.tool.jdbc;

import java.io.Serializable;
import java.sql.Types;

public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8606187740125202019L;

	public final static int NONE_POLICY = 0;

	public final static int IDENTITY_POLICY = 1;

	public final static int SEQUENCE_POLICY = 2;

	public final static int MAX_ID_POLICY = 3;

	public final static int CODE_GEN_POLICY = 4;

	private String columnName;

	private String tableName;
	
	private String jsTypeName;

	private int columnSize;

	private int decimalDigits;

	private boolean nullable;

	private int typeCode;

	private String comment;

	private boolean primaryKey;
	
	private boolean defaultValue;

	private int pkPolicy;

	private String policyExp;

	private String refCode;
	
	private boolean isSearchField=false;
	private boolean isCombo=false;
	private boolean isHidden=false;
	private String fieldString;

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getPolicyExp() {
		return policyExp;
	}

	public void setPolicyExp(String policyExp) {
		this.policyExp = policyExp;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String toString() {
		return this.getColumnName();
	}

	public int getTypeCode() {
		return typeCode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPkPolicy() {
		return pkPolicy;
	}

	public void setPkPolicy(int pkPolicy) {
		this.pkPolicy = pkPolicy;
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getJsTypeName() {
		if(this.jsTypeName==null){
			switch (typeCode) {
			case Types.VARCHAR:
				jsTypeName="STRING";
				break;
			case Types.DATE:
				jsTypeName="DATE";
				break;
			case Types.BIGINT:
				jsTypeName="NUMBER";
				break;
			case Types.DECIMAL:
				jsTypeName="NUMBER";
				break;
			case Types.DOUBLE:
				jsTypeName="NUMBER";
				break;
			case Types.FLOAT:
				jsTypeName="NUMBER";
				break;
			case Types.INTEGER:
				jsTypeName="NUMBER";
				break;
			case Types.BIT:
				jsTypeName="BOOL";
				break;
			case Types.NUMERIC:
				jsTypeName="NUMBER";
				break;
			case Types.TIMESTAMP:
				jsTypeName="DATE";
				break;
			case Types.SMALLINT:
				jsTypeName="NUMBER";
				break;
			case Types.CHAR:
				jsTypeName="STRING";
				break;
			default:
				jsTypeName="STRING";
				break;
			}
		}
		return jsTypeName;
	}

	public void setJsTypeName(String jsTypeName) {
		this.jsTypeName = jsTypeName;
	}

	public boolean isSearchField() {
		return isSearchField;
	}

	public void setSearchField(boolean isSearchField) {
		this.isSearchField = isSearchField;
	}

	public boolean isCombo() {
		return isCombo;
	}

	public void setCombo(boolean isCombo) {
		this.isCombo = isCombo;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getFieldString() {
		return fieldString;
	}

	public void setFieldString(String fieldString) {
		this.fieldString = fieldString;
	}
}
