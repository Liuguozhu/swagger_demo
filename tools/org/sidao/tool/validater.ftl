package org.sidao.cm.${modelName?lower_case};

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * ${modelName}Validator.
 */
public class ${modelName}Validator extends Validator {
	
	protected void validate(Controller controller) {
        //validateRequiredString("${modelName?lower_case}.", "Msg", "请输入${modelName}标题!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(${modelName}.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/${modelName?lower_case}/save"))
			controller.render("add.html");
		else if (actionKey.equals("/${modelName?lower_case}/update"))
			controller.render("edit.html");
	}
}
