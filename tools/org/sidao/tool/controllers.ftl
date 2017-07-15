package org.sidao.cm.${modelName?lower_case};

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import java.util.HashMap;
import java.util.Map;
import com.jfinal.plugin.activerecord.Page;

/**
 * ${modelName}Controller
 * 
 */
public class ${modelName}Controller extends Controller {
	public void index() {
		setAttr("${modelName?lower_case}Page", ${modelName}.dao.paginate(getParaToInt(0, 1), 10));
		render("${modelName?lower_case}.html");
	}
	
	public void add() {
	}
	
	public void show(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pagesStart=getParaToInt("iDisplayStart")/getParaToInt("iDisplayLength")+1;
		Page<${modelName}> pages= ${modelName}.dao.paginate(pagesStart, getParaToInt("iDisplayLength"));
		resultMap.put("list", pages.getList());
		resultMap.put("sEcho", getPara("sEcho"));
		resultMap.put("iTotalRecords", pages.getTotalPage());
		resultMap.put("iTotalDisplayRecords", pages.getTotalRow());
		renderJson(resultMap);
	}
	
	@Before(${modelName}Validator.class)
	public void save() {
		getModel(${modelName}.class).save();
		redirect("/${modelName?lower_case}");
	}
	
	public void edit() {
		setAttr("${modelName?lower_case}", ${modelName}.dao.findById(getParaToInt()));
	}
	
	@Before(${modelName}Validator.class)
	public void update() {
		getModel(${modelName}.class).update();
		redirect("/${modelName?lower_case}");
	}
	
	public void delete() {
		${modelName}.dao.deleteById(getParaToInt());
		redirect("/${modelName?lower_case}");
	}
}


