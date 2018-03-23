package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;

@ApiModel(value = "", description = "账号展示类")
public class AccountBO {
	@ApiModelProperty("账号id")
	private Integer accountId;
	@ApiModelProperty("手机号（登录名）")
	private String username;
	@ApiModelProperty("账号类型")
	private String typeStr;
	@ApiModelProperty("状态")
	private Integer status;
	@ApiModelProperty("状态")
	private Integer type;
	@ApiModelProperty("角色")
	private String roleName;
	@ApiModelProperty("省")
	private String provinceName;
	@ApiModelProperty("推荐码")
	private String refereeCode;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("省")
	private Integer provinceId;

	public AccountBO(){
		super();
	}

	public AccountBO(AccountDO accountPo) {
		accountPo.setPassword("");
		this.accountId = accountPo.getId();
		this.username = accountPo.getUsername();
		this.status = accountPo.getStatus();
		this.type = accountPo.getType();
		switch (accountPo.getType()) {
		case AccountDO.TYPE_MANAGER:
			this.typeStr = "管理员";
			break;
		case AccountDO.TYPE_DISTRIBUTOR:
			this.typeStr = "经销商";
			break;
		case AccountDO.TYPE_BUSINESS:
			this.typeStr = "商家";
			break;
		case AccountDO.TYPE_SALESMAN:
			this.typeStr = "营业员";
			break;
		case AccountDO.TYPE_RECURSIVE:
			this.typeStr = "递推员";
			break;

		case AccountDO.TYPE_USER:
			this.typeStr = "用户";
			break;
		default:
			break;
		}
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getRefereeCode() {
		return refereeCode;
	}

	public void setRefereeCode(String refereeCode) {
		this.refereeCode = refereeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
}
