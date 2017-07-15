/**
 * 
 */
package org.sidao.common;

/**
 * @author xcwang
 *
 */
public class JobUtil {
	private static ThreadPool tpool=null;

	public static void execute(Runnable job){
		if(tpool==null){
			tpool=new ThreadPool(10);
		}
		tpool.execute(job);
	}

}
