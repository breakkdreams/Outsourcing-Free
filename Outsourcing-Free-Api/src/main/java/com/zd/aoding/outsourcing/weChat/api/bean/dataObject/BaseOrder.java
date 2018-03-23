package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

/**
 * @ClassName: BaseOrder
 * @Description: 基础订单类
 * @author zhangj
 * @date 2016年8月16日 上午8:38:05
 */
@ApiModel(value = "", description = "订单基础属性")
public class BaseOrder extends DOBase {
	// /**
	// * @Fields process : 正常流程（默认0）即加入购物车
	// */
	// private Integer process;// 流程（正常流程1：下单，2付款，3发货，4收货，5评价）
	// /**
	// * @Fields status : 不同流程下的状态（默认0） 1时：下单（1取消）;
	// *2：付款（1申请退款，2商家同意退款，3商家不同意退款）;
	// * 3：发货 （1申请退款，2商家同意退货，3商家不同意退货，4同意退款）;
	// * 4：收货（1申请退款，2商家同意退款，3商家不同意退款，4同意退款）;
	// * 5：评价（1申请退款，2商家同意退款，3商家不同意退款）;
	// */
	// private Integer status;// 状态(不同流程下的状态)
	// private Integer orderType;// 订单类型
	// private Integer payType;// 支付方式/1支付宝（ZFB）2微信（）WX3现金（商城币（ftp币））
	/*** 正常流程 ***/
	/**
	 * @Fields process_SHOPCART 购物流程: 加入购物车
	 */
	public final static Integer process_SHOPCART = 0;
	/**
	 * @Fields process_Order_xaidan 购物流程: 下单
	 */
	public final static Integer process_XiaDan = 1;
	/**
	 * @Fields process_Order_fukuan 购物流程: 付款
	 */
	public final static Integer process_FuKuan = 2;
	/**
	 * @Fields process_Order_fahuo 购物流程: 发货
	 */
	public final static Integer process_FaHuo = 3;
	/**
	 * @Fields process_Order_shouhuo 购物流程: 收货
	 */
	public final static Integer process_ShouHuo = 4;
	/**
	 * @Fields process_Order_pingjia 购物流程:评价
	 */
	public final static Integer process_PingJia = 5;
	/**** 订单状态（>0退单） ****/
	public final static Integer status_Default = 0;
	/**
	 * @Fields status_Order_tuidan退单状态 : 申请买家直接退单
	 */
	public final static Integer status_Tuidan = 1;
	/**
	 * @Fields status_Order_apply_tuidan 退单状态:买家申退款
	 */
	public final static Integer status_RefundMoney = 2;
	/**
	 * @Fields status_agree_applyTuidan 退单状态 : 商家同意退款
	 */
	public final static Integer status_Agree_RefundMoney = 3;
	/**
	 * @Fields status_refuse_applyTuidan 退单状态 : 商家拒绝退款
	 */
	public final static Integer status_Refuse_RefundMoney = 4;
	/**
	 * @Fields status_RefundGoods 退单状态 : 买家申请退货
	 */
	public final static Integer status_RefundGoods = 5;
	/**
	 * @Fields status_Agree_RefundGoods 退单状态 : 商家同意退货
	 */
	public final static Integer status_Agree_RefundGoods = 6;
	/**
	 * @Fields status_Refuse_RefundGoods 退单状态 : 商家拒绝退货
	 */
	public final static Integer status_Refuse_RefundGoods = 7;
	/**
	 * @Fields status_ReSentGoods 退单状态 : 买家发货
	 */
	public final static Integer status_ReSentGoods = 8;
	/**
	 * @Fields status_Receive_ReSentGoods 退单状态 : 商家收货
	 */
	public final static Integer status_Receive_ReSentGoods = 9;
	/**
	 * @Fields status_ReSentGoods_refundMoney 退单状态 :商家收货确认退款
	 */
	public final static Integer status_ReSentGoods_agree_refundMoney = 10;
	/**
	 * @Fields status_ReSentGoods_refundMoney 退单状态 :商家收货拒绝退款
	 */
	public final static Integer status_ReSentGoods_refuse_refundMoney = 11;
	/**
	 * @Fields status_O2OOrder_peihuo o2o订单状态 : 商家完成配货
	 */
	public final static Integer status_O2OOrder_peihuo = 1;
	/**
	 * @Fields status_O2OOrder_fahuo o2o订单状态 : 商家完成发货
	 */
	public final static Integer status_O2OOrder_fahuo = 2;
	/**
	 * @Fields status_O2OOrder_songhuo o2o订单状态 : 快递接单送货
	 */
	public final static Integer status_O2OOrder_songhuo = 3;
	/*** 支付类型 ***/
	/**
	 * @Fields payType_ZFB 支付类型： 支付宝支付
	 */
	public static final Integer payType_ZFB = 1;
	/**
	 * @Fields payType_WX 支付类型：微信支付
	 */
	public static final Integer payType_WX = 2;
	/**
	 * @Fields payType_Bonus 支付类型: 奖金支付
	 */
	public static final Integer payType_Bonus = 3;
	/**
	 * @Fields payType_Score 支付类型: 积分支付
	 */
	public static final Integer payType_Score = 4;
	/**
	 * @Fields payType_Score 支付类型: 现金余额
	 */
	public static final Integer payType_money = 5;
	/**
	 * @Fields payType_Cash 支付类型: 商城币支付
	 */
	//public static final Integer payType_BeeCloud = 4;
	/**
	 * @Fields orderOrBackOrder : 订单或申請退貨（0订单，1申请）
	 */
	@ApiModelProperty(value = " 类描述", hidden = true)
	private Integer orderOrBackOrder;
	@ApiModelProperty(value = " 订单号", hidden = true)
	protected String orderNum;// 订单号
	@ApiModelProperty(value = " 单价（默认0）")
	protected Double price;// 单价（默认0）
	@ApiModelProperty(value = " 数量（默认1）")
	protected Integer quantity;// 数量（默认1）
	/**
	 * @fieldName: isFinish
	 * @fieldType: Integer
	 * @Description: 是否完成交易
	 */
	@ApiModelProperty(value = "0默认1完成交易")
	private Integer isFinish;// 0默认1完成交易
	/**
	 * @Fields process : 正常流程（0：加入购物车，1：下单，2付款，3发货，4收货，5评价）
	 */
	@ApiModelProperty("正常流程对于  B2C订单（0：在购物车中，1：已下单未付款，2已付款，3已发货待收货，4已收货，5评价完成）"
			+ "o2o订单（1：下单未付款，2已付款，3发货（status==1商家完成配货，2商家完成发货，3快递接单送货），4买家验货（status==1收货，2换货，3退货）），5评价完成"
			+ "dbOrder夺宝订单（1：下单未付款，2已付款，3开奖（state==1中奖，2未中奖")
	private Integer process;
	/******** 正常流程时间戳 ***********/
	/**
	 * @Fields orderTs : 下单时间戳
	 */
	private Long orderTs; // 下单时间戳
	/**
	 * @Fields payTs : 付款时间戳
	 */
	private Long payTs; // 付款时间戳
	/**
	 * @Fields sendTs : 发货时间戳
	 */
	private Long sendTs; // 发货时间戳
	/**
	 * @Fields takeTs : 确认收货时间戳
	 */
	private Long takeTs; // 确认收货时间戳
	/**
	 * @Fields discussTs : 评论时间戳
	 */
	private Long discussTs; // 评论时间戳
	/**
	 * @Fields status : 不同流程下的状态（默认0）
	 * @下单 process==1：status==（1：取消订单）;
	 * @付款 process==2：status==（2：申请退款，3商家同意退款，4：商家不同意退款）;
	 * @收货或评价 process==
	 *        4,5：status==（5：申请退货，6：商家同意退货，7：商家不同意退货，8：买家退货发货，9：商家确认收货并退款）;
	 *        申请退货，评价
	 */
	@ApiModelProperty(value = "@Fields status : 不同流程下的状态（默认0） @下单 process==1：status==（1：取消订单）;@付款 process==2：status==（2：申请退款，3商家同意退款，4：商家不同意退款）; @收货或评价 process== 4,5：status==（5：申请退货，6：商家同意退货，7：商家不同意退货，8：买家退货发货，9：商家确认收货并退款）; 申请退货，评价")
	private Integer status;// 状态(不同流程下的状态)
	/********* 申请退款退货时间戳 *********/
	/**
	 * @Fields refundOrderTs : 申请退单时间戳
	 */
	private Long refundOrderTs; // 申请退单时间戳
	/**
	 * @Fields refundMoneyTs : 申请退款时间戳
	 */
	private Long refundMoneyTs; // 申请退款时间戳
	/**
	 * @Fields refuseRefundMoneyTs : 商户同意退款时间戳
	 */
	private Long refuseRefundMoneyTs; // 商户同意退款时间戳
	/**
	 * @Fields agreeRefundMoneyTs : 商家同意退款时间戳
	 */
	private Long agreeRefundMoneyTs; // 商户不同意退单时间戳
	/**
	 * @Fields refundGoodsTs : 申请退货时间戳
	 */
	private Long refundGoodsTs; // 申请退款时间戳
	/**
	 * @Fields refuseRefundGoodsTs : 商户同意退货时间戳
	 */
	private Long refuseRefundGoodsTs; // 商户同意退款时间戳
	/**
	 * @Fields agreeRefundGoodsTs : 商家同意退货时间戳
	 */
	private Long agreeRefundGoodsTs; // 商户不同意退单时间戳
	/**
	 * @fields：商户不同意退款时间戳
	 */
	private Long noBackTs; // 商户不同意退款时间戳
	/**
	 * @Fields payType : 支付方式(1支付宝（ZFB）2微信（WX）3商城币（cash）
	 */
	private Integer payType;
	/**
	 * @Fields deliveryTs : 买家退货发货时间戳
	 */
	private Long reSendTs; // 发货时间戳

	public Integer getOrderOrBackOrder() {
		return orderOrBackOrder;
	}

	public void setOrderOrBackOrder(Integer orderOrBackOrder) {
		this.orderOrBackOrder = orderOrBackOrder;
	}

	public Long getOrderTs() {
		return orderTs;
	}

	public void setOrderTs(Long orderTs) {
		this.orderTs = orderTs;
	}

	public Long getPayTs() {
		return payTs;
	}

	public void setPayTs(Long payTs) {
		this.payTs = payTs;
	}

	public Long getSendTs() {
		return sendTs;
	}

	public void setSendTs(Long sendTs) {
		this.sendTs = sendTs;
	}

	/**
	 * @Fields takeTs : 确认收货时间戳
	 */
	public Long getTakeTs() {
		return takeTs;
	}

	public void setTakeTs(Long takeTs) {
		this.takeTs = takeTs;
	}

	public Long getDiscussTs() {
		return discussTs;
	}

	public void setDiscussTs(Long discussTs) {
		this.discussTs = discussTs;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRefundOrderTs() {
		return refundOrderTs;
	}

	public void setRefundOrderTs(Long refundOrderTs) {
		this.refundOrderTs = refundOrderTs;
	}

	public Long getRefundMoneyTs() {
		return refundMoneyTs;
	}

	public void setRefundMoneyTs(Long refundMoneyTs) {
		this.refundMoneyTs = refundMoneyTs;
	}

	public Long getRefuseRefundMoneyTs() {
		return refuseRefundMoneyTs;
	}

	public void setRefuseRefundMoneyTs(Long refuseRefundMoneyTs) {
		this.refuseRefundMoneyTs = refuseRefundMoneyTs;
	}

	public Long getAgreeRefundMoneyTs() {
		return agreeRefundMoneyTs;
	}

	public void setAgreeRefundMoneyTs(Long agreeRefundMoneyTs) {
		this.agreeRefundMoneyTs = agreeRefundMoneyTs;
	}

	public Long getRefundGoodsTs() {
		return refundGoodsTs;
	}

	public void setRefundGoodsTs(Long refundGoodsTs) {
		this.refundGoodsTs = refundGoodsTs;
	}

	public Long getRefuseRefundGoodsTs() {
		return refuseRefundGoodsTs;
	}

	public void setRefuseRefundGoodsTs(Long refuseRefundGoodsTs) {
		this.refuseRefundGoodsTs = refuseRefundGoodsTs;
	}

	public Long getAgreeRefundGoodsTs() {
		return agreeRefundGoodsTs;
	}

	public void setAgreeRefundGoodsTs(Long agreeRefundGoodsTs) {
		this.agreeRefundGoodsTs = agreeRefundGoodsTs;
	}

	public Long getNoBackTs() {
		return noBackTs;
	}

	public void setNoBackTs(Long noBackTs) {
		this.noBackTs = noBackTs;
	}

	public Long getReSendTs() {
		return reSendTs;
	}

	public void setReSendTs(Long reSendTs) {
		this.reSendTs = reSendTs;
	}

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "BaseOrder [orderOrBackOrder=" + orderOrBackOrder + ", price=" + price + ", quantity=" + quantity
				+ ", isFinish=" + isFinish + ", process=" + process + ", orderTs=" + orderTs + ", payTs=" + payTs
				+ ", sendTs=" + sendTs + ", takeTs=" + takeTs + ", discussTs=" + discussTs + ", status=" + status
				+ ", refundOrderTs=" + refundOrderTs + ", refundMoneyTs=" + refundMoneyTs + ", refuseRefundMoneyTs="
				+ refuseRefundMoneyTs + ", agreeRefundMoneyTs=" + agreeRefundMoneyTs + ", refundGoodsTs="
				+ refundGoodsTs + ", refuseRefundGoodsTs=" + refuseRefundGoodsTs + ", agreeRefundGoodsTs="
				+ agreeRefundGoodsTs + ", noBackTs=" + noBackTs + ", payType=" + payType + ", reSendTs=" + reSendTs
				+ ", toString()=" + super.toString() + "]";
	}
}
