package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "地址类")
public class TakeMoneyDO extends DOBase {
	public static final int STATE_NOW = 0;
	public static final int STATE_PASS = 1;
	public static final int STATE_FAIL = -1;
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("userId")
	private Integer userId;
	@ApiModelProperty("银行卡id")
	private Integer bankId;
	@ApiModelProperty("银行卡号")
	private String bankNum;
	@ApiModelProperty("开户行")
	private String bankKaihu;
	@ApiModelProperty("银行名称")
	private String bankName;
	@ApiModelProperty("真实姓名")
	private String userName;
	@ApiModelProperty("提现金额")
	private Double money;
	@ApiModelProperty("审核状态（0审核中,1审核通过,-1审核失败）")
	private Integer state;
	@ApiModelProperty("操作人员")
	private Integer managerId;
	@ApiModelProperty("提现手续费")
	private String charge;

	public TakeMoneyDO(Integer userId, Integer bankId, String bankNum, String bankKaihu, String userName,
			Double money,String bankName) {
		super();
		this.userId = userId;
		this.bankId = bankId;
		this.bankNum = bankNum;
		this.bankKaihu = bankKaihu;
		this.userName = userName;
		this.money = money;
		this.state = 0;
		this.bankName = bankName;
	}
	
	public TakeMoneyDO() {
		super();
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

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getBankKaihu() {
		return bankKaihu;
	}

	public void setBankKaihu(String bankKaihu) {
		this.bankKaihu = bankKaihu;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	@Override
	public String toString() {
		return "TakeMoneyDO [id=" + id + ", userId=" + userId + ", bankId=" + bankId + ", bankNum=" + bankNum
				+ ", bankKaihu=" + bankKaihu + ", userName=" + userName + ", money=" + money + ", state=" + state
				+ ", managerId=" + managerId + ", toString()=" + super.toString() + "]";
	}
}
