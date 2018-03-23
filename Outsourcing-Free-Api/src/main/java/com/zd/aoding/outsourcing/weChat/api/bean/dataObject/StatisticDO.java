package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "统计")

public class StatisticDO extends DOBase {

	@ApiModelProperty("id")
	private Integer id;
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
	@ApiModelProperty("订单总额")
	private Double orderMoney;
	@ApiModelProperty("时间")
	private String time;
	
	
	public StatisticDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatisticDO(Integer orderCount,Integer saleCount,Double turnover,Double cost,Double netProfit) {
		super();
		this.orderCount = orderCount;
		this.saleCount = saleCount;
		this.turnover = turnover;
		this.cost = cost;
		this.netProfit = netProfit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "StatisticDO [id=" + id + ", orderCount=" + orderCount + ", saleCount=" + saleCount + ", turnover="
				+ turnover + ", cost=" + cost + ", netProfit=" + netProfit + ", toString()=" + super.toString() + "]";
	}
}
