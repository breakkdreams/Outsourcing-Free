package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.util.Date;

import com.zd.aoding.base.DO.base.DOBase;

/**
 * 支付宝交易订单
 * 
 * @author and
 */
public class ZFBOrderDO extends DOBase {
	private Integer zfbId;
	private String notify_time; // 通知时间 Date 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss。 不可空
								// 2013/8/22 14:45
	private String notify_type; // 通知类型 String 通知的类型。 不可空 trade_status_sync
	private String notify_id; // 通知校验ID String 通知校验ID。 不可空
								// 64ce1b6ab92d00ede0ee56ade98fdf2f4c
	private String sign_type; // 签名方式 String 固定取值为RSA。 不可空 RSA
	private String sign; // 签名 String 请参见签名验证。 不可空
							// lBBK%2F0w5LOajrMrji7DUgEqNjIhQbidR13GovA5r3TgIbNqv231yC1NksLdw%2Ba3JnfHXoXuet6XNNHtn7VE%2BeCoRO1O%2BR1KugLrQEZMtG5jmJI
	private String out_trade_no;// 商户网站唯一订单号 String(64)
								// 对应商户网站的订单系统中的唯一订单号，非支付宝交易号。需保证在商户网站中的唯一性。是请求时对应的参数，原样返回。
								// 可空 8.22152E+13
	private String subject; // 商品名称 String(128)
							// 商品的标题/交易标题/订单标题/订单关键字等。它在支付宝的交易明细中排在第一列，对于财务对账尤为重要。是请求时对应的参数，原样通知回来。
							// 可空 测试
	private String payment_type;// 支付类型 String(4) 支付类型。默认值为：1（商品购买）。 可空 1
	private String trade_no; // 支付宝交易号 String(64) 该交易在支付宝系统中的交易流水号。最短16位，最长64位。
								// 不可空 2.01308E+15
	private String trade_status;// 交易状态 String 交易状态，取值范围请参见“交易状态”。 不可空
								// TRADE_SUCCESS
	private String seller_id; // 卖家支付宝用户号 String(30)
								// 卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。 不可空
								// 2.0885E+15
	private String seller_email;// 卖家支付宝账号 String(100) 卖家支付宝账号，可以是email和手机号码。
								// 不可空 xxx@alipay.com
	private String buyer_id; // 买家支付宝用户号 String(30)
								// 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。 不可空
								// 2.0886E+15
	private String buyer_email; // 买家支付宝账号 String(100) 买家支付宝账号，可以是Email或手机号码。
								// 不可空 dlwdgl@gmail.com
	private Double total_fee; // 交易金额 Number 该笔订单的总金额。请求时对应的参数，原样通知回来。 不可空 1
	private Integer quantity; // 购买数量 Number 购买数量，固定取值为1（请求时使用的是total_fee）。 可空 1
	private Double price; // 商品单价 Number price等于total_fee（请求时使用的是total_fee）。 可空
							// 1
	private String body; // 商品描述 String(512) 该笔订单的备注、描述、明细等。对应请求时的body参数，原样通知回来。
							// 可空 测试测试
	private String gmt_create; // 交易创建时间 Date 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss。
								// 可空 2013/8/22 14:45
	private String gmt_payment; // 交易付款时间 Date 该笔交易的买家付款时间。格式为yyyy-MM-dd
								// HH:mm:ss。 可空 2013/8/22 14:45
	private String is_total_fee_adjust;// 是否调整总价 String(1) 该交易是否调整过价格。 可空 N
	private String use_coupon; // 是否使用红包买家 String(1) 是否在交易过程中使用了红包。 可空 N
	private String discount; // 折扣 String
								// 支付宝系统会把discount的值加到交易金额上，如果有折扣，本参数为负数，单位为元。
								// 可空 0
	private String refund_status;// 退款状态 String 取值范围请参见“退款状态”。 可空 REFUND_SUCCESS
	private String gmt_refund; // 退款时间 Date 卖家退款的时间，退款通知时会发送。格式为yyyy-MM-dd
								// HH:mm:ss。 可空 2008/10/29 19:38
	private Integer userId; // 系统 用户id
	private String orderType;// 系统 订单类型
	private String orderId;// 系统 订单id

	public ZFBOrderDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ZFBOrderDO(String notify_time, String notify_type, String notify_id, String sign_type, String sign,
			String out_trade_no, String subject, String payment_type, String trade_no, String trade_status,
			String seller_id, String seller_email, String buyer_id, String buyer_email, Double total_fee,
			Integer quantity, Double price, String body, String gmt_create, String gmt_payment,
			String is_total_fee_adjust, String use_coupon, String discount, String refund_status, String gmt_refund) {
		super();
		this.notify_time = notify_time;
		this.notify_type = notify_type;
		this.notify_id = notify_id;
		this.sign_type = sign_type;
		this.sign = sign;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.payment_type = payment_type;
		this.trade_no = trade_no;
		this.trade_status = trade_status;
		this.seller_id = seller_id;
		this.seller_email = seller_email;
		this.buyer_id = buyer_id;
		this.buyer_email = buyer_email;
		this.total_fee = total_fee;
		this.quantity = quantity;
		this.price = price;
		this.body = body;
		this.gmt_create = gmt_create;
		this.gmt_payment = gmt_payment;
		this.is_total_fee_adjust = is_total_fee_adjust;
		this.use_coupon = use_coupon;
		this.discount = discount;
		this.refund_status = refund_status;
		this.gmt_refund = gmt_refund;
		this.setCreateTime(new Date());
	}

	public Integer getZfbId() {
		return zfbId;
	}

	public void setZfbId(Integer zfbId) {
		this.zfbId = zfbId;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public Double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}

	public String getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}

	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}

	public String getUse_coupon() {
		return use_coupon;
	}

	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}

	public String getGmt_refund() {
		return gmt_refund;
	}

	public void setGmt_refund(String gmt_refund) {
		this.gmt_refund = gmt_refund;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ZFBOrder [zfbId=" + zfbId + ", notify_time=" + notify_time + ", notify_type=" + notify_type
				+ ", notify_id=" + notify_id + ", sign_type=" + sign_type + ", sign=" + sign + ", out_trade_no="
				+ out_trade_no + ", subject=" + subject + ", payment_type=" + payment_type + ", trade_no=" + trade_no
				+ ", trade_status=" + trade_status + ", seller_id=" + seller_id + ", seller_email=" + seller_email
				+ ", buyer_id=" + buyer_id + ", buyer_email=" + buyer_email + ", total_fee=" + total_fee + ", quantity="
				+ quantity + ", price=" + price + ", body=" + body + ", gmt_create=" + gmt_create + ", gmt_payment="
				+ gmt_payment + ", is_total_fee_adjust=" + is_total_fee_adjust + ", use_coupon=" + use_coupon
				+ ", discount=" + discount + ", refund_status=" + refund_status + ", gmt_refund=" + gmt_refund
				+ ", userId=" + userId + ", orderType=" + orderType + ", orderId=" + orderId + ", getIsDeleted()="
				+ getDeleted() + ", getCreateDate()=" + getCreateTime() + ", getUpdateTs()=" + getUpdateTime() + "]";
	}
}
