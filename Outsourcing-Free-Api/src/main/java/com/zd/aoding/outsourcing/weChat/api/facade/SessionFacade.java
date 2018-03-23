package com.zd.aoding.outsourcing.weChat.api.facade;

import javax.servlet.http.HttpServletRequest;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;

/**
 * @ClassName: SessionFacade
 * @Description: session保存或 memcachedClient保存
 * @author zhangj
 * @date 2016年8月16日 下午7:35:33
 */
public interface SessionFacade {
	/**
	 * @Title: saveClientLoginSession
	 * @Description: 保存登录信息
	 * @param accountMapper
	 * @param request
	 * @return
	 * @return: int
	 */
	public abstract int saveLoginSession(AccountBO accountMapper, String loginType, Object loginSession,
			HttpServletRequest request);

	/**
	 * @Title: checkClientLoginSession
	 * @Description: 检查登录对象
	 * @param request设定文件
	 * @return void 返回类型
	 */
	public abstract Object checkLoginSession(HttpServletRequest request, String loginType);
	/**
	 * @Title: saveMemcache
	 * @Description: 保存到memcache
	 * @param MSM_CODE
	 * @return
	 * @return: String
	 */
//	public abstract String saveSystemParamMemcache(String MSM_CODE, Integer value);
	/**
	 * @Title: checkMemcache
	 * @Description: 检查memcache保存值
	 * @param MemcacheKey
	 * @return
	 * @return: Integer
	 */
//	public abstract Integer checkSystemParamMemcache(String MemcacheKey);

	/**
	 * @Title: checkClientLoginAccountSession
	 * @Description: 检查登录账号
	 * @param accountType
	 * @param request
	 * @return设定文件
	 * @return AccountMapper 返回类型
	 */
	public abstract AccountBO checkLoginAccountSession(HttpServletRequest request);

	/**
	 * @Title: removeSessionAndMemory
	 * @Description:登录退出， 清除session和緩存
	 * @param request
	 * @return
	 * @return: int
	 */
	public abstract int removeSessionAndMemcache(HttpServletRequest request);
	
	public abstract String checkMemcache(String MemcacheKey);

}
