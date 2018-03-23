package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.StatisticDO;

@ApiModel(value = "", description = "statistic类")
public class StatisticBO {
	@ApiModelProperty("statisticid")
	private Integer statisticId;
	@ApiModelProperty("订单数")
	private Integer orderCount;
	@ApiModelProperty("销售数量")
	private Integer saleCount;
	@ApiModelProperty("营业额")
	private Double turnover;
	@ApiModelProperty("成本")
	private Double cost;
	@ApiModelProperty("净利")
	private Double netProfit;
	@ApiModelProperty("日期")
	private String timeStr;
	@ApiModelProperty("日期")
	private String time;
	@ApiModelProperty("积分金额")
	private Double orderMoney;
	
	public StatisticBO(StatisticDO statisticPo) {
		this.statisticId = statisticPo.getId();
		this.orderCount = statisticPo.getOrderCount();
		this.saleCount = statisticPo.getSaleCount();
		this.turnover = statisticPo.getTurnover();
		this.cost = statisticPo.getCost();
		this.netProfit = statisticPo.getNetProfit();
		this.timeStr = DateUtil.Format("yyyy-MM-dd", statisticPo.getCreateTime());
		this.orderMoney = statisticPo.getOrderMoney();
		this.time = statisticPo.getTime();
	}

	public Integer getStatisticId() {
		return statisticId;
	}

	public void setStatisticId(Integer statisticId) {
		this.statisticId = statisticId;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(Double netProfit) {
		this.netProfit = netProfit;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
}
