package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "登录信息")
public class AccountLoginDO extends DOBase {

	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("账号id")
	private Integer accountId;
	@ApiModelProperty("登录时间")
	private Date loginTime;
	@ApiModelProperty("登录ip")
	private String loginIp;

	public AccountLoginDO(Integer accountId, String loginIp) {
		super();
		this.accountId = accountId;
		this.loginTime = new Date();
		this.loginIp = loginIp;
	}

	public AccountLoginDO() {
		super();
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

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Override
	public String toString() {
		return "AccountLoginDO [id=" + id + ", accountId=" + accountId + ", loginTime=" + loginTime + ", loginIp="
				+ loginIp + ", toString()=" + super.toString() + "]";
	}

}
