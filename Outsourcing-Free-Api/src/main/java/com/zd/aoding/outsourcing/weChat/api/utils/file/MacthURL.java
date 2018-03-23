package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ServerMathchBO;

/**
 * 用于匹配分布式图片服务器的算法
 * @author kcj
 *
 */
public class MacthURL{
	public static ServerMathchBO getMathchURL(){
		Map<String, ServerMathchBO> clients = UrlTable.getUrlTable();
		ServerMathchBO server = null;
		if (clients != null) {
			boolean flag = true;
			for (Map.Entry<String, ServerMathchBO> entry : clients.entrySet()) {
				ServerMathchBO sm = entry.getValue();
				if(sm!=null&&sm.getState()!=null&&sm.getState()==1&&(sm.getTotalSize()>(sm.getFileSize()==null?0:sm.getFileSize()))){
					if(flag){
						server = sm;
					}else{
						if(sm.getTotalSize()-(sm.getFileSize()==null?0:sm.getFileSize())>(server.getTotalSize()-(server.getFileSize()==null?0:server.getFileSize()))){
							server = sm ;
						}
					}
					flag = false;
				}
				System.out.println(sm.getUrl()+"    "+sm.getState()+"    "+sm.getFileSize());
			}
		}
		return server;
	}
}
