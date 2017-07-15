/**
 * 
 */
package org.sidao.common;

/**
 * @author wxc
 *
 */
public class PhoneUtils {
	private static int count=0;
	private static String[] phones=new String[]{
		"15810493315",
		"15801648492",
		"15811463239",
		"15810490762",
		"15810490812",
		"15801648545",
		"15810490814",
		"15801651854"};
	/**
	 * 得到下一个手机号
	 * @return
	 */
	public static String nextPhoneNumber(){
		count++;
		if(count>phones.length-1){
			count-=(phones.length-1);
		}
		return phones[count];
	}
}
