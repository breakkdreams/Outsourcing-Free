package com.zd.aoding.outsourcing.weChat.api.constant;

/**
 * @ClassName: MemcachedKeyUtil
 * @Description: menmcache缓存器存储常
 * @author: zj
 * @date: 2017年3月8日 下午2:44:31
 */
public class MemcachedConstant {
	/** 登入保存时间 */
	public static final Integer MEMCACHE_LOGIN_TIMEOUT = 1800;
	/** 系统参数保存时间 */
	public static final Integer MEMCACHE_SYSTEMPARAM_TIMEOUT = 86400;
	/**
	 * @fieldName: LOGIN_MANANGER
	 * @fieldType: String
	 * @Description: 短信校验码前缀
	 */
	public static final String MSM_CODE = "MSM_CODE";
	public static final String PHONE_LOGIN = "accountId";
	public static final String PHONE_USERNAME = "username";

	/**
	 * @Title: getLoginKey
	 * @Description: user用户缓存king
	 * @param userName
	 * @param ip
	 * @param code
	 * @return设定文件
	 * @return String 返回类型
	 */
	public static String getLoginKey(String userName, String ip, String code) {
		return "check_login_" + userName + "_" + ip + "_" + code;
	}

}
