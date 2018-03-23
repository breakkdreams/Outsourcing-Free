package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value ="",description="积分订单产品明细")
public class ShoppingOrderItemDO extends DOBase {
    
    /**
     * @Fields status_RefundGoods 退单状态 : 买家申请退货
     */
    public final static Integer status_RefundGoods = 1;
    /**
     * @Fields status_Agree_RefundGoods 退单状态 : 商家同意退货
     */
    public final static Integer status_Agree_RefundGoods = 2;
    /**
     * @Fields status_Refuse_RefundGoods 退单状态 : 商家拒绝退货
     */
    public final static Integer status_Refuse_RefundGoods = -1;
    /**
     * @Fields status_ReSentGoods 退单状态 : 买家发货
     */
    public final static Integer status_ReSentGoods = 3;
    /**
     * @Fields status_Receive_ReSentGoods 退单状态 : 商家收货
     */
    public final static Integer status_Receive_ReSentGoods = 4;
    
	/** 订单明细 id **/
	private Integer id;
	/** 配置id option_Id **/
	@ApiModelProperty("产品配置组合id")
	private Integer optionId;
	/** 产品id goods_Id **/
	@ApiModelProperty("产品id")
	private Integer goodsId;
	/** 订单id order_Id **/
	@ApiModelProperty("订单id")
	private Integer orderId;
	/**
	 * 实际单价 actual_price
	 **/
	@ApiModelProperty("实际单价")
	private Double actualPrice;
	/** 数量 quantity **/
	@ApiModelProperty("购买数量")
	private Integer quantity;
	/** 实际价格 original_price **/
	@ApiModelProperty("原价")
	private Double originalPrice;
	/** 展示图片路径 show_img **/
	@ApiModelProperty("展示小图片")
	private String showImg;
	/** show_goods_Name **/
	@ApiModelProperty("展示产品名称")
	private String showGoodsName;
	/** show_option **/
	@ApiModelProperty("配置组合名称")
	private String showOption;
	@ApiModelProperty("产品总价小计")
	private Double itemTotalFee;
	/**
	 * 不同流程下的状态（默认0）1：取消订单2：申请退款，3商家同意退款，4：商家不同意退款 5：申请退货，6：商家同意退货，7：商家不同意退货，8：买家退货发货，9：商家确认收货并退款
	 * status
	 **/
	private Integer status;
	
	//自定义sql字段
	private String quant;//销量
	private String time;//时间
	private String title;//产品名
	private String goods_Id;//产品id
	
	@ApiModelProperty("积分抵扣价")
	private Integer integralDeductible;
	@ApiModelProperty("积分抵扣百分比")
	private Double integralPercent;
	

	public ShoppingOrderItemDO(Integer orderId, ShoppingCartItemDO shoppingCartItemPo) {
		super();
		this.orderId = orderId;
		this.optionId = shoppingCartItemPo.getOptionId();
		this.goodsId = shoppingCartItemPo.getGoodsId();
		this.actualPrice = shoppingCartItemPo.getActualPrice();
		this.quantity = shoppingCartItemPo.getQuantity();
		this.originalPrice = shoppingCartItemPo.getOriginalPrice();
		this.showImg = shoppingCartItemPo.getShowImg();
		this.showGoodsName = shoppingCartItemPo.getShowGoodsName();
		this.showOption = shoppingCartItemPo.getShowOption();
		this.status = 0;
	}
	
	
	
	
	public ShoppingOrderItemDO(Integer orderId,Integer optionId, Integer goodsId,  Double actualPrice, Integer quantity, Double originalPrice,
			String showImg, String showGoodsName, String showOption) {
		super();
		this.optionId = optionId;
		this.goodsId = goodsId;
		this.orderId = orderId;
		this.actualPrice = actualPrice;
		this.quantity = quantity;
		this.originalPrice = originalPrice;
		this.showImg = showImg;
		this.showGoodsName = showGoodsName;
		this.showOption = showOption;
		this.itemTotalFee = quantity*actualPrice;
		this.status = 0;
	}




	public ShoppingOrderItemDO() {
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
	/** 订单id order_Id **/
	public Integer getOrderId() {
		return orderId;
	}
	/** 订单id order_Id **/
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getQuant() {
		return quant;
	}
	public void setQuant(String quant) {
		this.quant = quant;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGoods_Id() {
		return goods_Id;
	}
	public void setGoods_Id(String goods_Id) {
		this.goods_Id = goods_Id;
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
	@Override
	public String toString() {
		return "ShoppingOrderItemDO [integralDeductible=" + integralDeductible + ",integralPercent=" + integralPercent + ", id=" + id + ", optionId=" + optionId + ", goodsId=" + goodsId + ", orderId=" + orderId + ", actualPrice="
				+ actualPrice + ", quantity=" + quantity + ", originalPrice=" + originalPrice + ", showImg=" + showImg + ", showGoodsName="
				+ showGoodsName + ", showOption=" + showOption + ", itemTotalFee=" + itemTotalFee + ", status=" + status + ", toString()="
				+ super.toString() + "]";
	}
}
