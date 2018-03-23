package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "购物车产品（购物车产品明细）")
public class ShoppingCartItemDO extends DOBase {
	/** 订单明细 id **/
	private Integer id;
	/** 配置id option_Id **/
	@ApiModelProperty("配置组合id")
	private Integer optionId;
	/** 产品id goods_Id **/
	@ApiModelProperty("产品id")
	private Integer goodsId;
	/** 订单id shopping_cart_Id **/
	private Integer shoppingCartId;
	private Integer userId;
	/**
	 * 实际单价 actual_price
	 **/
	@ApiModelProperty("实际单价")
	private Double actualPrice;
	/** 数量 quantity **/
	@ApiModelProperty("勾选数量")
	private Integer quantity;
	/** 实际价格 original_price **/
	@ApiModelProperty("实际价格")
	private Double originalPrice;
	@ApiModelProperty("真的价格")
	private Double truePrice;
	/** 展示图片路径 show_img **/
	@ApiModelProperty("展示小图标")
	private String showImg;
	/** show_goods_Name **/
	@ApiModelProperty("展示产品名称")
	private String showGoodsName;
	/** show_option **/
	@ApiModelProperty("展示配置名称")
	private String showOption;
	@ApiModelProperty("产品总价小计")
	private Double itemTotalFee;
	private String iosCode;
	/**
	 * 不同流程下的状态（保留） status
	 **/
	private Integer status;

	public ShoppingCartItemDO(Integer optionId, Integer goodsId, Integer shoppingCartId, Double actualPrice, Integer quantity, Double originalPrice,
			String showImg, String showGoodsName, String showOption, Integer userId, String iosCode) {
		super();
		this.optionId = optionId;
		this.goodsId = goodsId;
		this.shoppingCartId = shoppingCartId;
		this.actualPrice = actualPrice;
		this.quantity = quantity;
		this.originalPrice = originalPrice;
		this.showImg = showImg;
		this.showGoodsName = showGoodsName;
		this.showOption = showOption;
		this.userId = userId;
		if(iosCode != null){
			this.iosCode = iosCode;
		}else{
			this.iosCode = "";
		}
	}
	public ShoppingCartItemDO() {
		super();
	}
	/** 订单明细 id **/
	public Integer getId() {
		return id;
	}
	/** 订单明细 id **/
	public void setId(Integer id) {
		this.id = id;
	}
	/** 配置id option_Id **/
	public Integer getOptionId() {
		return optionId;
	}
	/** 配置id option_Id **/
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	/** 产品id goods_Id **/
	public Integer getGoodsId() {
		return goodsId;
	}
	/** 产品id goods_Id **/
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/** 订单id shopping_cart_Id **/
	public Integer getShoppingCartId() {
		return shoppingCartId;
	}
	/** 订单id shopping_cart_Id **/
	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	/**
	 * 实际单价 actual_price
	 **/
	public Double getActualPrice() {
		return actualPrice;
	}
	/**
	 * 实际单价 actual_price
	 **/
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	/** 数量 quantity **/
	public Integer getQuantity() {
		return quantity;
	}
	/** 数量 quantity **/
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/** 实际价格 original_price **/
	public Double getOriginalPrice() {
		return originalPrice;
	}
	/** 实际价格 original_price **/
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	/** 展示图片路径 show_img **/
	public String getShowImg() {
		return showImg;
	}
	/** 展示图片路径 show_img **/
	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}
	/** show_goods_Name **/
	public String getShowGoodsName() {
		return showGoodsName;
	}
	/** show_goods_Name **/
	public void setShowGoodsName(String showGoodsName) {
		this.showGoodsName = showGoodsName;
	}
	/** show_option **/
	public String getShowOption() {
		return showOption;
	}
	/** show_option **/
	public void setShowOption(String showOption) {
		this.showOption = showOption;
	}
	/**
	 * 不同流程下的状态（默认0）1：取消订单2：申请退款，3商家同意退款，4：商家不同意退款 5：申请退货，6：商家同意退货，7：商家不同意退货，8：买家退货发货，9：商家确认收货并退款
	 * status
	 **/
	public Integer getStatus() {
		return status;
	}
	/**
	 * 不同流程下的状态（默认0）1：取消订单2：申请退款，3商家同意退款，4：商家不同意退款 5：申请退货，6：商家同意退货，7：商家不同意退货，8：买家退货发货，9：商家确认收货并退款
	 * status
	 **/
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getItemTotalFee() {
		return itemTotalFee;
	}
	public void setItemTotalFee(Double itemTotalFee) {
		this.itemTotalFee = itemTotalFee;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getIosCode() {
		return iosCode;
	}
	public void setIosCode(String iosCode) {
		this.iosCode = iosCode;
	}
	public Double getTruePrice() {
        return truePrice;
    }
    public void setTruePrice(Double truePrice) {
        this.truePrice = truePrice;
    }
    @Override
    public String toString() {
        return "ShoppingCartItemDO [id=" + id + ", optionId=" + optionId
                + ", goodsId=" + goodsId + ", shoppingCartId=" + shoppingCartId
                + ", userId=" + userId + ", actualPrice=" + actualPrice
                + ", quantity=" + quantity + ", originalPrice=" + originalPrice
                + ", truePrice=" + truePrice + ", showImg=" + showImg
                + ", showGoodsName=" + showGoodsName + ", showOption="
                + showOption + ", itemTotalFee=" + itemTotalFee + ", iosCode="
                + iosCode + ", status=" + status + "]";
    }
}
