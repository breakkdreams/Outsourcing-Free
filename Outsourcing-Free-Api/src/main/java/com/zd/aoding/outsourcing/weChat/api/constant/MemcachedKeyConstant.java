package com.zd.aoding.outsourcing.weChat.api.constant;


/** 
 * @ClassName: MemcachedKeyConstant 
 * @Description: 缓存工具类
 * @author: HCD
 * @date: 2017年12月26日 下午2:31:54  
 */
public class MemcachedKeyConstant {
	


	/** 
	 * @Title: getLoginKey 
	 * @Description: user用户缓存king
	 *  @param userName
	 *  @param ip
	 *  @param code
	 *  @return设定文件 
	 * @return String    返回类型 
	
	 */
	public static String getLoginKey(String userName, String ip, String code) {
		return "check_login_" + userName + "_" + ip + "_" + code;
	}
	
	/** 
	 * @Title: YanZhengMaKey
	 * @Description: user用户缓存king
	 *  @param userName
	 *  @param ip
	 *  @param code
	 *  @return设定文件 
	 * @return String    返回类型 
	
	 */
	public static String regYanZhengMaKey( String tel) {
		return "zhuceTrends_code_" + tel;
	}
	/** 
	 * @Title: loginYanZhengMaKey
	 * @Description: user用户缓存king
	 *  @param 登录验证码
	 *  @param code
	 *  @return设定文件 
	 * @return String    返回类型 
	
	 */
	public static String loginYanZhengMaKey(String tel) {
		return "loginTrends_code_" + tel;
	}
	/** 
	 * @Title: ZhaoHuiMiMaMsgKey  找回密码
	 * @Description: user用户缓存king
	 *  @param userName
	 *  @param ip
	 *  @param code
	 *  @return设定文件 
	 * @return String    返回类型 
	
	 */
	public static String ZhaoHuiMiMaMsgKey( String tel) {
		return "ZhaoHuiMiMa_code_" + tel;
	}

	/** 
	 * @Title: updatePhoneYanZhengMaKey
	 * @Description: user用户缓存king
	 *  @param 修改手机号验证码
	 *  @param code
	 *  @return设定文件 
	 * @return String    返回类型 
	
	 */
	public static String updatePhoneYanZhengMaKey(String tel) {
		return "updatePhone_code_" + tel;
	}
	/** 
	 * @Title: supplierYanZhengMaKey
	 * @Description: user用户缓存king
	 *  @param 申请供货商验证码
	 *  @param code
	 *  @return设定文件 
	 * @return String    返回类型 
	
	 */
	public static String supplierYanZhengMaKey(String tel) {
		return "shenQinSupplier_code_" + tel;
	}
	/** 
	 * @Title: jiangShiYanZhengMaKey
	 * @Description: user用户缓存king
	 *  @param 申请讲师验证码
	 *  @param code
	 *  @return设定文件 
	 * @return String    返回类型 
	
	 */
	public static String jiangShiYanZhengMaKey(String tel) {
		return "shenQinjiangShi_code_" + tel;
	}
	
	/** 
	 * @Title: getAccountTypeKey 
	 * @Description: 缓存登录类型
	 * @param userName
	 * @param ip
	 * @param code
	 * @return
	 * @return: String
	 */
	public static String getAccountTypeKey(String userName, String ip, String code) {
		return "check_accountType_" + userName + "_" + ip + "_" + code;
	}
	
	
	/** 
	 * @Title: getCompanyKey 
	 * @Description: 缓存企业信息
	 * @param userName
	 * @param ip
	 * @param code
	 * @return
	 * @return: String
	 */
	public static String getCompanyKey(String userName, String ip, String code) {
		return "check_company_" + userName + "_" + ip + "_" + code;
	}
	
	/** 
	 * @Title: getSupplierMapperKey 
	 * @Description: 缓存供货商信息
	 * @param userName
	 * @param ip
	 * @param code
	 * @return
	 * @return: String
	 */
	public static String getSupplierMapperKey(String userName, String ip, String code) {
		return "check_supplier_" + userName + "_" + ip + "_" + code;
	}
	
	/** 
	 * @Title: getUserMapperKey 
	 * @Description: 缓存用户信息
	 * @param userName
	 * @param ip
	 * @param code
	 * @return
	 * @return: String
	 */
	public static String getUserMapperKey(String userName, String ip, String code) {
		return "check_user_" + userName + "_" + ip + "_" + code;
	}
	
	/** 
	 * @Title: projectTokenKey 
	 * @Description: 使用项目的标示
	 * @param token
	 * @return: String
	 */
	public static String projectTokenKey(String token) {
		return "project_token_key" + token;
	}
	
}
