package com.zd.aoding.outsourcing.weChat.api.constant;

/**
 * @ClassName: SessionUtil
 * @Description: session中所有key值和部分value值
 * @author zhangj
 * @date 2016年12月1日 下午1:23:27
 */
public class SessionConstant {
	/**** session key(name) ***************************************************/
	/**
	 * @Fields LOGIN_TYPE :登录账号
	 */
	public static final String LOGIN_ACCOUNT = "accountVo";
	/**
	 * @fieldName: LOGIN_ACCOUNT_RIGHT
	 * @fieldType: String
	 * @Description: 账号权限
	 */
	public static final String LOGIN_ACCOUNT_RIGHT = "accountRole";
	/**
	 * @fieldName: LOGIN_USER
	 * @fieldType: String
	 * @Description:普通用户 登入信息
	 */
	public static final String LOGIN_TYPE_USER = "userVo";
	/**
	 * @fieldName: LOGIN_TYPE_SUPPLIER
	 * @fieldType: String
	 * @Description: 代理商权限
	 */
	public static final String LOGIN_TYPE_SUPPLIER = "supplierVo";
	/**
	 * @fieldName: LOGIN_TYPE_APPCOMPANY
	 * @fieldType: String
	 * @Description: 代理商权限AppCompany
	 */
	public static final String LOGIN_TYPE_APPCOMPANY = "AppCompanyVo";
	/**
	 * @fieldName: LOGIN_MANANGER
	 * @fieldType: String
	 * @Description: 管理员用户登录信息
	 */
	public static final String LOGIN_TYPE_MANANGER = "managerUserVo";
	/**
	 * @fieldName: LOGIN_TYPE_DEALER
	 * @fieldType: String
	 * @Description: 商户登录信息
	 */
	public static final String LOGIN_TYPE_DEALER = "dealerVo";
	/**
	 * @fieldName: LOGIN_TYPE_MANANGER_RIGHT
	 * @fieldType: String
	 * @Description: 管理员角色
	 */
	// public static final String LOGIN_TYPE_MANANGER_ROLE = "role";
	/**
	 * @fieldName: OPTION
	 * @fieldType: String
	 */
	public static final String OPTION = "option";
	public static final String MODULE = "module";
	

}
