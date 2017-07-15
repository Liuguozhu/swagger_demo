//package org.sidao.cm.common;
//
//import javax.servlet.http.Cookie;
//
//
//import org.sidao.common.CommonConfigUtils;
//
//import com.jfinal.aop.Interceptor;
//import com.jfinal.aop.Invocation;
//public class CommonInterceptor implements Interceptor {
//    public final static String SESSION_NAME_USER="cuser";
//
//    /**
//     *
//     */
//    public void intercept(Invocation ai) {
//
//    	String snm=ai.getController().getRequest().getServerName();
//
//    	String ssoDomain = CommonConfigUtils.getConfig().getString("system.domain.sso");
//    	if(snm.equals(ssoDomain)){
//
//    		Cookie[] cookies= ai.getController().getRequest().getCookies();
//        	String encloginedInfoStr=null;
//        	for(int i=0;i<cookies.length;i++){
//        		if(cookies[i].getName().equalsIgnoreCase("WOSDK_OPERATION_SSO_TOKEN")){
//        			encloginedInfoStr=cookies[i].getValue();
//        		}
//        	}
//
//        	if(encloginedInfoStr != null){
//
//        		ai.getController().setSessionAttr(SESSION_NAME_USER,null);
//
//        		String loginedInfoStr = null;
//        		try {
//    				loginedInfoStr=EncryptUtil.decryptAESByDefaultAndBase64(encloginedInfoStr, "utf-8",  EncryptKeyUtil.getPWEncryptKey());
//    			} catch (Exception e) {
//
//    				e.printStackTrace();
//    			}
//
//
////            	String account = null;
//            	// TODO 解析JSON获取SSO登陆用户账号account
//
//    	    	// TODO 根据 SSO 的 account 单点登录
//        		Users ssoUsers = Users.dao.findFirst("select * from PLAT_USER_CHARGE where ACCOUNT=?",new Object[]{"sa"});
//    	    	if(loginedInfoStr != null){
//    	    		ai.getController().setSessionAttr(SESSION_NAME_USER,ssoUsers);
//    	    		ai.invoke();
//    	    		return;
//    	    	}
//
//    	    	// TODO 提示单点登录失败
//
//        	}
//
//    		return;
//    	}
//
//    	Users localUsers = ai.getController().getSessionAttr(SESSION_NAME_USER);
//
//		if(localUsers!=null){
//            ai.invoke();
//            return;
//        }
//		ai.getController().redirect("/");
//        ai.getController().setAttr("message","请登录系统！");
//
//
//
//    }
//}