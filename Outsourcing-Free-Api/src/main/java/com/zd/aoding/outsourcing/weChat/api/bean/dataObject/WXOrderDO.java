package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.util.Map;

import com.zd.aoding.base.DO.base.DOBase;

/**
 * 微信订单 params 包含数据字段取决于微信返回结构，例如：
 * <xml> <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
 * <attach><![CDATA[支付测试]]></attach> <bank_type><![CDATA[CFT]]></bank_type>
 * <fee_type><![CDATA[CNY]]></fee_type>
 * <is_subscribe><![CDATA[Y]]></is_subscribe>
 * <mch_id><![CDATA[10000100]]></mch_id>
 * <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
 * <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
 * <out_trade_no><![CDATA[1409811653]]></out_trade_no>
 * <result_code><![CDATA[SUCCESS]]></result_code>
 * <return_code><![CDATA[SUCCESS]]></return_code>
 * <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
 * <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
 * <time_end><![CDATA[20140903131540]]></time_end> <total_fee>1</total_fee>
 * <trade_type><![CDATA[JSAPI]]></trade_type>
 * <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
 * </xml>
 * 
 * @author and
 *
 */
public class WXOrderDO extends DOBase {
	
	private Integer wxOrderId; // 系统 用户id
	private Integer userId; // 系统 用户id
	private String orderType;// 系统 订单类型
	private String orderId;// 系统 订单id
	private Map<String, String> params;// 微信支付 回调参数

	// private Order order;//商城订单，后注入
	// private Consignation consignation;//商城预约单，后注入
	// private QMOrder qmOrder;//千米缴费单，后注入
	// private QMTicketOrder qmTicketOrder;//千米火车票订单（子单），后注入
	// private DZQOrder dzqOrder;//电子券 后注入
	//
	public WXOrderDO() {
		// TODO Auto-generated constructor stub
	}

	public WXOrderDO(Integer userId, String orderType, String orderId, Map<String, String> params) {
		super();
		this.userId = userId;
		this.orderType = orderType;
		this.orderId = orderId;
		this.params = params;
	}
	

	public Integer getWxOrderId() {
		return wxOrderId;
	}

	public void setWxOrderId(Integer wxOrderId) {
		this.wxOrderId = wxOrderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getuserId() {
		return userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getOrderId() {
		return orderId;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setuserId(Integer userId) {
		this.userId = userId;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
