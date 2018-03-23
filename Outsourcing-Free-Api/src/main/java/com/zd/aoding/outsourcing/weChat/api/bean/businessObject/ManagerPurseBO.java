package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerPurseDO;

@ApiModel(value = "", description = "管理员钱包展示类")
public class ManagerPurseBO {
	private Integer managerPurseId;
	@ApiModelProperty("管理员id")
	private Integer managerId;
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
	@ApiModelProperty("类型")
	private Integer type;
	
	public ManagerPurseBO() {
		super();
	}
	public ManagerPurseBO(ManagerPurseDO managerPursePo) {
		super();
		this.managerPurseId = managerPursePo.getId();
		this.managerId = managerPursePo.getManagerId();
		this.appCode = managerPursePo.getAppCode();
		this.score = managerPursePo.getScore();
		this.frozenScore = managerPursePo.getFrozenScore();
		this.bonus = managerPursePo.getBonus();
		this.frozenBonus = managerPursePo.getFrozenBonus();
		this.money = managerPursePo.getMoney();
		this.levelOneMoney = managerPursePo.getLevelOneMoney();
		this.levelTwoMoney = managerPursePo.getLevelTwoMoney();
		this.levelThreeMoney = managerPursePo.getLevelThreeMoney();
		this.type = managerPursePo.getType();
	}
	
	public Integer getManagerPurseId() {
		return managerPurseId;
	}
	public void setManagerPurseId(Integer managerPurseId) {
		this.managerPurseId = managerPurseId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
