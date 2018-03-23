package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MailDO;

@ApiModel(value = "", description = "站内信")
public class MailBO {
	@ApiModelProperty("站内信id")
	private Integer mailId;
	@ApiModelProperty("消息")
	private String message;
	@ApiModelProperty("时间")
	private String timeStr;
	@ApiModelProperty("状态")
	private Integer status;
	@ApiModelProperty("状态")
	private Integer statusAdmin;
	@ApiModelProperty("1新订单 2库存不足 3充值申请 4余额不足")
	private Integer contentType;
	@ApiModelProperty("新订单时为产品id")
	private Integer contentId;
	
	public MailBO() {
		super();
	}
	public MailBO(MailDO mailPo) {
		super();
		this.mailId = mailPo.getId();
		this.message = mailPo.getMessage();
		this.timeStr = DateUtil.Format("yyyy-MM-dd HH:mm", mailPo.getCreateTime());
		this.status = mailPo.getStatus();
		this.statusAdmin = mailPo.getStatusAdmin();
		this.contentType = mailPo.getContentType();
		this.contentId = mailPo.getContentId();
	}
	public Integer getMailId() {
		return mailId;
	}
	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatusAdmin() {
		return statusAdmin;
	}
	public void setStatusAdmin(Integer statusAdmin) {
		this.statusAdmin = statusAdmin;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

}
