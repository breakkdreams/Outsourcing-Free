package com.zd.aoding.outsourcing.web.controllerApi.pay;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BaseOrder;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigLocal;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.CashPayFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.PayFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;

@Api(value = "", description = "支付中转")
@Controller
@RequestMapping("user/pay")
public class PreparePayController {
	@Autowired
	private SessionFacade sessionService;
	@Autowired
	private PayFacade payFacade;
	@Autowired
	private ShoppingOrderFacade orderFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private CashPayFacade cashPayFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private RecordFacade recordFacade;

	@ResponseBody
	@RequestMapping(value = "preparePay", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "支付前选择支付方式", httpMethod = "POST", notes = "暂时只对接支付宝和现金支付", response = ResponseUtil.class)
	public String userPurseTiXian(
			@ApiParam(required = true, name = "payWay", value = "支付选择：支付宝传‘1’，微信传‘2’,现金余额5") @RequestParam(value = "payWay", required = true) String payWay,
			@ApiParam(required = true, name = "orderId", value = "订单id（下单时返回的id）") @RequestParam(value = "orderId", required = true) String orderId,
			@ApiParam(required = true, name = "boughtWhat", value = "订单类型：订单簿‘orderBook’(正常流程返回订单簿提交的)，订单‘order’（待付款界面提交）") @RequestParam(value = "boughtWhat", required = true) String boughtWhat,
			@ApiParam(required = false, name = "password", value = "支付密码") @RequestParam(value = "password", required = false) String password,
			@ApiParam(required = false, name = "isuse", value = "是否使用积分(1.使用 2.不使用)") @RequestParam(value = "isuse", required = false) String isuse,
//			@ApiParam(name = "appCode", value = "appCode") @PathVariable(value = "appCode") String appCode,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNumber(payWay) || !StringUtil.isNumber(orderId) || StringUtil.isNULL(boughtWhat)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if (BaseOrder.payType_money.equals(Integer.parseInt(payWay))) {
					if (ConfigLocal.pay_order.equals(boughtWhat)) {
						if (StringUtil.isNULL(password)) {
							return ResponseUtil.showMSGResultString("请输入支付密码");
						}
						UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
						if(userPurse == null){
							return ResponseUtil.showMSGResultString("未找到用户钱包");
						}
						if(ConfigLocal.password_purse.equals(password)){
							return ResponseUtil.showMSGResultString("请修改默认密码后再进行支付");
						}
						if(!MD5Util.MD5(password).equals(userPurse.getPassword())){
							return ResponseUtil.showMSGResultString("支付密码错误");
						}
					}
				}
				boolean f = false;
				boolean t = false;
				f = BaseOrder.payType_Bonus.equals(Integer.parseInt(payWay)) || BaseOrder.payType_WX.equals(Integer.parseInt(payWay))
						|| BaseOrder.payType_ZFB.equals(Integer.parseInt(payWay)) || BaseOrder.payType_money.equals(Integer.parseInt(payWay));
				t = ConfigLocal.pay_orderBook.equals(boughtWhat) || ConfigLocal.pay_order.equals(boughtWhat);
				if (!f || !t) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				Map<String, Object> payResult = payFacade.preparePay(payWay, orderId, boughtWhat, sessionUser, "0");
				String i = (String) payResult.get("code");
				System.err.println("code:==============="+i);
				if ("-1".equals(i)) {
					return ResponseUtil.showMSGResultString("价格出错");
				}
				if ("-2".equals(i)) {
					return ResponseUtil.showMSGResultString("余额不够钱包出错");
				}
				if ("200".equals(i)) {
					System.out.println(payResult);
					System.out.println(payResult.get("result"));
					return ResponseUtil.successResultString(payResult);
				} // 余额不足
				if ("202".equals(i)) {
					return ResponseUtil.successResultString(payResult);
				}
				return ResponseUtil.showMSGResultString("支付失败");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "scorePay", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "积分订单支付", httpMethod = "POST", notes = "积分订单支付", response = ResponseUtil.class)
	public String scorePay(
			@ApiParam(required = true, name = "orderId", value = "订单id（下单时返回的id）") @RequestParam(value = "orderId", required = true) String orderId,
			@ApiParam(required = true, name = "boughtWhat", value = "订单类型：订单簿‘orderBook’(正常流程返回订单簿提交的)，订单‘order’（待付款界面提交）") @RequestParam(value = "boughtWhat", required = true) String boughtWhat,
			@ApiParam(required = false, name = "password", value = "支付密码") @RequestParam(value = "password", required = false) String password,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if(ConfigLocal.pay_order.equals(boughtWhat)){
					if (StringUtil.isNULL(password)) {
						return ResponseUtil.showMSGResultString("请输入支付密码");
					}
					UserPurseDO zxuser = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
					if(!MD5Util.MD5(password).equals(zxuser.getPassword())){
						return ResponseUtil.showMSGResultString("支付密码错误");
					}
				}
				boolean t = false;
				t = ConfigLocal.pay_orderBook.equals(boughtWhat) || ConfigLocal.pay_order.equals(boughtWhat);
				if (!t) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				int i = cashPayFacade.useScorePaidOrders(orderId, boughtWhat, sessionUser, "0");
				if (i == -1) {
					return ResponseUtil.showMSGResultString("价格出错");
				}
				if (i == -2) {
					return ResponseUtil.showMSGResultString("订单出错");
				}
				if (i == 1) {
					Map<String, Object> payResult = new HashMap<String, Object>();
					payResult.put("result", "积分支付成功");
					return ResponseUtil.successResultString(payResult);
				} 
				return ResponseUtil.showMSGResultString("支付失败");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
//	@ResponseBody
//	@RequestMapping(value = "bonusPay", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "现金订单支付", httpMethod = "POST", notes = "现金订单支付", response = ResponseUtil.class)
//	public String bonusPay(
//	        @ApiParam(required = true, name = "orderId", value = "订单id（下单时返回的id）") @RequestParam(value = "orderId", required = true) String orderId,
//	        @ApiParam(required = true, name = "boughtWhat", value = "订单类型：订单簿‘orderBook’(正常流程返回订单簿提交的)，订单‘order’（待付款界面提交）") @RequestParam(value = "boughtWhat", required = true) String boughtWhat,
//	        @ApiParam(required = false, name = "password", value = "支付密码") @RequestParam(value = "password", required = false) String password,
////	        @ApiParam(name = "appCode", value = "appCode") @PathVariable(value = "appCode") String appCode,
//	        HttpServletRequest request) {
//	    try {
//	    	ZxUserVo user = (ZxUserVo) sessionService.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER, "0");
//	        if (user != null) {
//	            if (StringUtil.isNULL(password)) {
//	                return ResponseUtil.resultString("请输入支付密码", ResponseCodeEnum.PARAM_ERROR);
//	            }
//	            UserPursePo userPurse = userPurseFacade.getUserPursePoByUserId(user.getUserId(), "0");
//	            if(userPurse == null){
//	                return ResponseUtil.resultString("未找到用户钱包", ResponseCodeEnum.ERROR);
//	            }
//	            if(!MD5Util.MD5(password).equals(userPurse.getPassword())){
//	                return ResponseUtil.resultString("支付密码错误", ResponseCodeEnum.ERROR);
//	            }
//	            if(Config.password_purse.equals(password)){
//	                return ResponseUtil.resultString("请修改默认密码后再进行支付", ResponseCodeEnum.ERROR);
//	            }
//	            boolean t = false;
//	            t = Config.pay_orderBook.equals(boughtWhat) || Config.pay_order.equals(boughtWhat);
//	            if (!t) {
//	                return ResponseUtil.resultString("参数错误", ResponseCodeEnum.PARAM_ERROR);
//	            }
//	            int i = cashPayFacade.useBonusPaidOrders(orderId, boughtWhat, user, "0");
//	            if (i == -1) {
//	                return ResponseUtil.resultString("价格出错", ResponseCodeEnum.ERROR);
//	            }
//	            if (i == 2) {
//	                return ResponseUtil.resultString("奖金不够钱包出错", ResponseCodeEnum.ERROR);
//	            }
//	            if (i == 1) {
//	                Map<String, Object> payResult = new HashMap<String, Object>();
//	                payResult.put("result", "奖金支付成功");
//	                return ResponseUtil.successResultString(payResult);
//	            } 
//	            return ResponseUtil.resultString("支付失败", ResponseCodeEnum.PARAM_ERROR);
//	        }
//	        return ResponseUtil.notLoggedResultString();
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return ResponseUtil.systemErrorResultString();
//	    }
//	}
//	@ResponseBody
//	@RequestMapping(value = "moneyPay", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "余额订单支付", httpMethod = "POST", notes = "余额订单支付", response = ResponseUtil.class)
//	public String moneyPay(
//	        @ApiParam(required = true, name = "orderId", value = "订单id（下单时返回的id）") @RequestParam(value = "orderId", required = true) String orderId,
//	        @ApiParam(required = true, name = "boughtWhat", value = "订单类型：订单簿‘orderBook’(正常流程返回订单簿提交的)，订单‘order’（待付款界面提交）") @RequestParam(value = "boughtWhat", required = true) String boughtWhat,
//	        @ApiParam(required = false, name = "password", value = "支付密码") @RequestParam(value = "password", required = false) String password,
////	        @ApiParam(name = "appCode", value = "appCode") @PathVariable(value = "appCode") String appCode,
//	        HttpServletRequest request) {
//	    try {
//	    	UserBO user = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
//	        if (user != null) {
//	            if (StringUtil.isNULL(password)) {
//	                return ResponseUtil.resultString("请输入支付密码", ResponseCodeEnum.PARAM_ERROR);
//	            }
//	            UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(user.getUserId(), "0");
//	            if(userPurse == null){
//	                return ResponseUtil.resultString("未找到用户钱包", ResponseCodeEnum.ERROR);
//	            }
//	            if(!MD5Util.MD5(password).equals(userPurse.getPassword())){
//	                return ResponseUtil.resultString("支付密码错误", ResponseCodeEnum.ERROR);
//	            }
//	            if(Config.password_purse.equals(password)){
//	                return ResponseUtil.resultString("请修改默认密码后再进行支付", ResponseCodeEnum.ERROR);
//	            }
//	            boolean t = false;
//	            t = Config.pay_orderBook.equals(boughtWhat) || Config.pay_order.equals(boughtWhat);
//	            if (!t) {
//	                return ResponseUtil.resultString("参数错误", ResponseCodeEnum.PARAM_ERROR);
//	            }
//	            int i = cashPayFacade.useMoneyPaidOrders(orderId, boughtWhat, user, "0");
//	            if (i == -1) {
//	                return ResponseUtil.resultString("价格出错", ResponseCodeEnum.ERROR);
//	            }
//	            if (i == 2) {
//	                return ResponseUtil.resultString("余额不够", ResponseCodeEnum.ERROR);
//	            }
//	            if (i == 1) {
//	            	double needFee = payFacade.getTotalFee(orderId, boughtWhat);
//	            	int intFree = (int) needFee;
//	            	RecordPursesDO recordPursesDO = new RecordPursesDO("", "余额支付成功", 
//							RecordPursesDO.PURSETYPE_USER, Integer.parseInt(orderId), 
//							user.getUserId(), RecordPursesDO.PURSETYPE_CASH, RecordPursesDO.TYPE_CUT, 
//							intFree, "", user.getUsername()+"在商城消费奖金:"+needFee+"元.");
//					int u = recordFacade.insertRecordPurseDO(recordPursesDO);
//	                Map<String, Object> payResult = new HashMap<String, Object>();
//	                payResult.put("result", "余额支付成功");
//	                return ResponseUtil.successResultString(payResult);
//	            } 
//	            return ResponseUtil.resultString("支付失败", ResponseCodeEnum.PARAM_ERROR);
//	        }
//	        return ResponseUtil.notLoggedResultString();
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return ResponseUtil.systemErrorResultString();
//	    }
//	}
}
