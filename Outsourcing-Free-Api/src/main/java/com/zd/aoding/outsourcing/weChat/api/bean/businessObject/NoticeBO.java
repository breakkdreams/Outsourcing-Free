package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.NoticeDO;

@ApiModel(value = "", description = "系统消息展示类")
public class NoticeBO {
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "推送appCode")
	private String app;
	@ApiModelProperty(value = "推送时间")
	private String timeStr;
	@ApiModelProperty(value = "推送平台")
	private String companyName;
	
	public NoticeBO() {
		super();
	}
	
	public NoticeBO(NoticeDO noticePo) {
		super();
		this.id = noticePo.getId();
		this.title = noticePo.getTitle();
		this.content = noticePo.getContent();
		this.app = noticePo.getAppCode();
		this.timeStr = DateUtil.Format("yyyy-MM-dd", noticePo.getCreateTime());
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
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
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
