package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingBookDO;

@ApiModel(value = "", description = "订单簿")
public class ShoppingBookBO {
	@ApiModelProperty("id")
	private Integer shoppingBookId;
	@ApiModelProperty("订单提交参数 param ")
	private String param;
	@ApiModelProperty("appCode")
	private String appCode;
	@ApiModelProperty("买家userId")
	private Integer boughtUserId;
	@ApiModelProperty("地址id")
	private Integer boughtAddressId;
	@ApiModelProperty("总价")
	private Double totalFee;
	@ApiModelProperty("订单摘要")
	private String summary;
	@ApiModelProperty("type")
	private Integer type;
	@ApiModelProperty("status")
	private Integer status;
	
	public ShoppingBookBO() {
		super();
	}
	public ShoppingBookBO(ShoppingBookDO shoppingBookPo) {
		super();
		this.shoppingBookId = shoppingBookPo.getId();
		this.param = shoppingBookPo.getParam();
		this.appCode = shoppingBookPo.getAppCode();
		this.boughtUserId = shoppingBookPo.getboughtUserId();
		this.boughtAddressId = shoppingBookPo.getBoughtAddressId();
		this.totalFee = shoppingBookPo.getTotalFee();
		this.summary = shoppingBookPo.getSummary();
		this.type = shoppingBookPo.getType();
		this.status = shoppingBookPo.getStatus();
	}
	public Integer getShoppingBookId() {
		return shoppingBookId;
	}
	public void setShoppingBookId(Integer shoppingBookId) {
		this.shoppingBookId = shoppingBookId;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public Integer getBoughtUserId() {
		return boughtUserId;
	}
	public void setBoughtUserId(Integer boughtUserId) {
		this.boughtUserId = boughtUserId;
	}
	public Integer getBoughtAddressId() {
		return boughtAddressId;
	}
	public void setBoughtAddressId(Integer boughtAddressId) {
		this.boughtAddressId = boughtAddressId;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
