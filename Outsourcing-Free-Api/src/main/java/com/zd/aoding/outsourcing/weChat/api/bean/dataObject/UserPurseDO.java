package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;
import com.zd.aoding.common.Md5.MD5Util;

@ApiModel(value = "", description = "用户钱包")
public class UserPurseDO extends DOBase {
	public final static String tableNamePart_default = "user_purse_";
	@ApiModelProperty("钱包id")
	private Integer id;
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
	@ApiModelProperty("余额（推荐人消费返利）")
	private Double money;
	@ApiModelProperty("一级返利金额")
	private Double levelOneMoney;
	@ApiModelProperty("二级返利金额")
	private Double levelTwoMoney;
	@ApiModelProperty("三级返利金额")
	private Double levelThreeMoney;
	@ApiModelProperty("支付密码")
	private String password;
	private String tableName;
	 
	public UserPurseDO() {
		super();
	}
	public UserPurseDO(Integer userId, String appCode) {
		super();
		this.userId = userId;
		this.appCode = appCode;
		this.score = 0.0;
		this.frozenScore = 0.0;
		this.bonus = 0.0;
		this.frozenBonus = 0.0;
		this.money = 0.0;
		this.password = MD5Util.MD5("000000");
		this.tableName = tableNamePart_default + appCode;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
