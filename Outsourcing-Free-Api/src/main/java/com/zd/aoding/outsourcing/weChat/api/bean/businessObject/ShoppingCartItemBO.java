package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartItemDO;

@ApiModel(value = "", description = "购物车明细")
public class ShoppingCartItemBO {
	@ApiModelProperty("id")
	private Integer shoppingCartItemId;
	private Integer cartId;
	@ApiModelProperty("配置组合id")
	private Integer optionId;
	@ApiModelProperty("产品id")
	private Integer goodsId;
	@ApiModelProperty("shopping_cart_Id")
	private Integer shoppingCartId;
	@ApiModelProperty("实际单价")
	private Double actualPrice;
	@ApiModelProperty("积分单价")
	private Double scorePrice;
	@ApiModelProperty("奖金单价")
	private Double bonusPrice;
	@ApiModelProperty("实际售价")
	private Double marketPrice;
	@ApiModelProperty("勾选数量")
	private Integer quantity;
	@ApiModelProperty("实际价格 original_price")
	private Double originalPrice;
	@ApiModelProperty("展示小图标")
	private String showImg;
	@ApiModelProperty("展示产品名称")
	private String showGoodsName;
	@ApiModelProperty("展示配置名称")
	private String showOption;
	@ApiModelProperty("产品总价小计")
	private Double itemTotalFee;
	@ApiModelProperty("产品类型")
	private Integer goodsType;
	@ApiModelProperty("产品库存")
	private Integer stock;
	@ApiModelProperty("商家名称")
	private String dealerName;
	@ApiModelProperty("code")
	private String iosCode;
	@ApiModelProperty("1上架  2不可购买")
	private Integer goodsStatus;
	@ApiModelProperty("积分抵扣价")
	private Integer integralDeductible;
	@ApiModelProperty("积分抵扣百分比")
	private Double integralPercent;
	@ApiModelProperty("返利")
	private Integer rebate;
	
	public ShoppingCartItemBO() {
		super();
	}
	public ShoppingCartItemBO(ShoppingCartItemDO shoppingCartItemPo) {
		super();
		if(shoppingCartItemPo.getId() != null){
			this.shoppingCartItemId = shoppingCartItemPo.getId();
		}else{
			this.shoppingCartItemId = 0;
		}
		this.optionId = shoppingCartItemPo.getOptionId();
		this.goodsId = shoppingCartItemPo.getGoodsId();
		this.shoppingCartId = shoppingCartItemPo.getShoppingCartId();
		this.actualPrice = shoppingCartItemPo.getActualPrice();
		this.quantity = shoppingCartItemPo.getQuantity();
		this.originalPrice = shoppingCartItemPo.getOriginalPrice();
		this.showImg = shoppingCartItemPo.getShowImg();
		this.showGoodsName = shoppingCartItemPo.getShowGoodsName();
		this.showOption = shoppingCartItemPo.getShowOption();
		this.itemTotalFee = shoppingCartItemPo.getItemTotalFee();
		this.iosCode = shoppingCartItemPo.getIosCode();
		this.cartId = shoppingCartItemPo.getShoppingCartId();
	}
	public Integer getShoppingCartItemId() {
		return shoppingCartItemId;
	}
	public void setShoppingCartItemId(Integer shoppingCartItemId) {
		this.shoppingCartItemId = shoppingCartItemId;
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
	public Integer getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
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
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getScorePrice() {
		return scorePrice;
	}
	public void setScorePrice(Double scorePrice) {
		this.scorePrice = scorePrice;
	}
	public Double getBonusPrice() {
		return bonusPrice;
	}
	public void setBonusPrice(Double bonusPrice) {
		this.bonusPrice = bonusPrice;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getIosCode() {
		return iosCode;
	}
	public void setIosCode(String iosCode) {
		this.iosCode = iosCode;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
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
