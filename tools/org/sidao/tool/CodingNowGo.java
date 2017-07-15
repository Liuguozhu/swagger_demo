/**
 * 
 */
package org.sidao.tool;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationMap;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.sidao.jdbc.ConnectionPool;
import org.sidao.tool.jdbc.Table;

/**
 * @author xcwang
 *
 */
public class CodingNowGo {
	final static String TOOL_PATH="tools/org/sidao/tool/";
	final static String FILE_PATH="src/org/sidao/cm/";
	final static String MAIN_JAVA="src/org/sidao/jfinal/JfinalConfig.java";
	//src/org/sidao/common/NcmRun.java
	/**
	 * @param args
	 * @throws ConfigurationException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ConfigurationException, SQLException, ClassNotFoundException, IOException {
		//读取配置信息
		Configuration codingConfig = new PropertiesConfiguration(TOOL_PATH+"coding.properties");
		ConfigurationMap mapConfig= new ConfigurationMap(codingConfig);
		String modelname=StringUtils.lowerCase(codingConfig.getString("modelName"));
		String modelName=codingConfig.getString("modelName");
		String tableName=codingConfig.getString("tableName");
		int html=codingConfig.getInt("html");
		Map data=mapConfig;
	
		Generator gtor=new Generator();
		String modelFile=FILE_PATH+ modelname+"/" +modelName+".java";
		String controllersFile=FILE_PATH+ modelname+"/" +modelName+"Controller.java";
		String validaterFile=FILE_PATH+ modelname+"/" +modelName+"Validator.java";
		String interceptorFile=FILE_PATH+ modelname+"/" +modelName+"Interceptor.java";
		
		//生产java文件
		if(!new File(modelFile).exists())
		gtor.generateFile(TOOL_PATH+"/model.ftl", modelFile, data);
		if(!new File(controllersFile).exists())
		gtor.generateFile(TOOL_PATH+"/controllers.ftl", controllersFile, data);
		if(!new File(validaterFile).exists())
		gtor.generateFile(TOOL_PATH+"/validater.ftl", validaterFile, data);
		if(!new File(interceptorFile).exists())
		gtor.generateFile(TOOL_PATH+"/interceptor.ftl", interceptorFile, data);
		//设置配置文件
		File mainfile=new File(MAIN_JAVA);
		String addpath="me.add(\"/"+modelname+"\", "+modelName+"Controller.class);";
		String mainjava=FileUtils.readFileToString(mainfile);
		if(!mainjava.contains(addpath)){
			mainjava=mainjava.replaceFirst("@configcontoller", "@configcontoller\n"+addpath);
			FileUtils.writeStringToFile(mainfile, mainjava);
		}
		addpath="arp.addMapping(\""+tableName+"\", "+modelName+".class);";
		mainjava=FileUtils.readFileToString(mainfile);
		if(!mainjava.contains(addpath)){
			mainjava=mainjava.replaceFirst("@configmodel", "@configmodel\n"+addpath);
			FileUtils.writeStringToFile(mainfile, mainjava);
		}
		//生成html文件
		if(html!=1){
			return;
		}
		Connection con=ConnectionPool.getConnection();
		Table t=new Table(tableName);
		t.buildMe(con);
		data.put("table", t);
		String addhtml="WebRoot/"+modelname+"/add.html";
		String edithtml="WebRoot/"+modelname+"/edit.html";
		String searchhtml="WebRoot/"+modelname+"/"+modelname+".html";
		
		if(!new File(addhtml).exists()){
			gtor.generateFile(TOOL_PATH+"/add.html.ftl", addhtml, data);
			CodeUtils.replaceFgang(addhtml);
		}
			
		if(!new File(edithtml).exists()){
			gtor.generateFile(TOOL_PATH+"/edit.html.ftl", edithtml, data);
			CodeUtils.replaceFgang(edithtml);
		}
			
		if(!new File(searchhtml).exists()){
			gtor.generateFile(TOOL_PATH+"/search.html.ftl", searchhtml, data);
			CodeUtils.replaceFgang(searchhtml);
		}
			
		
		
		
	}
}
