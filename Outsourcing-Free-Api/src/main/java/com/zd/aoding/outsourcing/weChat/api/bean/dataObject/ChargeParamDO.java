package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

import java.util.Date;

@ApiModel(value = "", description = "充值赠送信息")
public class ChargeParamDO extends DOBase {
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("开始时间")
	private Date startTime;
	@ApiModelProperty("首周")
	private Double firstWeek;
	@ApiModelProperty("首月")
	private Double firstMonth;
	@ApiModelProperty("首季度")
	private Double firstSeason;
	@ApiModelProperty("赠送金额")
	private Double totalMoney;
	@ApiModelProperty("经销商id")
	private Integer distributorId;

	public ChargeParamDO() {
		super();
	}

	public ChargeParamDO(Date startTime, Double firstWeek, Double firstMonth, Double firstSeason, Double totalMoney, Integer distributorId) {
		this.startTime = startTime;
		this.firstWeek = firstWeek;
		this.firstMonth = firstMonth;
		this.firstSeason = firstSeason;
		this.totalMoney = totalMoney;
		this.distributorId = distributorId;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Double getFirstWeek() {
		return firstWeek;
	}

	public void setFirstWeek(Double firstWeek) {
		this.firstWeek = firstWeek;
	}

	public Double getFirstMonth() {
		return firstMonth;
	}

	public void setFirstMonth(Double firstMonth) {
		this.firstMonth = firstMonth;
	}

	public Double getFirstSeason() {
		return firstSeason;
	}

	public void setFirstSeason(Double firstSeason) {
		this.firstSeason = firstSeason;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}

	@Override
	public String toString() {
		return "ChargeParamDO{" +
				"id=" + id +
				", startTime=" + startTime +
				", firstWeek=" + firstWeek +
				", firstMonth=" + firstMonth +
				", firstSeason=" + firstSeason +
				", totalMoney=" + totalMoney +
				", distributorId=" + distributorId +
				'}';
	}
}
