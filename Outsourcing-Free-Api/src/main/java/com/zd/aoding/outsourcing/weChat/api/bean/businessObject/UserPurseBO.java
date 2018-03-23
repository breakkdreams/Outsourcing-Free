package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;

@ApiModel(value = "", description = "用户钱包展示类")
public class UserPurseBO {
	private Integer userPurseId;
	@ApiModelProperty("用户id")
	private Integer userId;
	@ApiModelProperty("appCode")
	private String appCode;
	@ApiModelProperty("积分")
	private Double score;
	@ApiModelProperty("冻结积分")
	private Double frozenScore;
	@ApiModelProperty("奖金")
	private Double bonus;
	@ApiModelProperty("冻结奖金")
	private Double frozenBonus;
	@ApiModelProperty("现金余额")
	private Double money;
	@ApiModelProperty("一级返利金额")
	private Double levelOneMoney;
	@ApiModelProperty("二级返利金额")
	private Double levelTwoMoney;
	@ApiModelProperty("三级返利金额")
	private Double levelThreeMoney;
	
	public UserPurseBO() {
		super();
	}
	public UserPurseBO(UserPurseDO userPursePo) {
		super();
		this.userPurseId = userPursePo.getId();
		this.userId = userPursePo.getUserId();
		this.appCode = userPursePo.getAppCode();
		this.score = userPursePo.getScore();
		this.frozenScore = userPursePo.getFrozenScore();
		this.bonus = userPursePo.getBonus();
		this.frozenBonus = userPursePo.getFrozenBonus();
		this.money = userPursePo.getMoney();
		this.levelOneMoney = userPursePo.getLevelOneMoney();
		this.levelTwoMoney = userPursePo.getLevelTwoMoney();
		this.levelThreeMoney = userPursePo.getLevelThreeMoney();
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
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getLevelOneMoney() {
		return levelOneMoney;
	}
	public void setLevelOneMoney(Double levelOneMoney) {
		this.levelOneMoney = levelOneMoney;
	}
	public Double getLevelTwoMoney() {
		return levelTwoMoney;
	}
	public void setLevelTwoMoney(Double levelTwoMoney) {
		this.levelTwoMoney = levelTwoMoney;
	}
	public Double getLevelThreeMoney() {
		return levelThreeMoney;
	}
	public void setLevelThreeMoney(Double levelThreeMoney) {
		this.levelThreeMoney = levelThreeMoney;
	}
}
