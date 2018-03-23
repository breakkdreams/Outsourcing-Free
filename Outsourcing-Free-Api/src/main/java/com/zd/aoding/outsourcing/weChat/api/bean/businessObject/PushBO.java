package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PushDO;

@ApiModel(value = "", description = "系统推送展示类")
public class PushBO {
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "推送时间")
	private Date pushTime;
	@ApiModelProperty(value = "推送appCode 多个appCode “，”分隔")
	private String app;
	@ApiModelProperty(value = "推送类型 1短信 2系统消息")
	private Integer type;
	@ApiModelProperty(value = "推送来源 1总后台  2客户后台")
	private Integer source;
	@ApiModelProperty(value = "推送状态 0未推送 1已推送 （-1 系统消息已撤销）")
	private Integer status;
	@ApiModelProperty(value = "推送时间")
	private String timeStr;
	@ApiModelProperty(value = "推送平台")
	private String companyName;
	
	public PushBO() {
		super();
	}
	
	public PushBO(PushDO pushPo) {
		super();
		this.id = pushPo.getId();
		this.title = pushPo.getTitle();
		this.content = pushPo.getContent();
		this.pushTime = pushPo.getPushTime();
		this.app = pushPo.getApp();
		this.type = pushPo.getType();
		this.source = pushPo.getSource();
		this.status = pushPo.getSource();
		this.timeStr = DateUtil.Format("yyyy-MM-dd", pushPo.getCreateTime());
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPushTime() {
		return pushTime;
	}
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
