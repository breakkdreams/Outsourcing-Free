package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import java.math.BigDecimal;
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
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.TakeMoneyBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankCardDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.TakeMoneyDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigLocal;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.BankCardFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.TakeMoneyFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;

@Api(value = "", description = "提现管理")
@Controller
@RequestMapping("user")
public class TakeMoneyController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private TakeMoneyFacade takeMoneyFacade;
	@Autowired
	private BankCardFacade bankCardFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	
	@ResponseBody
	@RequestMapping(value = "takeMoney/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "提现", httpMethod = "POST", notes = "提现", response = ResponseUtil.class)
	public String addTakeMoney(
			@ApiParam(name = "bankCardId", value = "银行卡号id", required = false) @RequestParam(value = "bankCardId", required = false) String bankCardId,
			@ApiParam(name = "money", value = "金额", required = false) @RequestParam(value = "money", required = false) String money,
			@ApiParam(name = "password", value = "交易密码", required = false) @RequestParam(value = "password", required = false) String password,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if(StringUtil.isNULL(bankCardId) || !StringUtil.isNumber(bankCardId) || !StringUtil.isNumber(money) || StringUtil.isNULL(password) ){
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
				if(userPurse==null){
					return ResponseUtil.errorResultString("找不到钱包");
				}
				if (!MD5Util.MD5(password).equals(userPurse.getPassword())) {
					return ResponseUtil.showMSGResultString("支付密码错误");
				}
				if(ConfigLocal.password_purse.equals(password)){
					return ResponseUtil.showMSGResultString("请修改默认密码后再进行支付");
				}
				int moneyInt = Integer.parseInt(money);
				if(moneyInt<100){
					return ResponseUtil.showMSGResultString("金额必须大于100");
				}
				if(moneyInt % 100 != 0 ){
					return ResponseUtil.showMSGResultString("金额为100的倍数");
				}
				//获取提现手续费
				BigDecimal rechargeMoney = new BigDecimal("1");
				BigDecimal num100 = new BigDecimal(100);
				SystemparamDO systemParam = systemparamFacade.getSystemparamPoByCode("takeMoney");
				if(systemParam != null){
					String chargeStr = systemParam.getStringVale();
					if(!StringUtil.isNULL(chargeStr)){
						rechargeMoney = new BigDecimal(chargeStr);
						rechargeMoney = rechargeMoney.divide(num100);
					}
				}
				
				BigDecimal purseMoney = new BigDecimal(Double.toString(userPurse.getMoney()));//用户钱包余额
				BigDecimal bigMoney = new BigDecimal(money);//前台传来金额
				if(bigMoney.compareTo(purseMoney)==1){
					return ResponseUtil.showMSGResultString("余额不足");
				}
				bigMoney = bigMoney.subtract(bigMoney.multiply(rechargeMoney));
				String bankNum = "";
				String bankKaihu = "";
				String userName = "";
				String bankName = "";
				BankCardDO bankCardDO = bankCardFacade.getBankCardDOByPK(Integer.parseInt(bankCardId));
				if(bankCardDO!=null){
					bankNum = bankCardDO.getBankNum();
					bankKaihu = bankCardDO.getBankKaihu();
					userName = bankCardDO.getCardName();
					bankName = bankCardDO.getBankName();
				}
				TakeMoneyDO takeMoneyDO = new TakeMoneyDO(sessionUser.getUserId(), 
						Integer.parseInt(bankCardId), bankNum, bankKaihu, userName, bigMoney.doubleValue(),bankName);
				takeMoneyDO.setCharge(rechargeMoney.toString());
				
				int j = takeMoneyFacade.insertTakeMoneyDO(takeMoneyDO);
				if (j == 1) {
					BigDecimal oldMoney = new BigDecimal(money);
					BigDecimal cutMoney = purseMoney.subtract(oldMoney);
					userPurse.setMoney(cutMoney.doubleValue());
					userPurseFacade.updatePurse(userPurse);
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "takeMoneyToOther/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "提现其他银行", httpMethod = "POST", notes = "提现", response = ResponseUtil.class)
	public String addTakeMoneyToOther(
			@ApiParam(name = "bankNum", value = "银行卡号", required = false) @RequestParam(value = "bankNum", required = false) String bankNum,
			@ApiParam(name = "bankKaihu", value = "银行卡开户", required = false) @RequestParam(value = "bankKaihu", required = false) String bankKaihu,
			@ApiParam(name = "userName", value = "姓名", required = false) @RequestParam(value = "userName", required = false) String userName,
			@ApiParam(name = "bankName", value = "银行名称", required = false) @RequestParam(value = "bankName", required = false) String bankName,
			@ApiParam(name = "money", value = "金额", required = false) @RequestParam(value = "money", required = false) String money,
			@ApiParam(name = "password", value = "交易密码", required = false) @RequestParam(value = "password", required = false) String password,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if(StringUtil.isNULL(money) || !StringUtil.isNumber(money) || StringUtil.isNULL(password)){
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
				if(userPurse==null){
					return ResponseUtil.errorResultString("找不到钱包");
				}
				if (!MD5Util.MD5(password).equals(userPurse.getPassword())) {
					return ResponseUtil.showMSGResultString("支付密码错误");
				}
				if(ConfigLocal.password_purse.equals(password)){
					return ResponseUtil.showMSGResultString("请修改默认密码后再进行支付");
				}
				int moneyInt = Integer.parseInt(money);
				if(moneyInt<100){
					return ResponseUtil.showMSGResultString("金额必须大于100");
				}
				if(moneyInt % 100 != 0 ){
					return ResponseUtil.showMSGResultString("金额为100的倍数");
				}
				//获取提现手续费
				BigDecimal rechargeMoney = new BigDecimal("1");
				BigDecimal num100 = new BigDecimal(100);
				SystemparamDO systemParam = systemparamFacade.getSystemparamPoByCode("takeMoney");
				if(systemParam != null){
					String chargeStr = systemParam.getStringVale();
					if(!StringUtil.isNULL(chargeStr)){
						rechargeMoney = new BigDecimal(chargeStr);
						rechargeMoney = rechargeMoney.divide(num100);
					}
				}
				BigDecimal purseMoney = new BigDecimal(Double.toString(userPurse.getMoney()));//用户钱包余额
				BigDecimal bigMoney = new BigDecimal(money);//前台传来金额
				if(bigMoney.compareTo(purseMoney)==1){
					return ResponseUtil.showMSGResultString("余额不足");
				}
				bigMoney = bigMoney.subtract(bigMoney.multiply(rechargeMoney));
				TakeMoneyDO takeMoneyDO = new TakeMoneyDO(sessionUser.getUserId(), 
						0, bankNum, bankKaihu, userName, bigMoney.doubleValue(),bankName);
				takeMoneyDO.setCharge(rechargeMoney.toString());
				int j = takeMoneyFacade.insertTakeMoneyDO(takeMoneyDO);
				if (j == 1) {
					BigDecimal cutMoney = purseMoney.subtract(bigMoney);
					userPurse.setMoney(cutMoney.doubleValue());
					userPurseFacade.updatePurse(userPurse);
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "takeMoney/getPage", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "分页查询", httpMethod = "POST", notes = "查看提现记录", response = ResponseUtil.class)
	public String getAddress(
			@ApiParam(required = false, name = "pageNum", value = "当前页数（即当前第几页）") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (StringUtil.isNumber(pageNum)) {
					pageEntity.setPage(Integer.parseInt(pageNum));
				}
				if (StringUtil.isNumber(pageSize)) {
					pageEntity.setSize(Integer.parseInt(pageSize));
				}
				/**
				 * 排序
				 */
				pageEntity.setOrderColumn("id");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", sessionUser.getUserId());
				param.put("deleted", 0);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<TakeMoneyBO> pageResult = takeMoneyFacade.getPageTakeMoneyBO(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "takeMoney/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "提现详情", httpMethod = "POST", notes = "提现详情", response = ResponseUtil.class)
	public String takeMoneyDetail(
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
				if(userPurse==null){
					return ResponseUtil.errorResultString("找不到钱包");
				}
				Double money = 0.0;
				money = userPurse.getMoney();
				String takeMoneyRe = "0";
				SystemparamDO systemParam = systemparamFacade.getSystemparamPoByCode("takeMoney");
				if(systemParam!=null){
					takeMoneyRe = systemParam.getStringVale();
				}
				Map<String, Object> map = new HashMap<>();
				map.put("userMoney", money);
				map.put("takeMoneyRe", takeMoneyRe);
				return ResponseUtil.successResultString(map);
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
