package com.zd.aoding.outsourcing.weChat.api.utils.file;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 用于spring的配置文件中的bean获取
 * 
 * @author kcj
 *
 */
public class SpringBeanFactory {
	private static MemcachedClient memcachedClient;

	@SuppressWarnings({ "unchecked", "resource" })
	public static <T> T getSpringBean(String xmlPathAndNameForSpring, String beanId, Class<T> clz) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(xmlPathAndNameForSpring);
		BeanFactory factory = (BeanFactory) ctx;
		T bean = (T) factory.getBean(beanId);
		return bean;
	}

	public static MemcachedClient getMemcacheClient() {
		if (memcachedClient == null) {
			memcachedClient = getSpringBean("classpath:spring/memcache-config.xml", "memcachedClient", MemcachedClient.class);
		}
		try {
			memcachedClient.get("test_netword_connect_for_memcachedClient");
		} catch (Exception e) {
			memcachedClient = getSpringBean("classpath:spring/memcache-config.xml", "memcachedClient", MemcachedClient.class);
		}
		return memcachedClient;
	}

	public static void main(String[] args) {
		getMemcacheClient();
	}
}
