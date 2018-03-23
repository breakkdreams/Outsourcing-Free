package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ChargeParamDO;

import java.util.Date;

@ApiModel(value = "", description = "充值赠送类")
public class ChargeParamBO {
	@ApiModelProperty("id")
	private Integer chargeParamId;
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
	@ApiModelProperty("开始时间Str")
	private String startTimeStr;

	public ChargeParamBO(ChargeParamDO chargeParamDO) {
		this.chargeParamId = chargeParamDO.getId();
		this.startTime = chargeParamDO.getStartTime();
		this.firstWeek = chargeParamDO.getFirstWeek();
		this.distributorId = chargeParamDO.getDistributorId();
		this.firstMonth = chargeParamDO.getFirstMonth();
		this.firstSeason = chargeParamDO.getFirstSeason();
		this.totalMoney = chargeParamDO.getTotalMoney();
		this.startTimeStr = DateUtil.getFormatDateTime(chargeParamDO.getStartTime(),"yyyy-MM-dd");
	}

	public Integer getChargeParamId() {
		return chargeParamId;
	}

	public void setChargeParamId(Integer chargeParamId) {
		this.chargeParamId = chargeParamId;
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

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
}
