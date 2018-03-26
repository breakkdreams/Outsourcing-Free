package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountRefereeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountRefereeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.SMSManager;

@Api(value = "", description = "用户账号登陆、退出及密码找回")
@Controller
@RequestMapping("pub")
public class UserLoginPubController {
	@Autowired
	private SessionFacade sessionService;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private AccountRefereeFacade accountRefereeFacade;

	/**
	 * @Title: loginExit
	 * @Description: 退出登录
	 * @param request
	 * @return
	 * @return: Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "logoff", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "注销、退出登录", notes = "退出登录（无参数返回code200成功）", response = ResponseUtil.class)
	public String logoff(
			HttpServletRequest request) {
		try {
			int i = sessionService.removeSessionAndMemcache(request);
			if (i > 0) {
				return ResponseUtil.successResultString("已注销");
			}
			sessionService.removeSessionAndMemcache(request);
			return ResponseUtil.successResultString("已注销");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/**
	 * @Title: login
	 * @Description: 账号密码登陆
	 * @param loginType 登陆类型（user/dealer）
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "账号密码登陆", notes = "详细描述", response = ResponseUtil.class)
	public String login(
			@ApiParam(required = true, name = "loginType", value = "登陆类型:用户‘userVo’") @RequestParam(value = "loginType", required = true) String loginType,
			@ApiParam(required = true, name = "username", value = "用户名（即手机号）") @RequestParam(value = "username", required = true) String username,
			@ApiParam(required = true, name = "password", value = "密码") @RequestParam(value = "password", required = true) String password,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNULL(loginType) || StringUtil.isNULL(username) || StringUtil.isNULL(password)) {
				return ResponseUtil.showMSGResultString("参数错误");
			}
			if (!SessionConstant.LOGIN_TYPE_USER.equals(loginType)) {
				return ResponseUtil.showMSGResultString("非法登录");
			}
			AccountDO accountPo = accountFacade.getUserAccountPoByPhone(username);
			if (accountPo != null) {
				switch (loginType) {
					/**
					 * 用户登录
					 */
					case SessionConstant.LOGIN_TYPE_USER:
						if (accountPo.getPassword().equals(MD5Util.MD5(password))) {
							UserBO user = userFacade.getUserByAccountId(accountPo.getId());
							if (user == null) {
								return ResponseUtil.showMSGResultString("没有该用户");
							}
							if (user != null) {
								UserPurseBO userPurse = userPurseFacade.getUserPurseByUserId(user.getUserId(), "0");
								Double score = 0.0;
								Double bonus = 0.0;
								if(userPurse != null){
									score = userPurse.getScore();
									bonus = userPurse.getBonus();
								}else{
									UserPurseDO pursePo = new UserPurseDO(user.getUserId(), "0");
									int p = userPurseFacade.insertPurse(pursePo);
									if(p != 1){
										return ResponseUtil.errorResultString("未找到钱包 并且创建钱包失败");
									}
								}
								user.setScore(score);
								user.setBonus(bonus);
								AccountRefereeDO referee = accountRefereeFacade.getAccountRefereePoByBeAccountId(user.getAccountId());
								String refereeUsername = "";
								if(referee != null){
									AccountDO refereeAccount = accountFacade.getPoByPK(referee.getRefereeAccountId());
									if(refereeAccount != null){
										refereeUsername = refereeAccount.getUsername();
									}else{
										refereeUsername = "无";
									}
								}else{
									refereeUsername = "无";
								}
								user.setRefereeUsername(refereeUsername);
								AccountBO accountVo = new AccountBO(accountPo);
								sessionService.saveLoginSession(accountVo, loginType, user, request);
								return ResponseUtil.successResultString(user);
							}
						}
						return ResponseUtil.showMSGResultString("密码错误");
					default:
						break;
				}
			}
			return ResponseUtil.showMSGResultString("账号不存在！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
//	/**
//	 * @Title: phoneLogin
//	 * @Description: 手机号登陆
//	 * @param loginType
//	 * @param phone
//	 * @param phoneCode
//	 * @param request
//	 * @return
//	 * @return: String
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/phoneLogin", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "账号验证码登陆", httpMethod = "POST", notes = "步骤1输入手机号账号，2请求登陆验证码，3输入验证码", response = ResponseUtil.class)
//	public String phoneLogin(
//			@ApiParam(required = true, name = "loginType", value = "登陆类型:用户‘userVo’") @RequestParam(value = "loginType", required = true) String loginType,
//			@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
//			@ApiParam(required = true, name = "phoneCode", value = "验证码") @RequestParam(value = "phoneCode", required = true) String phoneCode,
//			HttpServletRequest request) {
//		try {
//			if (StringUtil.isNULL(loginType) || StringUtil.isNULL(phone) || StringUtil.isNULL(phoneCode)) {
//				return ResponseUtil.resultString("参数错误", ResponseCodeEnum.PARAM_ERROR);
//			}
//			AccountPo accountPo = accountFacade.getUserAccountPoByPhone(phone);
//			if (accountPo != null) {
//				switch (loginType) {
//					case SessionConstant.LOGIN_TYPE_USER:
//						String code = sessionService.checkMemcache(SMSManager.getPhoneLoginCode(phone));
//						if (code != null && code.equals(phoneCode)) {
//							UserVo user = userFacade.getUserByAccountId(accountPo.getId());
//							if (user == null) {
//								return ResponseUtil.resultString("没有该用户", ResponseCodeEnum.ERROR);
//							}
//							
//							AccountVo accountVo = new AccountVo(accountPo);
//							sessionService.saveLoginSession(accountVo, loginType, user, request);
//							return ResponseUtil.successResultString(user);
//						}
//						return ResponseUtil.resultString("验证码错误", ResponseCodeEnum.ERROR);
//					default:
//						break;
//				}
//			}
//			return ResponseUtil.resultString("账号不存在！", ResponseCodeEnum.ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/yijqiLogin", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "易键启登陆", httpMethod = "POST", notes = "易键启登陆", response = ResponseUtil.class)
//	public String yijqiLogin(
//			@ApiParam(required = true, name = "loginType", value = "登陆类型:用户‘userVo’") @RequestParam(value = "loginType", required = true) String loginType,
//			@ApiParam(required = true, name = "openId", value = "openId") @RequestParam(value = "openId", required = true) String openId,
//			HttpServletRequest request) {
//		try {
//			if (StringUtil.isNULL(loginType) || StringUtil.isNULL(openId)) {
//				return ResponseUtil.resultString("参数错误", ResponseCodeEnum.PARAM_ERROR);
//			}
//			AccountOpenIdRelationPo accountOpenIdRelationPo = accountOpenIdRelationFacade.getAccountOpenIdRelationPoByOpenId(openId);
//			if (accountOpenIdRelationPo != null) {
//				switch (loginType) {
//					case SessionConstant.LOGIN_TYPE_USER:
//						UserVo user = userFacade.getUserByAccountId(accountOpenIdRelationPo.getAccountId());
//						if (user == null) {
//							return ResponseUtil.resultString("未找到该用户", ResponseCodeEnum.ERROR);
//						}
//						user = view(user, "0");
//						AccountPo accountPo = accountFacade.getPoByPK(accountOpenIdRelationPo.getAccountId());
//						AccountVo accountVo = new AccountVo(accountPo);
//						sessionService.saveLoginSession(accountVo, loginType, user, request);
//						return ResponseUtil.successResultString(user);
//					default:
//						break;
//				}
//			} else {
//				//发送 POST 请求
//		        String str = HttpRequest.sendPost("http://120.27.244.32:8081/yijqi/appSessionInterfaceParameter.web", "openId="+openId+"&appSession="+Config.APPSESSION);
//		        System.out.println(str);
//		        JSONObject object = JSON.parseObject(str);
//		        JSONObject objectResult = JSON.parseObject(object.get("result").toString());
//		        if("200".equals(objectResult.get("Code"))){
//		        	JSONObject object2 = JSON.parseObject(objectResult.get("Response").toString());
//		        	String phone = (String) object2.get("phone");
//		        	String portrait = (String) object2.get("headImg");
//		        	String nickName = (String) object2.get("userName");
//		        	String yjqPhone = "yijqi_"+phone;
//		        	AccountPo accountCheck = accountFacade.getUserAccountPoByPhone(yjqPhone);
//					if (accountCheck != null) {
//						return ResponseUtil.resultString("该账号已注册", ResponseCodeEnum.ERROR);
//					}
//					AccountPo accountPo = new AccountPo(AccountPo.TYPE_USER, yjqPhone, MD5Util.MD5("123456"));
//		        	int i = userFacade.registerUser(accountPo, "0", "");
//		        	if (i == 1) {
//		        		AccountOpenIdRelationPo accountOpenIdRelation = new AccountOpenIdRelationPo(accountPo.getId(), openId);
//		        		int k = accountOpenIdRelationFacade.insertAccountOpenIdRelationPo(accountOpenIdRelation);
//		        		if(k != 1){
//		        			k = accountOpenIdRelationFacade.insertAccountOpenIdRelationPo(accountOpenIdRelation);
//		        		}
//		        		if(k != 1){
//		        			LogUtil.dataError("生成accountOpenId关系失败accountId="+accountPo.getId()+",openId="+openId, getClass());
//		        		}
//		        		if(phone.contains("-")){
//		        			String[] ary = phone.split("-");
//		        			phone = ary[1];
//		        		}
//		        		UserVo user = userFacade.getUserByAccountId(accountPo.getId());
//		        		UserPo userPo = new UserPo();
//		        		userPo.setId(user.getUserId());
//		        		userPo.setNickName(nickName);
//		        		userPo.setPhone(phone);
//		        		userPo.setYjqPortrait(portrait);
//		        		int j = userFacade.updateUserPo(userPo);
//		        		if(j == 1){
//		        			user = userFacade.getUserByAccountId(accountPo.getId());
//		        		}
//		        		user = view(user, "0");
//						// 注册成功自动登录
//						sessionService.saveLoginSession(new AccountVo(accountPo), SessionConstant.LOGIN_TYPE_USER,
//								user, request);
//						return ResponseUtil.successResultString(user);
//					}
//					return ResponseUtil.resultString("注册失败", ResponseCodeEnum.ERROR);
//		        }
//		        return ResponseUtil.resultString("post请求失败", ResponseCodeEnum.ERROR);
//			}
//			return ResponseUtil.resultString("账号不存在！", ResponseCodeEnum.ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
//	/**
//	 * @Title: phoneLoginCode
//	 * @Description:登录发送验证码
//	 * @param phone
//	 * @param request
//	 * @return
//	 * @return: String
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/phoneLoginCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "发送登录验证码", httpMethod = "POST", notes = "用于手机号验证码登陆", response = ResponseUtil.class)
//	public String phoneLoginCode(
//			@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
//			HttpServletRequest request) {
//		try {
//			int i = SMSManager.sendPhoneLoginCode(phone);
//			if (i == 1) {
//				return ResponseUtil.successResultString("发送成功请注意查收");
//			}
//			System.out.println(sessionService.checkMemcache(SMSManager.getPhoneLoginCode(phone)));
//			return ResponseUtil.resultString("验证码发送失败", ResponseCodeEnum.ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
	/**
	 * @Title: changePassword
	 * @Description: 手机号重置密码
	 * @param phone
	 * @param phoneCode
	 * @param newPassword
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "/rechangePasswordByPhoneCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "短信重置密码", httpMethod = "POST", notes = "短信重置密码", response = ResponseUtil.class)
	public String changePassword(
			@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
			@ApiParam(required = true, name = "phoneCode", value = "验证码") @RequestParam(value = "phoneCode", required = true) String phoneCode,
			@ApiParam(required = true, name = "newPassword", value = "新密码") @RequestParam(value = "newPassword", required = true) String newPassword,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNULL(phone) || StringUtil.isNULL(phoneCode) || StringUtil.isNULL(newPassword)) {
				return ResponseUtil.paramErrorResultString("参数错误");
			}
			AccountDO accountPo = accountFacade.getUserAccountPoByPhone(phone);
			if (accountPo != null) {
				String code = sessionService.checkMemcache(SMSManager.getRechangePasswordCode(phone));
				if (phoneCode.equals(code)) {
					Integer id = accountPo.getId();
					int i = accountFacade.reSetPassword(newPassword, id);
					if (i == 1) {
						return ResponseUtil.successResultString("密码重设成功");
					}
					return ResponseUtil.showMSGResultString("重置失败");
				}
				return ResponseUtil.showMSGResultString("验证码错误");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/checkRechangePasswordCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "检查短信重置验证码是否正确", httpMethod = "POST", notes = "检查短信重置验证码是否正确", response = ResponseUtil.class)
	public String changePassword(
			@ApiParam(required = true, name = "phoneCode", value = "验证码") @RequestParam(value = "phoneCode", required = true) String phoneCode,
			@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNULL(phoneCode)) {
				return ResponseUtil.paramErrorResultString("参数错误");
			}
			String code = sessionService.checkMemcache(SMSManager.getRechangePasswordCode(phone));
				if (phoneCode.equals(code)) {
					return ResponseUtil.successResultString("验证码正确");
				}
				return ResponseUtil.showMSGResultString("验证码错误");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/**
	 * @Title: rechangePasswordCode
	 * @Description:重置密码发送验证码
	 * @param phone
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "/rechangePasswordCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "发送重置密码验证码", httpMethod = "POST", notes = "用于短信重置密码", response = ResponseUtil.class)
	public String rechangePasswordCode(
			@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
			HttpServletRequest request) {
		try {
			int i = SMSManager.sendRechangePasswordCode(phone);
			if (i == 1) {
				return ResponseUtil.successResultString("发送成功请注意查收");
			}
			System.out.println(sessionService.checkMemcache(SMSManager.getRechangePasswordCode(phone)));
			return ResponseUtil.showMSGResultString("验证码发送失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
//	
//	private UserVo view(UserVo user, String appCode){
//		UserPurseVo userPurse = userPurseFacade.getUserPurseByUserId(user.getUserId(), appCode);
//		Double score = 0.0;
//		Double bonus = 0.0;
//		if(userPurse != null){
//			score = userPurse.getScore();
//			bonus = userPurse.getBonus();
//		}else{
//			UserPursePo pursePo = new UserPursePo(user.getUserId(), appCode);
//			int p = userPurseFacade.insertUserPurse(pursePo);
//			if(p != 1){
//				LogUtil.dataError("未找到钱包 并且创建钱包失败", getClass());
//			}
//		}
//		user.setScore(score);
//		user.setBonus(bonus);
//		AccountRefereePo referee = accountRefereeFacade.getAccountRefereePoByBeAccountId(user.getAccountId());
//		String refereeUsername = "";
//		if(referee != null){
//			AccountVo refereeAccount = accountFacade.getAccountVoByPK(referee.getRefereeAccountId());
//			if(refereeAccount != null){
//				refereeUsername = refereeAccount.getPhone();
//			}else{
//				refereeUsername = "无";
//			}
//		}else{
//			refereeUsername = "无";
//		}
//		user.setRefereeUsername(refereeUsername);
//		return user;
//	}
	
	/**
	 * @Title: login
	 * @Description: 账号密码登陆
	 * @param loginType 登陆类型（user/dealer）
	 * @param forwordJsp 跳转界面（可为空）
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 * @return: String
	 */
//	@ResponseBody
//	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "账号密码登陆", notes = "详细描述", response = ResponseUtil.class)
//	public String login(
//			@ApiParam(required = true, name = "username", value = "用户名") @RequestParam(value = "username", required = true) String username,
//			@ApiParam(required = true, name = "password", value = "密码") @RequestParam(value = "password", required = true) String password,
//			HttpServletRequest request) {
//		try {
//			
//			ZxUserPo zxUserPo = zxUserFacade.getZxUserByName(username,MD5Util.MD5(password));
//			if (zxUserPo != null) {
//				ZxUserVo zxUserVo = new ZxUserVo(zxUserPo);
//				//获取积分兑换比例
//				String proportionCode = "1";
//				SystemparamPo systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
//			    if(systemparamPo != null){
//			    	proportionCode = systemparamPo.getStringVale();
//			    }
//			    double proportionCodeDouble =  Double.parseDouble(proportionCode);
//			    //获取用户积分
//			    double userMoney = zxUserPo.getUserMoney1();
//			    //兑换后比例
//			    double resuleScore = userMoney*proportionCodeDouble;
//			    zxUserVo.setUserMoney1(resuleScore);
//			    
//				sessionService.saveZxLoginSession(zxUserVo, "userVo", zxUserVo, request);
//				return ResponseUtil.successResultString(zxUserVo);
//			}
//			return ResponseUtil.resultString("账号不存在！", ResponseCodeEnum.ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
}
