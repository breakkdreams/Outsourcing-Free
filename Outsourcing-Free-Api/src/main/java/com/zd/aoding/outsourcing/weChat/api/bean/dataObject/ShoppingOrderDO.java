package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "订单")
public class ShoppingOrderDO extends DOBase {
	/*** 正常流程 ***/
    /**
     * @Fields Unable 无法付款的时间
     */
    /*public final static Long UnableToPayTime = 7200000L;*/
    public final static Long UnableToPayTime = 60000L;
    /**
     * @Fields isPay_yes 可以付款
     */
    public final static Integer isPay_yes = 0;
    /**
     * @Fields isPay_yes 不可以付款
     */
    public final static Integer isPay_no = 1;
	/**
	 * @Fields process_SHOPCART 购物流程: 加入购物车
	 */
	public final static Integer process_Default = 0;
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
	/**
	 * @Fields process_returnGoods 退货
	 */
	public final static Integer process_returnGoods = -1;
	/**** 订单状态（>0退单） ****/
	public final static Integer status_Default = 0;
//	/**
//	 * @Fields status_Order_tuidan退单状态 : 申请买家直接退单
//	 */
//	public final static Integer status_Tuidan = 1;
//	/**
//	 * @Fields status_Order_apply_tuidan 退单状态:买家申退款
//	 */
//	public final static Integer status_RefundMoney = 2;
//	/**
//	 * @Fields status_agree_applyTuidan 退单状态 : 商家同意退款
//	 */
//	public final static Integer status_Agree_RefundMoney = 3;
//	/**
//	 * @Fields status_refuse_applyTuidan 退单状态 : 商家拒绝退款
//	 */
//	public final static Integer status_Refuse_RefundMoney = 4;
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
//	/**
//	 * @Fields status_ReSentGoods_refundMoney 退单状态 :商家收货确认退款
//	 */
//	public final static Integer status_ReSentGoods_agree_refundMoney = 10;
//	/**
//	 * @Fields status_ReSentGoods_refundMoney 退单状态 :商家收货拒绝退款
//	 */
//	public final static Integer status_ReSentGoods_refuse_refundMoney = 11;
	/**
	 * /*** 支付类型
	 ***/
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
	 * @Fields orderType_score 积分订单
	 */
	public static final Integer orderType_score = 1;
	/**
	 * @Fields orderType_bonus 奖金订单
	 */
	public static final Integer orderType_bonus = 2;
	/** 购买id orderId **/
	private Integer id;
	/** 正常流程（0：加入购物车,1：下单，2已付款，3已发货，4确认收货，5评价完成，-1申请售后） process **/
	private Integer process;
	/**
	 * 不同流程下的状态（默认0）1：申请退货 2： 同意退货，3用户发货，4：商家确认收货退款 -1：拒绝退货，
	 **/
	private Integer status;
	/** appCode **/
	private String appCode;
	/** dealerId **/
	private Integer dealerId;
	/** 购买账号id bought_Account_Id **/
	private Integer boughtUserId;
	/** 买家地址id bought_Address_Id **/
	private Integer boughtAddressId;
	/** shopping_order_book_id **/
	private Integer shoppingOrderBookId;
	/** 支付方式(1支付宝（ZFB）2微信（WX）3商城币（cash） pay_Type **/
	private Integer payType;
	/** 留言 message **/
	private String message;
	/** 总价 total_Fee **/
	private Double totalFee;
	/** 总价 total_Fee **/
	private Integer goodsNum;
	/** 快递公司 express_Name **/
	private String expressName;
	/** 快递单号 express_Num **/
	private String expressNum;
	/** 是否完成交易, 0未完成,1完成 is_Finish **/
	private Integer isFinish;
	/** orderTs order_Ts **/
	private Long orderTs;
	/** 付款时间戳 pay_Ts **/
	private Long payTs;
	/** 发货时间戳 send_Ts **/
	private Long sendTs;
	/** 确认收货时间戳 take_Ts **/
	private Long takeTs;
	/** 评论时间戳 discuss_Ts **/
	private Long discussTs;
	/** 申请退单时间戳 refund_Order_Ts **/
	private Long refundOrderTs;
	/** 申请退款时间戳 refund_Money_Ts **/
	private Long refundMoneyTs;
	/** 商户同意退款时间戳 refuse_Refund_Money_Ts **/
	private Long refuseRefundMoneyTs;
	/** 申请退款时间戳 refund_Goods_Ts **/
	private Long refundGoodsTs;
	/** 商户同意退款时间戳 refuse_Refund_Goods_Ts **/
	private Long refuseRefundGoodsTs;
	/** 商户不同意退单时间戳 agree_Refund_Goods_Ts **/
	private Long agreeRefundGoodsTs;
	/** 商户不同意退款时间戳 noBack_Ts **/
	private Long nobackTs;
	/** 买家退货发货时间戳 reSend_Ts **/
	private Long resendTs;
	/** 是否完成交易, 0未删除,1删除 **/
	private Integer isShow;
	/** 订单编号 **/
	private Long orderNum;
	/** 订单类型 1 积分订单 2 奖金订单 **/
	private Integer orderType;
	/** 下单时未付款自动取消订单时间戳 发货时自动收货时间戳 确认收货后自动好评时间戳 **/
    private Long automaticTs;
    /** 系统自动收货标示 0否 1是 **/
    private Integer autoShouhuo;
    /** 系统自动好评标示 0否 1是 **/
    private Integer autoHaoping;
    /** 是否可以付款 0可以 1不可以 **/
    private Integer isPay;
    /** 修改的订单地址（买家填错地址时修改用） **/
    private String updateAddress;
    /** 快递公司名称 展示用 **/
    private String expressNameView;
    /** 积分总价 **/
    private Integer scoreTotal;
    /** 积分兑换比例 **/
    private Double proportion;
    @ApiModelProperty("实际支付价格")
	private Double realTotalFee;
    
    @ApiModelProperty("收货人")
    private String updateConsignee;
    @ApiModelProperty("收货号码")
    private String updatePhone;

	public ShoppingOrderDO(ShoppingCartDO cart, String message, Integer boughtAddressId, Integer dealerId) {
		this.process = process_XiaDan;
		this.status = status_Default;
		this.boughtUserId = cart.getBoughtUserId();
		this.appCode = cart.getAppCode();
		this.boughtAddressId = boughtAddressId;
		this.message = message;
		this.totalFee = 0D;
		this.isFinish = 0;
		this.isShow = 0;
		this.orderTs = System.currentTimeMillis();
		this.dealerId = dealerId;
		this.orderType = cart.getType();
	}

	public ShoppingOrderDO(String appCode, Integer boughtUserId, String message, Integer boughtAddressId, 
			Integer dealerId, Integer orderType) {
		this.process = process_XiaDan;
		this.status = status_Default;
		this.appCode = appCode;
		this.boughtUserId = boughtUserId;
		this.boughtAddressId = boughtAddressId;
		this.message = message;
		this.totalFee = 0D;
		this.isFinish = 0;
		this.isShow = 0;
		this.orderTs = System.currentTimeMillis();
		this.dealerId = dealerId;
		this.orderType = orderType;
	}

	public ShoppingOrderDO() {
		super();
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	/** 购买id orderId **/
	public Integer getId() {
		return id;
	}

	/** 购买id orderId **/
	public void setId(Integer id) {
		this.id = id;
	}

	/** 正常流程（0：加入购物车,1：下单，2已付款，3已发货，4确认收货，5评价完成） process **/
	public Integer getProcess() {
		return process;
	}

	/** 正常流程（0：加入购物车,1：下单，2已付款，3已发货，4确认收货，5评价完成） process **/
	public void setProcess(Integer process) {
		this.process = process;
	}

	/**
	 * 不同流程下的状态（默认0）1：取消订单2：申请退款，3商家同意退款，4：商家不同意退款
	 * 5：申请退货，6：商家同意退货，7：商家不同意退货，8：买家退货发货，9：商家确认收货并退款 status
	 **/
	public Integer getStatus() {
		return status;
	}

	/**
	 * 不同流程下的状态（默认0）1：取消订单2：申请退款，3商家同意退款，4：商家不同意退款
	 * 5：申请退货，6：商家同意退货，7：商家不同意退货，8：买家退货发货，9：商家确认收货并退款 status
	 **/
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 购买账号id bought_Account_Id **/
	public Integer getBoughtUserId() {
		return boughtUserId;
	}

	/** 购买账号id bought_Account_Id **/
	public void setBoughtUserId(Integer boughtUserId) {
		this.boughtUserId = boughtUserId;
	}

	/** 买家地址id bought_Address_Id **/
	public Integer getBoughtAddressId() {
		return boughtAddressId;
	}

	/** 买家地址id bought_Address_Id **/
	public void setBoughtAddressId(Integer boughtAddressId) {
		this.boughtAddressId = boughtAddressId;
	}

	/** shopping_order_book_id **/
	public Integer getShoppingOrderBookId() {
		return shoppingOrderBookId;
	}

	/** shopping_order_book_id **/
	public void setShoppingOrderBookId(Integer shoppingOrderBookId) {
		this.shoppingOrderBookId = shoppingOrderBookId;
	}

	/** 支付方式(1支付宝（ZFB）2微信（WX）3商城币（cash） pay_Type **/
	public Integer getPayType() {
		return payType;
	}

	/** 支付方式(1支付宝（ZFB）2微信（WX）3商城币（cash） pay_Type **/
	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/** 留言 message **/
	public String getMessage() {
		return message;
	}

	/** 留言 message **/
	public void setMessage(String message) {
		this.message = message;
	}

	/** 总价 total_Fee **/
	public Double getTotalFee() {
		return totalFee;
	}

	/** 总价 total_Fee **/
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	/** 快递公司 express_Name **/
	public String getExpressName() {
		return expressName;
	}

	/** 快递公司 express_Name **/
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	/** 快递单号 express_Num **/
	public String getExpressNum() {
		return expressNum;
	}

	/** 快递单号 express_Num **/
	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}

	/** 是否完成交易, 0未完成,1完成 is_Finish **/
	public Integer getIsFinish() {
		return isFinish;
	}

	/** 是否完成交易, 0未完成,1完成 is_Finish **/
	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	/** orderTs order_Ts **/
	public Long getOrderTs() {
		return orderTs;
	}

	/** orderTs order_Ts **/
	public void setOrderTs(Long orderTs) {
		this.orderTs = orderTs;
	}

	/** 付款时间戳 pay_Ts **/
	public Long getPayTs() {
		return payTs;
	}

	/** 付款时间戳 pay_Ts **/
	public void setPayTs(Long payTs) {
		this.payTs = payTs;
	}

	/** 发货时间戳 send_Ts **/
	public Long getSendTs() {
		return sendTs;
	}

	/** 发货时间戳 send_Ts **/
	public void setSendTs(Long sendTs) {
		this.sendTs = sendTs;
	}

	/** 确认收货时间戳 take_Ts **/
	public Long getTakeTs() {
		return takeTs;
	}

	/** 确认收货时间戳 take_Ts **/
	public void setTakeTs(Long takeTs) {
		this.takeTs = takeTs;
	}

	/** 评论时间戳 discuss_Ts **/
	public Long getDiscussTs() {
		return discussTs;
	}

	/** 评论时间戳 discuss_Ts **/
	public void setDiscussTs(Long discussTs) {
		this.discussTs = discussTs;
	}

	/** 申请退单时间戳 refund_Order_Ts **/
	public Long getRefundOrderTs() {
		return refundOrderTs;
	}

	/** 申请退单时间戳 refund_Order_Ts **/
	public void setRefundOrderTs(Long refundOrderTs) {
		this.refundOrderTs = refundOrderTs;
	}

	/** 申请退款时间戳 refund_Money_Ts **/
	public Long getRefundMoneyTs() {
		return refundMoneyTs;
	}

	/** 申请退款时间戳 refund_Money_Ts **/
	public void setRefundMoneyTs(Long refundMoneyTs) {
		this.refundMoneyTs = refundMoneyTs;
	}

	/** 商户同意退款时间戳 refuse_Refund_Money_Ts **/
	public Long getRefuseRefundMoneyTs() {
		return refuseRefundMoneyTs;
	}

	/** 商户同意退款时间戳 refuse_Refund_Money_Ts **/
	public void setRefuseRefundMoneyTs(Long refuseRefundMoneyTs) {
		this.refuseRefundMoneyTs = refuseRefundMoneyTs;
	}

	/** 申请退款时间戳 refund_Goods_Ts **/
	public Long getRefundGoodsTs() {
		return refundGoodsTs;
	}

	/** 申请退款时间戳 refund_Goods_Ts **/
	public void setRefundGoodsTs(Long refundGoodsTs) {
		this.refundGoodsTs = refundGoodsTs;
	}

	/** 商户同意退款时间戳 refuse_Refund_Goods_Ts **/
	public Long getRefuseRefundGoodsTs() {
		return refuseRefundGoodsTs;
	}

	/** 商户同意退款时间戳 refuse_Refund_Goods_Ts **/
	public void setRefuseRefundGoodsTs(Long refuseRefundGoodsTs) {
		this.refuseRefundGoodsTs = refuseRefundGoodsTs;
	}

	/** 商户不同意退单时间戳 agree_Refund_Goods_Ts **/
	public Long getAgreeRefundGoodsTs() {
		return agreeRefundGoodsTs;
	}

	/** 商户不同意退单时间戳 agree_Refund_Goods_Ts **/
	public void setAgreeRefundGoodsTs(Long agreeRefundGoodsTs) {
		this.agreeRefundGoodsTs = agreeRefundGoodsTs;
	}

	/** 商户不同意退款时间戳 noBack_Ts **/
	public Long getNobackTs() {
		return nobackTs;
	}

	/** 商户不同意退款时间戳 noBack_Ts **/
	public void setNobackTs(Long nobackTs) {
		this.nobackTs = nobackTs;
	}

	/** 买家退货发货时间戳 reSend_Ts **/
	public Long getResendTs() {
		return resendTs;
	}

	/** 买家退货发货时间戳 reSend_Ts **/
	public void setResendTs(Long resendTs) {
		this.resendTs = resendTs;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

    public Long getAutomaticTs() {
        return automaticTs;
    }

    public void setAutomaticTs(Long automaticTs) {
        this.automaticTs = automaticTs;
    }

	public Integer getAutoShouhuo() {
		return autoShouhuo;
	}

	public void setAutoShouhuo(Integer autoShouhuo) {
		this.autoShouhuo = autoShouhuo;
	}

	public Integer getAutoHaoping() {
		return autoHaoping;
	}

	public void setAutoHaoping(Integer autoHaoping) {
		this.autoHaoping = autoHaoping;
	}

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

	public String getUpdateAddress() {
		return updateAddress;
	}

	public void setUpdateAddress(String updateAddress) {
		this.updateAddress = updateAddress;
	}

	public String getExpressNameView() {
		return expressNameView;
	}

	public void setExpressNameView(String expressNameView) {
		this.expressNameView = expressNameView;
	}

	public Integer getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(Integer scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	public Double getRealTotalFee() {
		return realTotalFee;
	}

	public void setRealTotalFee(Double realTotalFee) {
		this.realTotalFee = realTotalFee;
	}

	public String getUpdateConsignee() {
		return updateConsignee;
	}

	public void setUpdateConsignee(String updateConsignee) {
		this.updateConsignee = updateConsignee;
	}

	public String getUpdatePhone() {
		return updatePhone;
	}

	public void setUpdatePhone(String updatePhone) {
		this.updatePhone = updatePhone;
	}
}
