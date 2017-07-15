/**
 * 
 */
package org.sidao.tool;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author wxc
 *
 */
public class CodeUtils {
	public static void replaceFgang(String filepath){
		File file=new File(filepath);
		if(file.exists()){
			try {
				String content=FileUtils.readFileToString(file);
				content=StringUtils.replace(content, "\\", "");
				FileUtils.writeStringToFile(file, content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
