package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.io.Serializable;

public class ServerMathchBO implements Serializable{
	private static final long serialVersionUID = 201606060913L;
	private String url;			//外网地址
	private Integer state;		//服务器是否正常	1.服务器开启  0.服务器未连接
	private Long createTs=0l;	//最近的心跳时间
	private Long fileSize;	//子服务器现有大小
	private Long totalSize;	//设置的总空间大小 
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Long createTs) {
		this.createTs = createTs;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	
}

