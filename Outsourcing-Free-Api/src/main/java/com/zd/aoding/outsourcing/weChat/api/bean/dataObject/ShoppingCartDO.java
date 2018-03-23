package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "购物车")
public class ShoppingCartDO extends DOBase {
    public static final Integer type_score = 1;
    public static final Integer type_bonus = 2;
	/** 购物车id id **/
	private Integer id;
	/** appCode **/
	private String appCode;
	/** dealerId 0为自营 **/
	private Integer dealerId;
	/** 添加产品数量 shop_goods_num **/
	private Integer shopGoodsNum;
	/** 购买账号id bought_AccountId **/
	private Integer boughtUserId;
	/** 该商家购买总价(保留） total_Fee **/
	private Double totalFee;
	/** 状态（保留） status **/
	private Integer status;
	/** 产品类型 1积分 2奖金  **/
	private Integer type;
	

	public ShoppingCartDO() {
		super();
	}

	public ShoppingCartDO(String appCode, Integer boughtUserId, Integer dealerId, Integer type) {
		super();
		this.shopGoodsNum = 1;
		this.appCode = appCode;
		this.boughtUserId = boughtUserId;
		this.dealerId = dealerId;
		this.type = type;
	}

	/** 购物车id id **/
	public Integer getId() {
		return id;
	}

	/** 购物车id id **/
	public void setId(Integer id) {
		this.id = id;
	}

	/** 添加产品数量 shop_goods_num **/
	public Integer getShopGoodsNum() {
		return shopGoodsNum;
	}

	/** 添加产品数量 shop_goods_num **/
	public void setShopGoodsNum(Integer shopGoodsNum) {
		this.shopGoodsNum = shopGoodsNum;
	}

	/** 状态（保留） status **/
	public Integer getStatus() {
		return status;
	}

	/** 状态（保留） status **/
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 该商家购买总价(保留） total_Fee **/
	public Double getTotalFee() {
		return totalFee;
	}

	/** 该商家购买总价(保留） total_Fee **/
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
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

	public Integer getBoughtUserId() {
		return boughtUserId;
	}

	public void setBoughtUserId(Integer boughtUserId) {
		this.boughtUserId = boughtUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ShoppingCartDO [id=" + id + ", appCode=" + appCode
				+ ", dealerId=" + dealerId + ", shopGoodsNum=" + shopGoodsNum
				+ ", boughtUserId=" + boughtUserId + ", totalFee=" + totalFee
				+ ", status=" + status + ", type=" + type
				+ ", toString()=" + super.toString() + "]";
	}

}
