package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import java.math.BigDecimal;

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
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigLocal;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;

@Api(value = "", description = "积分或余额转入")
@Controller
@RequestMapping("pub")
public class ChangeIntoPubController {
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private RecordFacade recordFacade;

	@ResponseBody
	@RequestMapping(value = "takeMoney/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "积分或余额转入", httpMethod = "POST", notes = "积分或余额转入", response = ResponseUtil.class)
	public String addTakeMoney(
			@ApiParam(name = "userName", value = "账号(手机号)", required = false) @RequestParam(value = "userName", required = false) String userName,
			@ApiParam(name = "password", value = "交易密码", required = false) @RequestParam(value = "password", required = false) String password,
			@ApiParam(name = "money", value = "积分或余额值", required = false) @RequestParam(value = "money", required = false) String money,
			@ApiParam(name = "state", value = "类型(1.积分 2.余额)", required = false) @RequestParam(value = "state", required = false) String state,
			HttpServletRequest request) {
		try {
			if(StringUtil.isNULL(userName) || !StringUtil.isNumber(password) || StringUtil.isNULL(money) || StringUtil.isNULL(state)){
				return ResponseUtil.resultString("参数错误", ResponseCodeEnum.ERROR);
			}
			AccountDO accountPo = accountFacade.getUserAccountPoByPhone(userName);
			if (accountPo == null) {
				return ResponseUtil.resultString("未找到用户", ResponseCodeEnum.ERROR);
			}
			UserBO user = userFacade.getUserByAccountId(accountPo.getId());
			if(user==null){
				return ResponseUtil.resultString("未找到用户", ResponseCodeEnum.ERROR);
			}
			UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(user.getUserId(), "0");
			if(userPurse == null){
				return ResponseUtil.resultString("未找到用户钱包", ResponseCodeEnum.ERROR);
			}
			if (!MD5Util.MD5(password).equals(userPurse.getPassword())) {
				return ResponseUtil.resultString("支付密码错误", ResponseCodeEnum.ERROR);
			}
			if(ConfigLocal.password_purse.equals(password)){
				return ResponseUtil.resultString("请修改默认密码后再进行支付", ResponseCodeEnum.ERROR);
			}
			//用户的积分和余额
			BigDecimal purseMoney = new BigDecimal(Double.toString(userPurse.getMoney()));//用户钱包余额
			BigDecimal purseScore = new BigDecimal(Double.toString(userPurse.getScore()));//用户钱包积分
			//积分
			int i= 0;
			if("1".equals(state)){
				BigDecimal newScore = new BigDecimal(money);//传入的积分
				userPurse.setScore(purseScore.add(newScore).doubleValue());
				i = userPurseFacade.updatePurse(userPurse);
				if(i==1){
					RecordPursesDO recordPursesDO = new RecordPursesDO("", "转入积分成功", 
							RecordPursesDO.PURSETYPE_USER,user.getUserId(), 
							user.getUserId(), RecordPursesDO.PURSETYPE_SCORE, RecordPursesDO.TYPE_ADD, 
							newScore.intValue(), "score", "茶宝转入积分:"+newScore);
					recordFacade.insertRecordPurseDO(recordPursesDO);
				}
			}
			//余额
			if("2".equals(state)){
				BigDecimal newMoney = new BigDecimal(money);//传入的余额
				userPurse.setMoney(purseMoney.add(newMoney).doubleValue());
				i = userPurseFacade.updatePurse(userPurse);
				if(i==1){
					RecordPursesDO recordPursesDO = new RecordPursesDO("", "转入余额成功", 
							RecordPursesDO.PURSETYPE_USER,user.getUserId(), 
							user.getUserId(), RecordPursesDO.PURSETYPE_CASH, RecordPursesDO.TYPE_ADD, 
							newMoney.intValue(), "money", "茶宝转入余额:"+newMoney);
					recordFacade.insertRecordPurseDO(recordPursesDO);
				}
			}
			if (i == 1) {
				return ResponseUtil.successResultString("添加成功");
			}
			return ResponseUtil.resultString("添加失败", ResponseCodeEnum.ERROR);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
