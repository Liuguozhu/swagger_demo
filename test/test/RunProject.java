package test;

import com.jfinal.core.JFinal;


/**
 * @author xcwang
 *
 */
public class RunProject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 5);
	}

}
