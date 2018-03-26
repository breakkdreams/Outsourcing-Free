package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.util.Date;

import com.zd.aoding.outsourcing.weChat.api.constant.MemcachedConstant;
import com.zd.aoding.plugin.SMS.SMSUtil;

/**
 * @ClassName: SMSManager
 * @Description: 验证码事物处理
 * @author: zj
 * @date: 2017年3月8日 下午3:17:45
 */
public class SMSManager {
	/**
	 * @fieldName: REGISTER
	 * @fieldType: String
	 * @Description: 注册验证码
	 */
	public static final String REGISTER = MemcachedConstant.MSM_CODE + "register";
	/**
	 * @fieldName: PHONE_LOGIN
	 * @fieldType: String
	 * @Description: 手机登录
	 */
	public static final String PHONE_LOGIN = MemcachedConstant.MSM_CODE + "phoneLogin";
	/**
	 * @fieldName: RECHANGE_PASSWORD
	 * @fieldType: String
	 * @Description: 重置密码
	 */
	public static final String RECHANGE_PASSWORD = MemcachedConstant.MSM_CODE + "rechangePassword";
	/**
	 * @fieldName: UPDATE_PASSWORD
	 * @fieldType: String
	 * @Description: 修改密码
	 */
	public static final String UPDATE_PASSWORD = MemcachedConstant.MSM_CODE + "updatePassword";
	/**
	 * @fieldName: UPDATE_PASSWORD
	 * @fieldType: String
	 * @Description: 修改交易密码
	 */
	public static final String ResetTradePassword = MemcachedConstant.MSM_CODE + "resetTradePassword";
	/**
	 * @fieldName: UPDATE_PASSWORD
	 * @fieldType: String
	 * @Description: 真实姓名
	 */
	public static final String RealNameCode = MemcachedConstant.MSM_CODE + "RealNameCode";

	/**
	 * 生成验证码
	 * @return
	 */
	public static String generateCode() {
		String code = "123456";
//		 String num = (new Date().getTime()) + "";
//		 code = num.subSequence(7, 13).toString();
		return code;
	}
	/**
	 * 发送 注册验证码
	 * @param tel 手机号码
	 * @return
	 */
	public static int sendRegisterCode(String tel) {
		int result = 0;
		String msg = generateRegisterCode(tel);
		result = SMSUtil.sendMsg(tel, msg);
		return result;
	}
	public static int sendBankCode(String tel) {
		int result = 0;
		String msg = generateBankMsg(tel);
		result = SMSUtil.sendMsg(tel, msg);
		return result;
	}
	/**
	 * 实名认证验证码
	 * @param tel 手机号码
	 * @return
	 */
	public static int sendRealNameCode(String tel) {
		int result = 0;
		String msg = generateRealNameCode(tel);
		result = SMSUtil.sendMsg(tel, msg);
		return result;
	}
	/**
	 * 重设密码证码
	 * @param tel 手机号码
	 * @return
	 */
	public static int sendReSetTradePasswordCode(String tel) {
		int result = 0;
		String msg = generateTradePasswordCode(tel);
		result = SMSUtil.sendMsg(tel, msg);
		return result;
	}
	/**
	 * @Title: generateRegisterCode
	 * @Description: 生成注册验证码并保存memcache
	 * @param tel
	 * @return设定文件
	 * @return String 返回类型
	 */
	public static String generateRegisterCode(String tel) {
		String code = generateCode();
		String result = "您的验证码是 " + code + " (注册验证码)。工作人员不会向您索要,请勿向任何人泄露";
		SpringBeanFactory.getMemcacheClient().set(getREGCode(tel), 180, code);
		return result;
	}
	/**
	 * @Title: generateRegisterCode
	 * @Description: 生成注册验证码并保存memcache
	 * @param tel
	 * @return设定文件
	 * @return String 返回类型
	 */
	public static String generateRealNameCode(String tel) {
		String code = generateCode();
		String result = "您的实名认证验证码是 " + code + " (注册验证码)。工作人员不会向您索要,请勿向任何人泄露";
		SpringBeanFactory.getMemcacheClient().set(getRealNameCode(tel), 180, code);
		return result;
	}
	/**
	 * @Title: generateRegisterCode
	 * @Description: 生成注册验证码并保存memcache
	 * @param tel
	 * @return设定文件
	 * @return String 返回类型
	 */
	public static String generateTradePasswordCode(String tel) {
		String code = generateCode();
		String result = "您的修改交易密码验证码是 " + code + " (注册验证码)。工作人员不会向您索要,请勿向任何人泄露";
		SpringBeanFactory.getMemcacheClient().set(getResetTPCode(tel), 180, code);
		return result;
	}
	/**
	 * 发送 登录验证码
	 * @param tel 手机号码
	 * @return
	 */
	public static int sendPhoneLoginCode(String tel) {
		int result = 0;
		String msg = generatePhoneLoginMsg(tel);
		result = SMSUtil.sendMsg(tel, msg);
		return result;
	}
	/**
	 * @Title: generateLoginMsg
	 * @Description: 登录发送信息，保存验证码
	 * @param tel
	 * @return设定文件
	 * @return String 返回类型
	 */
	public static String generatePhoneLoginMsg(String tel) {
		String code = generateCode();
		String result = "您的手机到登录验证码是 " + code + "(登录验证码)。工作人员不会向您索要,请勿向任何人泄露";
		SpringBeanFactory.getMemcacheClient().set(getPhoneLoginCode(tel), 180, code);
		return result;
	}
	/**
	 * 发送 重置密码验证码
	 * @param tel 手机号码
	 * @return
	 */
	public static int sendRechangePasswordCode(String tel) {
		int result = 0;
		String msg = generateRechangePasswordMsg(tel);
		result = SMSUtil.sendMsg(tel, msg);
		return result;
	}
	/**
	 * @Title: sendRechangePasswordMsg
	 * @Description: 重置密码发送信息，保存验证码
	 * @param tel
	 * @return设定文件
	 * @return String 返回类型
	 */
	public static String generateRechangePasswordMsg(String tel) {
		String code = generateCode();
		String result = "您的验证码是 " + code + "(重置密码验证码)。工作人员不会向您索要,请勿向任何人泄露";
		SpringBeanFactory.getMemcacheClient().set(getRechangePasswordCode(tel), 180, code);
		return result;
	}
	public static String generateBankMsg(String tel) {
		String code = generateCode();
		String result = "您的验证码是 " + code + "(绑定银行卡密码验证码)。工作人员不会向您索要,请勿向任何人泄露";
		SpringBeanFactory.getMemcacheClient().set(getRechangePasswordCode(tel), 180, code);
		return result;
	}
	/**
	 * 发送 修改密码验证码
	 * @param tel 手机号码
	 * @return
	 */
	public static int sendUpdatePasswordCode(String tel) {
		int result = 0;
		String msg = generateUpdatePasswordMsg(tel);
		result = SMSUtil.sendMsg(tel, msg);
		return result;
	}
	/**
	 * @Title: generateUpdatePasswordMsg
	 * @Description: 修改密码发送信息，保存验证码
	 * @param tel
	 * @return设定文件
	 * @return String 返回类型
	 */
	public static String generateUpdatePasswordMsg(String tel) {
		String code = generateCode();
		String result = "您的验证码是 " + code + "(修改密码验证码)。工作人员不会向您索要,请勿向任何人泄露";
		SpringBeanFactory.getMemcacheClient().set(getUpdatePasswordCode(tel), 180, code);
		return result;
	}
	/**
	 * @Title: getPhoneLoginCode
	 * @Description: 手机登录code
	 * @param tel
	 * @return
	 * @return: String
	 */
	public static String getPhoneLoginCode(String tel) {
		// TODO Auto-generated method stub
		return getCode(tel, PHONE_LOGIN);
	}
	public static String getREGCode(String tel) {
		// TODO Auto-generated method stub
		return getCode(tel, REGISTER);
	}
	public static String getResetTPCode(String tel) {
		// TODO Auto-generated method stub
		return getCode(tel, ResetTradePassword);
	}
	public static String getRealNameCode(String tel) {
		// TODO Auto-generated method stub
		return getCode(tel, RealNameCode);
	}
	public static String getRechangePasswordCode(String tel) {
		// TODO Auto-generated method stub
		return getCode(tel, RECHANGE_PASSWORD);
	}
	public static String getUpdatePasswordCode(String tel) {
		// TODO Auto-generated method stub
		return getCode(tel, UPDATE_PASSWORD);
	}
	/**
	 * @Title: getREGCode
	 * @Description: 手机号为唯一标识的验证码
	 * @param tel
	 * @param code
	 * @return
	 * @return: String
	 */
	private static String getCode(String tel, String code) {
		return tel + code;
	}
}
