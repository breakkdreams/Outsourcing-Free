package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;


public class AccountOpenIdRelationDO extends DOBase {
	
	@ApiModelProperty("账号opinId表id")
    private Integer id;
	@ApiModelProperty("账号id")
    private Integer accountId;
	@ApiModelProperty("openId")
    private String openId;
	
	
	public AccountOpenIdRelationDO() {
		super();
	}
	public AccountOpenIdRelationDO(Integer accountId, String openId) {
		super();
		this.accountId = accountId;
		this.openId = openId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "AccountOpenIdRelationDO [id=" + id + ", accountId=" + accountId
				+ ", openId=" + openId + ", toString()=" + super.toString()
				+ "]";
	}
}