package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartDO;

@ApiModel(value = "", description = "购物车")
public class ShoppingCartBO {
	/**
	 * @fieldName: TO_PAGING
	 * @fieldType: String
	 * @Description: 分页
	 */
	public static final String TO_PAGING = "paging";
	/**
	 * @fieldName: TO_ALL
	 * @fieldType: String
	 * @Description: 全部
	 */
	public static final String TO_ALL = "all";
	private Integer shoppingCartId;
	@ApiModelProperty("产品数量小计")
	private String appCode;
	@ApiModelProperty("产品数量小计")
	private Integer dealerId;
	@ApiModelProperty("产品数量小计")
	private Integer shopGoodsNum;
	@ApiModelProperty("产品数量小计")
	private Integer boughtUserId;
	@ApiModelProperty("商家名称")
	private String dealerName;
	@ApiModelProperty("商家手机号")
	private String dealerPhone;
	@ApiModelProperty(value="",notes="购车中一个或几多个产品信息")
	private List<ShoppingCartItemBO> shoppingCartItemVoList;
	@ApiModelProperty(value="type")
	private Integer type;
	
	@ApiModelProperty("积分抵扣总价")
	private Integer integralDeductibleCount;

	public ShoppingCartBO(ShoppingCartDO shoppingCartPo, String toWhat) {
		super();
		switch (toWhat) {
			case TO_PAGING:
				toPaging(shoppingCartPo);
				break;
			case TO_ALL:
				toAll(shoppingCartPo);
				break;
			default:
				toPaging(shoppingCartPo);
		}
	}
	private void toPaging(ShoppingCartDO shoppingCartPo) {
		if(shoppingCartPo.getId() != null){
			this.shoppingCartId = shoppingCartPo.getId();
		}else{
			this.shoppingCartId = 0;
		}
		this.appCode = shoppingCartPo.getAppCode();
		this.dealerId = shoppingCartPo.getDealerId();
		this.shopGoodsNum = shoppingCartPo.getShopGoodsNum();
		this.boughtUserId = shoppingCartPo.getBoughtUserId();
		this.type = shoppingCartPo.getType();
	}
	private void toAll(ShoppingCartDO shoppingCartPo) {
		if(shoppingCartPo.getId() != null){
			this.shoppingCartId = shoppingCartPo.getId();
		}else{
			this.shoppingCartId = 0;
		}
		this.appCode = shoppingCartPo.getAppCode();
		this.dealerId = shoppingCartPo.getDealerId();
		this.shopGoodsNum = shoppingCartPo.getShopGoodsNum();
		this.boughtUserId = shoppingCartPo.getBoughtUserId();
		this.type = shoppingCartPo.getType();
	}
	
	public Integer getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public Integer getDealerId() {
		return dealerId;
	}
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	public Integer getShopGoodsNum() {
		return shopGoodsNum;
	}
	public void setShopGoodsNum(Integer shopGoodsNum) {
		this.shopGoodsNum = shopGoodsNum;
	}
	public Integer getBoughtUserId() {
		return boughtUserId;
	}
	public void setBoughtUserId(Integer boughtUserId) {
		this.boughtUserId = boughtUserId;
	}
	public List<ShoppingCartItemBO> getShoppingCartItemVoList() {
		return shoppingCartItemVoList;
	}
	public void setShoppingCartItemVoList(
			List<ShoppingCartItemBO> shoppingCartItemVoList) {
		this.shoppingCartItemVoList = shoppingCartItemVoList;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDealerPhone() {
		return dealerPhone;
	}
	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}
	public Integer getIntegralDeductibleCount() {
		return integralDeductibleCount;
	}
	public void setIntegralDeductibleCount(Integer integralDeductibleCount) {
		this.integralDeductibleCount = integralDeductibleCount;
	}
}
