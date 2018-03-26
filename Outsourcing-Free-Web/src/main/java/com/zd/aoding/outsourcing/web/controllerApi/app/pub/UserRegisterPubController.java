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
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.SMSManager;

@Api(value = "", description = "用户账号注册")
@Controller
@RequestMapping("pub")
public class UserRegisterPubController {
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private SessionFacade sessionService;

	/**
	 * @Title: testCheck
	 * @Description: 注册保存账号
	 * @param phone
	 * @param password
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "/regUser", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "用户注册", httpMethod = "POST", notes = "注册账号步骤（步骤前台可视顺序情况而定自行决定，除必要的先后逻辑）：1验证手机号是否注册，2请求验证码，3填写密码，4填写推荐人（默认推荐人admin，也可以请求判断推荐人账号是否存在）", response = ResponseUtil.class)
	public String regUser(@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
			@ApiParam(required = true, name = "regCode", value = "手机验证码") @RequestParam(value = "regCode", required = true) String regCode,
			@ApiParam(required = true, name = "password", value = "设置密码") @RequestParam(value = "password", required = true) String password,
//			@ApiParam(required = false, name = "refereePhone", value = "推荐人手机号") @RequestParam(value = "refereePhone", required = false) String refereePhone,
			@ApiParam(required = false, name = "refereeCode", value = "推荐人code") @RequestParam(value = "refereeCode", required = false) String refereeCode,
			HttpServletRequest request) {
		try {
			if ("".equals(regCode) || regCode == null) {
				return ResponseUtil.showMSGResultString("未填写验证码");
			}
			String code = sessionService.checkMemcache(SMSManager.getREGCode(phone));
			if (!regCode.equals(code)) {
				return ResponseUtil.showMSGResultString("验证码错误！");
			}
			AccountDO account = accountFacade.getUserAccountPoByPhone(phone);
			if (account != null) {
				return ResponseUtil.showMSGResultString("该手机号已注册");
			}
			if(!StringUtil.isNULL(refereeCode)){
				UserDO userDO = userFacade.getUserByUserCode(refereeCode);
				if(userDO == null){
					return ResponseUtil.showMSGResultString("该推荐人手机号未注册");
				}
			}
//			if(!StringUtil.isNULL(refereePhone)){
//				AccountDO refereeAccount = accountFacade.getUserAccountPoByPhone(refereePhone);
//				if(refereeAccount == null){
//					return ResponseUtil.resultString("该推荐人手机号未注册", ResponseCodeEnum.ERROR);
//				}
//			}
			AccountDO accountPo = new AccountDO(AccountDO.TYPE_USER, phone, MD5Util.MD5(password));
			int i = userFacade.registerUser(accountPo, "0", refereeCode);
			if (i == 1) {
				// 注册成功自动登录
//				sessionService.saveLoginSession(new AccountBO(accountPo), SessionConstant.LOGIN_TYPE_USER,
//						userFacade.getUserByAccountId(accountPo.getId()), request);
				return ResponseUtil.successResultString("注册成功!");
			}
			return ResponseUtil.showMSGResultString("注册失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/**
	 * @Title: checkPhone
	 * @Description: 检查手机号是否注册
	 * @param phone
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "/cehckPhoneHadUsed", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "检查手机号是否注册", httpMethod = "POST", notes = "检查手机号是否注册，code==200已注册，201未注册", response = ResponseUtil.class)
	public String cehckPhoneHadUsed(
			@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNumber(phone) && phone.length() == 11) {
				AccountDO account = accountFacade.getUserAccountPoByPhone(phone);
				if (account != null) {
					return ResponseUtil.showMSGResultString("手机号已注册");
				} else {
					return ResponseUtil.successResultString("手机号未注册");
				}
			} else {
				return ResponseUtil.showMSGResultString("手机号输入有误");
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
	@RequestMapping(value = "/phoneRegCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "发送注册验证码", httpMethod = "POST", notes = "测试阶段请求均失败code==300，请求之后使用验证码‘123456’", response = ResponseUtil.class)
	public String phoneRegCode(@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
			HttpServletRequest request) {
		try {
			int i = SMSManager.sendRegisterCode(phone);
			if (i == 1) {
				return ResponseUtil.successResultString("发送成功请注意查收");
			}
			return ResponseUtil.showMSGResultString("验证码发送失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
