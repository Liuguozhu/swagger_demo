package org.sidao.tool.jdbc;

import java.util.TreeMap;

/**
 * @author rickie_hsu
 * @since 2011-6-7
 */
public enum Operator {
    EQUAL(" = "),
    NOT_EQUAL(" <> "),
    LIKE(" LIKE "),
    GREATER_THAN(" > "),
    LESS_THAN(" < "),
    LESS_THAN_OR_EQUAL(" <= "),
    GREATER_THAN_OR_EQUAL(" >= "),
    IN(" IN "),
    NOT_IN(" NOT IN "),
    BETWEEN(" BETWEEN "),
    IS_NULL(" IS NULL "),
    IS_NOT_NULL(" IS NOT NULL "),
    AND(" AND "),
    OR(" OR ");

    private final String opstr;
    private static TreeMap<String,Operator> operators = buildOperators();
    private static TreeMap<String,Operator> buildOperators(){
        TreeMap<String,Operator> map = new TreeMap<String, Operator>();
        map.put("=",EQUAL);
        map.put("eq",EQUAL);
        map.put("<>",NOT_EQUAL);
        map.put("!=",NOT_EQUAL);
        map.put("neq",NOT_EQUAL);
        map.put("like",LIKE);
        map.put(">=", GREATER_THAN);
        map.put("gt", GREATER_THAN);
        map.put("<=", LESS_THAN);
        map.put("lt", LESS_THAN);
        map.put("ge", GREATER_THAN_OR_EQUAL);
        map.put("le", LESS_THAN_OR_EQUAL);
        map.put("between", BETWEEN);
        map.put("in", IN);
        map.put("notin", NOT_IN);
        map.put("isnull", IS_NULL);
        map.put("isnotnull", IS_NOT_NULL);
        return map;
    }

    Operator(final String opstr) {
        this.opstr = opstr;
    }

    public String toString() {
        return this.opstr;
    }
    /**
     * 操作符的维度，即该操作符需要几个参数例如‘=’为2维
     * @return
     */
    public int getType(){
    	switch(this){
    	case IN:{
    		return 4;
    	}
    	case NOT_IN:{
    		return 4;
    	}
    	case BETWEEN:{
    		return 3;
    	}
    	case IS_NULL:{
    		return 1;
    	}
    	case IS_NOT_NULL:{
    		return 1;
    	}
    	case AND:{
    		return -9;
    	}
    	case OR:{
    		return -9;
    	}
    	default:
    		return 2;
    	}
    }
    public static Operator parse(String value){
        if(value==null){
            return null;
        }
        return operators.get(value);
    }
}
