/**
 * 
 */
package org.sidao.common;

import java.util.List;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * @author xcwang
 *
 */
/**
 * @author xcwang
 *
 */
public class RedisUtils {
	
	/**
	 * 设置key-value
	 * @param key 键
	 * @param value 值
	 * @param senconds 时间-秒，为null，或者0时，永久
	 */
	public static String set(Object key,Object value,Integer seconds){
		Cache redis=Redis.use();
		if(seconds==null||seconds==0){
			return redis.set(key, value);
		}else{
			return redis.setex(key, seconds, value);
		}
	}
	
	/**
	 * 获取值
	 * @param key
	 * @return value
	 */
	public static <T> T get(Object key){
		Cache redis=Redis.use();
		return redis.get(key);
	}
	
	/**
	 * 获取值keys，
	 * @param keys
	 * @return list
	 */
	public static List<?> get(Object... keys){
		Cache redis=Redis.use();
		return redis.mget(keys);
	}
	
	/**
	 * 删除给定的一个 key 不存在的 key 会被忽略
	 * @param key
	 * @return
	 */
	public static Long del(Object key){
		Cache redis=Redis.use();
		return redis.del(key);
	}
	
	/**
	 * 删除给定的多个 key 不存在的 key 会被忽略。
	 * @param keys
	 * @return
	 */
	public static Long del(Object... keys){
		Cache redis=Redis.use();
		return redis.del(keys);
	}
	
	/**
	 * 获取cache对象进行其他复杂操作
	 * @return cache
	 */
	public static Cache getCache(){
		Cache redis=Redis.use();
		return redis;
	}
}

