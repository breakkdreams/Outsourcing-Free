package com.zd.aoding.outsourcing.weChat.api.constant;


/** 
 * @ClassName: CacheConsts 
 * @Description: 缓存常量类
 * @author: HCD
 * @date: 2017年12月25日 下午1:11:26  
 */
public class CacheConsts {
	/** 永久保存 */
	public static final  int MEMCACHE_EXP_NEVER_TIME = 2592000;
	/** 缓存长时间过期时间，用于基本不会有数据修改,一定的延时不影响体验 */
	public static final  int MEMCACHE_EXP_LONG_TIME = 3600;
	/** 缓存短时间过期时间，一定的延时不影响体验 */
	public static final  int MEMCACHE_EXP_SHORT_TIME = 300;
}
