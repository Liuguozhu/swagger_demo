package org.sidao.jfinal;


import org.sidao.cm.common.CommonController;
//import org.sidao.cm.common.CommonInterceptor;
import swagger.controller.SwaggerController;
import org.sidao.common.QuartzPlugin;
import org.sidao.jdbc.BonecpPlugin;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.log.Log4jLoggerFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.ehcache.EhCachePlugin;


/**
 * API引导式配置
 */
public class JfinalConfig extends JFinalConfig {
    /**
     * Logger for this class
     */
//	private static final Logger logger = Logger.getLogger(JfinalConfig.class);

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setLoggerFactory(new Log4jLoggerFactory());
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", CommonController.class);
        //@configcontoller
        me.add("/swaggerTest", SwaggerController.class);
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {

        //配置数据
//        BonecpPlugin bp = new BonecpPlugin();
//        me.add(bp);
//        ActiveRecordPlugin arp = new ActiveRecordPlugin("base", bp);
//        SwaggerPlugin swagger=new SwaggerPlugin();
//        me.add(swagger);

        //配置第二个数据库
        //BonecpPlugin2 bp_wosdk=new BonecpPlugin2();
        //me.add(bp_wosdk);
        //ActiveRecordPlugin arp_wosdk = new ActiveRecordPlugin("wosdk",bp_wosdk);
        //arp_wosdk.setDialect(new MysqlDialect());
        //arp_wosdk.setShowSql(true);
        //me.add(arp_wosdk);

        //配置定时任务
        QuartzPlugin qp = new QuartzPlugin();
        me.add(qp);

        //配置redis
//		Prop redisProp=PropKit.use("RedisConnector.properties",Const.DEFAULT_ENCODING);
//		RedisPlugin jp= new RedisPlugin("defalut",redisProp.get("host"));
//		JedisPlugin jp= new JedisPlugin();
//		me.add(jp);
        //logger.info("配置redis数据源："+redisProp.get("host"));
        //配置ehcache
        me.add(new EhCachePlugin());

//        arp.setDialect(new MysqlDialect());
//        arp.setShowSql(false);
//        me.add(arp);
    }

    /**
     * 配置全局拦截
     */
    public void configInterceptor(Interceptors me) {
//        me.add(new CommonInterceptor());
    }

    /**
     * 配置处理
     */
    public void configHandler(Handlers me) {
//        me.add(new SwaggerHandler());
    }


}
