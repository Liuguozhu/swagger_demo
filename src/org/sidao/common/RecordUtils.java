/**
 * 
 */
package org.sidao.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.ListUtils;
//import org.apache.poi.ss.formula.functions.T;

import com.jfinal.plugin.activerecord.Record;

/**
 * @author xcwang
 *
 */
public class RecordUtils {
	@SuppressWarnings("unchecked")
	public static List<Record> compareRecord(List<Record> records1,List<Record> records2,final String attname){
		List<Record> result=new ArrayList<Record>();
		result=ListUtils.union(records1, records2);
		Collections.sort(result, new Comparator<Record>() {  
	      	@Override
			public int compare(Record r1, Record r2) {
	      		return r2.getTimestamp(attname).compareTo(r2.getTimestamp(attname));
			}
		});
		return result;
		
	}
}
