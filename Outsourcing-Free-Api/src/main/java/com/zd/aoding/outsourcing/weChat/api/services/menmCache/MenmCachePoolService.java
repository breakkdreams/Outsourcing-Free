package com.zd.aoding.outsourcing.weChat.api.services.menmCache;

import javax.servlet.http.HttpServletRequest;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;

/** 
 * @ClassName: MenmCachePoolService 
 * @Description: session保存或 memcachedClient保存
 * @author: HCD
 * @date: 2017年12月26日 下午2:24:29  
 */
public interface MenmCachePoolService {
	/**
	 * @Title: saveClientLoginSession
	 * @Description: 保存登录对象
	 * @param userType 账号类型: 用户类型：1 普通，2：后台普通管理员，3：后台超级管理
	 * @param accountDO 登录账号
	 * @param request设定文件
	 * @return int 返回类型 1保存成功
	 */
	public abstract int saveClientLoginSession(int accountType, AccountDO accountDO, HttpServletRequest request);
	/**
	 * @Title: checkClientLoginSession
	 * @Description: 检查登录对象
	 * @param request设定文件
	 * @return void 返回类型
	 */
	public abstract Object checkClientLoginSession(HttpServletRequest request);
	/**
	 * @Title: checkClientLoginAccountSession
	 * @Description: 检查登录账号
	 * @param accountType
	 * @param request
	 * @return设定文件
	 * @return AccountMapper 返回类型
	 */
	public abstract AccountDO checkClientLoginAccountSession(HttpServletRequest request);
	/**
	 * @Title: removeSessionAndMemory
	 * @Description:登录退出， 清除session和緩存
	 * @param request
	 * @return
	 * @return: int
	 */
	public abstract int removeSessionAndMemory(HttpServletRequest request);
	
	/** 
	 * @Title: checkAccountTypeSession 
	 * @Description: 获取登录账号 类型 
	 * @param Code
	 * @return: String
	 */
	public abstract String checkAccountTypeSession(HttpServletRequest request);
}
