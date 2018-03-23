package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderItemDO;

@ApiModel(value = "", description = "订单明细")
public class ShoppingOrderItemBO {
	@ApiModelProperty("id")
	private Integer shoppingOrderItemId;
	@ApiModelProperty("产品配置组合id")
	private Integer optionId;
	@ApiModelProperty("产品id")
	private Integer goodsId;
	@ApiModelProperty("订单id")
	private Integer orderId;
	@ApiModelProperty("实际单价")
	private Double actualPrice;
	@ApiModelProperty("购买数量")
	private Integer quantity;
	@ApiModelProperty("原价")
	private Double originalPrice;
	@ApiModelProperty("展示小图片")
	private String showImg;
	@ApiModelProperty("展示产品名称")
	private String showGoodsName;
	@ApiModelProperty("配置组合名称")
	private String showOption;
	@ApiModelProperty("产品总价小计")
	private Double itemTotalFee;
	@ApiModelProperty("成本价")
	private Double costPrice;
	
	@ApiModelProperty("状态")
	private Integer status;
	@ApiModelProperty("产品类型")
	private Integer goodsType;
	@ApiModelProperty("订单状态")
	private Integer process;
	@ApiModelProperty("积分抵扣价")
	private Integer integralDeductible;
	@ApiModelProperty("积分抵扣百分比")
	private Double integralPercent;
	@ApiModelProperty("返利")
	private Integer rebate;
	
	
	public ShoppingOrderItemBO() {
		super();
	}
	public ShoppingOrderItemBO(ShoppingOrderItemDO shoppingOrderItemPo) {
		super();
		this.shoppingOrderItemId = shoppingOrderItemPo.getId();
		this.optionId = shoppingOrderItemPo.getOptionId();
		this.goodsId = shoppingOrderItemPo.getGoodsId();
		this.orderId = shoppingOrderItemPo.getOrderId();
		this.actualPrice = shoppingOrderItemPo.getActualPrice();
		this.quantity = shoppingOrderItemPo.getQuantity();
		this.originalPrice = shoppingOrderItemPo.getOriginalPrice();
		this.showImg = shoppingOrderItemPo.getShowImg();
		this.showGoodsName = shoppingOrderItemPo.getShowGoodsName();
		this.showOption = shoppingOrderItemPo.getShowOption();
		this.itemTotalFee = shoppingOrderItemPo.getItemTotalFee();
		this.status = shoppingOrderItemPo.getStatus();
		this.integralDeductible = shoppingOrderItemPo.getIntegralDeductible();
		this.integralPercent = shoppingOrderItemPo.getIntegralPercent();
	}
	public Integer getShoppingOrderItemId() {
		return shoppingOrderItemId;
	}
	public void setShoppingOrderItemId(Integer shoppingOrderItemId) {
		this.shoppingOrderItemId = shoppingOrderItemId;
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getShowImg() {
		return showImg;
	}
	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}
	public String getShowGoodsName() {
		return showGoodsName;
	}
	public void setShowGoodsName(String showGoodsName) {
		this.showGoodsName = showGoodsName;
	}
	public String getShowOption() {
		return showOption;
	}
	public void setShowOption(String showOption) {
		this.showOption = showOption;
	}
	public Double getItemTotalFee() {
		return itemTotalFee;
	}
	public void setItemTotalFee(Double itemTotalFee) {
		this.itemTotalFee = itemTotalFee;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public Integer getIntegralDeductible() {
		return integralDeductible;
	}
	public void setIntegralDeductible(Integer integralDeductible) {
		this.integralDeductible = integralDeductible;
	}
	public Double getIntegralPercent() {
		return integralPercent;
	}
	public void setIntegralPercent(Double integralPercent) {
		this.integralPercent = integralPercent;
	}
	public Integer getRebate() {
		return rebate;
	}
	public void setRebate(Integer rebate) {
		this.rebate = rebate;
	}
	
	
}
