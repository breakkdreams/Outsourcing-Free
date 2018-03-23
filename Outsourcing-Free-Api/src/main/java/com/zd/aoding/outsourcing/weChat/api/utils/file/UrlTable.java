package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.util.HashMap;
import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ServerMathchBO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigConsts;

import net.spy.memcached.MemcachedClient;


public class UrlTable {
	private static MemcachedClient memcachedClient;
	static{
		memcachedClient = SpringBeanFactory.getMemcacheClient();
	}
	public static void loadUrlTable(){
		Map<String, ServerMathchBO> clients = new HashMap<String, ServerMathchBO>();
		if (ConfigConsts.tableUrl != null) {
			String[] strs = ConfigConsts.tableUrl.split(",");
			for (String str : strs) {
				String[] ss = str.split("-");
				if (ss.length == 2) {
					ServerMathchBO sui = new ServerMathchBO();
					sui.setUrl(ss[0]);
					sui.setTotalSize(Long.parseLong(ss[1])*1024*1024);
					sui.setState(0);
					clients.put(ss[0], sui);
				} else {
					System.out.println("系统配置文件异常，子服务器没有设置硬盘容量!");
				}
			}
		}
		memcachedClient.set("CommunityUrlTable", 24*60*60*1000*365, clients);
	}
	@SuppressWarnings("unchecked")
	public static Map<String, ServerMathchBO> getUrlTable(){
		Map<String, ServerMathchBO> result = (Map<String, ServerMathchBO>)memcachedClient.get("CommunityUrlTable");
		if(result==null){
			loadUrlTable();
		}
		result = (Map<String, ServerMathchBO>)memcachedClient.get("CommunityUrlTable");
		if(result==null){
			result = new HashMap<String, ServerMathchBO>();
		}
		return result;
	}
	public static int setServerMathch(Map<String, ServerMathchBO> clients){
		memcachedClient.set("CommunityUrlTable",24*60*60*1000*365,clients);
		return 1;
	}
	public static ServerMathchBO getServerMathch(String key){
		Map<String, ServerMathchBO> result = getUrlTable();
		if(result!=null){
			ServerMathchBO sui = result.get(key);
			return sui;
		}
		return null;
	}
}
