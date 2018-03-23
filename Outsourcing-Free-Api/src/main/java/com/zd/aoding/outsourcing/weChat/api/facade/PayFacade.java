package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;

public interface PayFacade {
	/**
	 * @Title: checkZfBOrderIsInsert
	 * @Description: 检查是否已插入支付宝支付订单
	 * @param notify_id
	 * @return
	 * @return: boolean
	 */
	public boolean checkZfBOrderIsInsert(String notify_id);
	// public boolean checkWXOrderIsInsert(String notify_id);
	/**
	 * @Title: getTotalFee
	 * @Description: 查询所需支付金额
	 * @param orderId 订单簿id，o2o订单id
	 * @param boughtWhat 购买类型
	 * @return
	 * @return: Double
	 */
	public Double getTotalFee(String orderId, String boughtWhat);
	public Double getCashTotalFee(String orderId, String boughtWhat);
	/**
	 * @Title: getTotalFee
	 * @Description: 查询成本价
	 * @param orderId 订单簿id，o2o订单id
	 * @param boughtWhat 购买类型
	 * @return
	 * @return: Double
	 */
	public Double getTotalOriginalPrice(String orderId);
	
	/**
	 * @Title: preparePay
	 * @Description: 为app支付返回直接数据
	 * @param payWay
	 * @param orderId
	 * @param user
	 * @param orderType
	 * @return
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> preparePay(String payWay, String orderId, String boughtWhat, UserBO user, String appCode);
	/**
	 * @Title: getOutTradeNo
	 * @Description: 获取提交支付的id（生成交付支付订单唯一id）
	 * @param orderId
	 * @param boughtWhat
	 * @return
	 * @return: String
	 */
	public String getOutTradeNo(String orderId, String boughtWhat, UserBO user);
	public String getOutTradeNo(String orderId, String boughtWhat, AccountBO account);
	/**
	 * @Title: paidOrder
	 * @Description: 完成支付
	 * @param orderIdOrOrderBookId
	 * @param boughtAccountId
	 * @param boughtWhat
	 * @param paytypeZfb
	 * @return
	 * @return: int
	 */
	int finshPaid(String orderIdOrOrderBookId, String boughtAccountId, String boughtWhat, Integer paytype);
}
