package test;

import com.jfinal.core.Const;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;

public class RedisTest2 {
	static Prop redisProp=PropKit.use("RedisConnector.properties",Const.DEFAULT_ENCODING);
	static RedisPlugin jp= new RedisPlugin("defalut",redisProp.get("host"));
	static{
		jp.start();
	}
	
	public static void  main(String[] args){
		Cache redis=Redis.use();
		String key=redis.randomKey();
		System.out.println(key);
	}
}
