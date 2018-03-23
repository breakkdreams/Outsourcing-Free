package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "站内信")
public class MailDO extends DOBase {
	/**
     * 商户
     */
    public static final Integer TYPE_DEALER = 1;
    /**
     * 客户
     */
    public static final Integer TYPE_COMPANY = 2;
    /**
     * 未读
     */
    public static final Integer STATUS_DEFAULT = 0;
    /**
     * 已读
     */
    public static final Integer STATUS_READ = 1;
    /**
     * 新订单
     */
    public static final Integer contentType_newOrder = 1;
    /**
     * 库存不足
     */
    public static final Integer contentType_stock = 2;
    /**
     * 充值申请
     */
    public static final Integer contentType_recharge = 3;
    /**
     * 余额不足
     */
    public static final Integer contentType_balance = 4;
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("1发给商户和总后台 2发给客户company")
	private Integer type;
	@ApiModelProperty("对象id type=1时商家id type=2时companyId ")
	private Integer ownerId;
	@ApiModelProperty("信息")
	private String message;
	@ApiModelProperty("0未读  1已读")
	private Integer status;
	@ApiModelProperty("总后台标示 0未读 1已读")
	private Integer statusAdmin;
	@ApiModelProperty("1新订单 2库存不足 3充值申请 4余额不足")
	private Integer contentType;
	@ApiModelProperty("新订单时为产品id")
	private Integer contentId;
	
	public MailDO() {
		super();
	}
	public MailDO(Integer type, Integer ownerId, String message,
			Integer status, Integer statusAdmin, Integer contentType) {
		super();
		this.type = type;
		this.ownerId = ownerId;
		this.message = message;
		this.status = status;
		this.statusAdmin = statusAdmin;
		this.contentType = contentType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
