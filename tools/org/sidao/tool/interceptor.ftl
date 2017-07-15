package org.sidao.cm.${modelName?lower_case};

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * ${modelName}Interceptor
 */
public class ${modelName}Interceptor implements Interceptor {
	
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		inv.invoke();
		
	}
}
