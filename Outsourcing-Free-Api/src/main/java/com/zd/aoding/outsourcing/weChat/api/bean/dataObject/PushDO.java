package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "推送")
public class PushDO extends DOBase {
	/**
	 * 推送短信
	 */
	public final static int type_message = 1;
	/**
	 * 推送系统消息
	 */
	public final static int type_system = 2;
	/**
	 * 总后台推送
	 */
	public final static int source_admin = 1;
	/**
	 * 客户后台推送
	 */
	public final static int source_compony = 2;
	/**
	 * 默认未推送
	 */
	public final static int status_default = 0;
	/**
	 * 已推送
	 */
	public final static int status_push = 1;
	/**
	 * 系统消息已撤销
	 */
	public final static int status_repeal = -1;
	
	@ApiModelProperty(value = "推送id", hidden = true)
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
	@Override
	public String toString() {
		return "PushDO [id=" + id + ", title=" + title + ", content=" + content
				+ ", pushTime=" + pushTime + ", app=" + app + ", type=" + type
				+ ", source=" + source + ", status=" + status + ", toString()="
				+ super.toString() + "]";
	}
}