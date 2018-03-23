package com.zd.aoding.outsourcing.web.controllerApi.app.user;

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
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RecordPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.SMSManager;

@Api(value = "", description = "用户钱包管理")
@Controller
@RequestMapping("user")
public class UserPurseController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private RecordFacade recordFacade;

	@ResponseBody
	@RequestMapping(value = "reSetTradePassword", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改交易密码", httpMethod = "POST", notes = "检查手机号是否注册，code==200已注册，300未注册，999系统错误", response = ResponseUtil.class)
	public String reSetTradePassword(
			@ApiParam(required = true, name = "code", value = "验证码") @RequestParam(value = "code", required = true) String code,
			@ApiParam(required = true, name = "loginPassword", value = "登录密码") @RequestParam(value = "loginPassword", required = true) String loginPassword,
			@ApiParam(required = true, name = "tradePassword", value = "交易密码") @RequestParam(value = "tradePassword", required = true) String tradePassword,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser == null) {
				return ResponseUtil.notLoggedResultString();
			}
//			tradePassword.replace(" ", "");
//			String reg = ("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
//			if (StringUtil.isNULL(tradePassword) || !tradePassword.matches(reg)) {
//				return ResponseUtil.resultString("格式有误", ResponseCodeEnum.PARAM_ERROR);
//			}
			if(StringUtil.isNULL(tradePassword)){
				return ResponseUtil.paramErrorResultString("交易密码不能为空");
			}
			if (StringUtil.isNULL(loginPassword)) {
				return ResponseUtil.paramErrorResultString(loginPassword + "=loginPassword");
			}
			String rCode = sessionFacade.checkMemcache(SMSManager.getResetTPCode(sessionUser.getPhone()));
			if (!code.equals(rCode)) {
				return ResponseUtil.showMSGResultString("验证码错误!");
			}
			AccountDO account = accountFacade.getPoByPK(sessionUser.getAccountId());
			if (account != null && account.getPassword().equals(MD5Util.MD5(loginPassword))) {
				UserPurseBO userPurseVo = userPurseFacade.getUserPurseByUserId(sessionUser.getUserId(), "0");
				if (userPurseVo != null) {
					int i = userPurseFacade.setPassword(userPurseVo.getUserPurseId(), tradePassword);
					if (i == 1) {
						return ResponseUtil.successResultString("设置成功");
					} else {
						return ResponseUtil.showMSGResultString("设置失败");
					}
				} else {
					return ResponseUtil.errorResultString("钱包未找到");
				}
			} else {
				return ResponseUtil.showMSGResultString("未找到账号或登录密码不对");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/**
	 * @Title: phoneRegCode
	 * @Description:注册发送验证码
	 * @param phone
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "phoneReSetradePasswordCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "发送修改交易密码验证码", httpMethod = "POST", notes = "测试阶段请求均失败code==300，请求之后使用验证码‘123456’", response = ResponseUtil.class)
	public String phoneRegCode(
			HttpServletRequest request) {
		try {
			UserBO user = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (user == null) {
				return ResponseUtil.notLoggedResultString();
			}
			int i = SMSManager.sendReSetTradePasswordCode(user.getPhone());
			if (i == 1) {
				return ResponseUtil.successResultString("发送成功请注意查收");
			}
			return ResponseUtil.showMSGResultString("验证码发送失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/checkReSetradePasswordCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "检查修改交易密码验证码是否正确", httpMethod = "POST", notes = "检查修改交易密码验证码是否正确", response = ResponseUtil.class)
	public String changePassword(
			@ApiParam(required = true, name = "phoneCode", value = "验证码") @RequestParam(value = "phoneCode", required = true) String phoneCode,
			HttpServletRequest request) {
		try {
			UserBO user = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (user == null) {
				return ResponseUtil.notLoggedResultString();
			}
			if (StringUtil.isNULL(phoneCode)) {
				return ResponseUtil.paramErrorResultString("参数错误");
			}
			String code = sessionFacade.checkMemcache(SMSManager.getResetTPCode(user.getPhone()));
				if (phoneCode.equals(code)) {
					return ResponseUtil.successResultString("验证码正确");
				}
				return ResponseUtil.showMSGResultString("验证码错误");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/getBalance", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看钱包余额", httpMethod = "POST", notes = "查看钱包余额", response = ResponseUtil.class)
	public String getBalance(
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser == null) {
				return ResponseUtil.notLoggedResultString();
			}
			//获取兑换比例
			String proportion = "1";
			SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
			if(systemparamPo != null){
				proportion = systemparamPo.getStringVale();
			}
			double d_proportion = Double.valueOf(proportion).doubleValue();
			Double score = 0.0;
			Double money = 0.0;
			UserPurseDO purseDo = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
			if(purseDo!=null){
				score = purseDo.getScore() * d_proportion;
				money = purseDo.getMoney();
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("money", money);
			result.put("score", score);
			return ResponseUtil.successResultString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPurseRecord", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看钱包记录", httpMethod = "POST", notes = "分页查看钱包记录", response = ResponseUtil.class)
	public String getPurseRecord(
			@ApiParam(required = false, name = "pageNum", value = "当前pageNum页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = false, name = "purseType", value = "记录类型(-1.全部 4.积分 5.返利 7.奖金)") @RequestParam(value = "purseType", required = false) String purseType,
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
				pageEntity.setOrderColumn("create_time");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("beDealedId", sessionUser.getUserId());
				if(!StringUtil.isNULL(purseType)){
					if("-1".equals(purseType)){
						param.put("purseTypeAll", 1);
					}else{
						param.put("purseType",purseType);
					}
				}else{
					param.put("purseType",999);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<RecordPurseBO> pageResult = recordFacade.getPageRecordPurseVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/generalMall", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "转至母商城", httpMethod = "POST", notes = "转至母商城", response = ResponseUtil.class)
//	public String generalMall(
//			@ApiParam(required = false, name = "password", value = "支付密码") @RequestParam(value = "password", required = false) String password,
//			HttpServletRequest request) {
//		try {
//			UserVo user = (UserVo) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER, "0");
//			if (user == null) {
//				return ResponseUtil.notLoggedResultString();
//			}
//			UserPurseVo userPurse = userPurseFacade.getUserPurseByUserId(user.getUserId(), "0");
//			if(userPurse == null){
//				return ResponseUtil.resultString("未找到钱包", ResponseCodeEnum.ERROR);
//			}
//			if(StringUtil.isNULL(password)){
//				return ResponseUtil.resultString("请填写支付密码", ResponseCodeEnum.PARAM_ERROR);
//			}
//			if(!userPurse.getPassword().equals(MD5Util.MD5(password))){
//				return ResponseUtil.resultString("支付密码错误", ResponseCodeEnum.PARAM_ERROR);
//			}
//			AppCompanyPo company = companyFacade.getCompanyPoByAppCode("0");
//			if(!"0".equals(company.getActivation()+"")){
//				return ResponseUtil.resultString("当前商城尚未关闭 不能转至母商城", ResponseCodeEnum.ERROR);
//			}
//			/** 获取母商城"0" */
//			String generalMallAppCode = sessionFacade.checkSystemParamMemcacheString("GeneralMallAppCode");
//			if(generalMallAppCode == null){
//				SystemparamPo systemparam = systemparamFacade.getSystemparamPoByCode("GeneralMallAppCode");
//				if(systemparam != null){
//					sessionFacade.saveSystemParamMemcacheString("GeneralMallAppCode", systemparam.getStringVale());
//					generalMallAppCode = systemparam.getStringVale();
//				}
//			}
//			if(generalMallAppCode == null){
//				return ResponseUtil.resultString("未找到系统参数 母商城", ResponseCodeEnum.ERROR);
//			}
//			UserPursePo userGeneralPurse = userPurseFacade.getUserPursePoByUserId(user.getUserId(), generalMallAppCode);
//			if(userGeneralPurse == null){
//				userGeneralPurse = new UserPursePo(user.getUserId(), generalMallAppCode);
//				int p = userPurseFacade.insertUserPurse(userGeneralPurse);
//				if(p != 1){
//					return ResponseUtil.resultString("未找到母商城钱包 并且创建失败", ResponseCodeEnum.ERROR);
//				}
//			}
//			userGeneralPurse.setScore(userGeneralPurse.getScore() + userPurse.getScore());
//			userGeneralPurse.setBonus(userGeneralPurse.getBonus() + userPurse.getBonus());
//			userGeneralPurse.setMoney(userGeneralPurse.getMoney() + userPurse.getMoney());
//			int i = userPurseFacade.updatePurse(userGeneralPurse);
//			if(i == 1){
//				return ResponseUtil.successResultString("成功传入母商城");
//			}
//			return ResponseUtil.resultString("转至母商城失败", ResponseCodeEnum.ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
}
