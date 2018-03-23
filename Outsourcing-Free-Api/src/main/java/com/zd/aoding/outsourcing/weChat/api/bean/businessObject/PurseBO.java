package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;

@ApiModel(value = "", description = "钱包")
public class PurseBO {
	@ApiModelProperty("钱包id")
	private Integer userPurseId;
	@ApiModelProperty("用户id")
	private Integer userId;
	@ApiModelProperty("积分")
	private Double score;
	@ApiModelProperty("冻结积分")
	private Double frozenScore;
	@ApiModelProperty("奖金")
	private Double bonus;
	@ApiModelProperty("冻结奖金")
	private Double frozenBonus;
	@ApiModelProperty("所在平台")
	private String appName;
	@ApiModelProperty("现金余额")
	private Double money;

	public PurseBO(UserPurseDO userPursePo) {
		super();
		this.userPurseId = userPursePo.getId();
		this.userId = userPursePo.getUserId();
		this.score = userPursePo.getScore();
		this.frozenScore = userPursePo.getFrozenScore();
		this.bonus = userPursePo.getBonus();
		this.frozenBonus = userPursePo.getFrozenBonus();
		this.money = userPursePo.getMoney();
	}

	public Integer getUserPurseId() {
		return userPurseId;
	}

	public void setUserPurseId(Integer userPurseId) {
		this.userPurseId = userPurseId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getFrozenScore() {
		return frozenScore;
	}

	public void setFrozenScore(Double frozenScore) {
		this.frozenScore = frozenScore;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getFrozenBonus() {
		return frozenBonus;
	}

	public void setFrozenBonus(Double frozenBonus) {
		this.frozenBonus = frozenBonus;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
}
