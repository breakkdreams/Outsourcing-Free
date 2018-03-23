package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.TakeMoneyDO;

@ApiModel(value = "", description = "banner类")
public class TakeMoneyBO {
	@ApiModelProperty("id")
	private Integer takeMoneyId;
	@ApiModelProperty("userId")
	private Integer userId;
	@ApiModelProperty("银行卡id")
	private Integer bankId;
	@ApiModelProperty("银行卡号")
	private String bankNum;
	@ApiModelProperty("开户行")
	private String bankKaihu;
	@ApiModelProperty("真实姓名")
	private String userName;
	@ApiModelProperty("提现金额")
	private Double money;
	@ApiModelProperty("审核状态（0审核中,1审核通过,-1审核失败）")
	private Integer state;
	@ApiModelProperty("操作人员")
	private Integer managerId;
	@ApiModelProperty("审核状态Str")
	private String stateStr;
	@ApiModelProperty("用户名")
	private String name;
	@ApiModelProperty("银行名称")
	private String bankName;
	@ApiModelProperty("时间")
	private String createTime;
	@ApiModelProperty("提现手续费")
	private String charge;

	public TakeMoneyBO(TakeMoneyDO takeMoneyDO) {
		super();
		this.takeMoneyId = takeMoneyDO.getId();
		this.userId = takeMoneyDO.getUserId();
		this.bankId = takeMoneyDO.getBankId();
		this.bankNum = takeMoneyDO.getBankNum();
		this.bankKaihu = takeMoneyDO.getBankKaihu();
		this.userName = takeMoneyDO.getUserName();
		this.money = takeMoneyDO.getMoney();
		this.charge = takeMoneyDO.getCharge();
		this.state = takeMoneyDO.getState();
		this.bankName = takeMoneyDO.getBankName();
		this.createTime = DateUtil.Format("yyyy-MM-dd", takeMoneyDO.getCreateTime());
		this.managerId = takeMoneyDO.getManagerId();
		switch (state) {
		case TakeMoneyDO.STATE_PASS:
			this.stateStr = "审核通过";
			break;
		case TakeMoneyDO.STATE_NOW:
			this.stateStr = "审核中";
			break;
		case TakeMoneyDO.STATE_FAIL:
			this.stateStr = "审核失败";
			break;
		default:
			break;
		}
	}

	public Integer getTakeMoneyId() {
		return takeMoneyId;
	}

	public void setTakeMoneyId(Integer takeMoneyId) {
		this.takeMoneyId = takeMoneyId;
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

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}
}
