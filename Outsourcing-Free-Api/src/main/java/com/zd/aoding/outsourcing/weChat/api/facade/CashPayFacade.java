package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;

public interface CashPayFacade {
	/**
	 * @Title: useCashPaidOrders
	 * @Description: 现金支付订单（包括b2c，o2o）
	 * @param orderId 订单簿id，o2o订单id
	 * @param boughtWhat 支付的类型（b2c总金额，o2o订金，o2o尾款）
	 * @param userMapper 用户
	 * @return
	 * @return: int -1总价<0;-2 b2c订单积分<0;2钱不够；3积分不够
	 */
	int useScorePaidOrders(String orderId, String boughtWhat, UserBO user, String appCode);
	int useBonusPaidOrders(String orderId, String boughtWhat, UserBO user, String appCode);
	int useMoneyPaidOrders(String orderId, String boughtWhat, UserBO user, String appCode);
}
