package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "系统消息阅读记录")
public class SystemNoticeRecordDO extends DOBase {
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("用户id")
	private Integer userId;
	@ApiModelProperty("appCode")
	private String appCode;
	@ApiModelProperty("noticeId")
	private Integer noticeId;
	
	public SystemNoticeRecordDO() {
		super();
	}
	public SystemNoticeRecordDO(Integer userId, String appCode,
			Integer noticeId) {
		super();
		this.userId = userId;
		this.appCode = appCode;
		this.noticeId = noticeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	@Override
	public String toString() {
		return "SystemNoticeRecordDO [id=" + id + ", userId=" + userId
				+ ", appCode=" + appCode + ", noticeId=" + noticeId + "]";
	}
}
