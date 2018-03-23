package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigTestPrarm;
import com.zd.aoding.outsourcing.weChat.api.constant.MemcachedConstant;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.RequestUtil;

import net.spy.memcached.MemcachedClient;

@Service
public class SessionFacadeImpl implements SessionFacade {
	@Autowired
	private MemcachedClient memcachedClient;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private UserFacade userFacade;

	@Override
	public int saveLoginSession(AccountBO accountVo, String sessionType, Object loginSession,
			HttpServletRequest request) {
		try {
			String uri = request.getRequestURI();
			if (accountVo == null) {
				LogUtil.operationRecord("账号为null；accountVo==" + accountVo);
				return 0;
			}
			request.getSession(true).setAttribute(SessionConstant.LOGIN_ACCOUNT, accountVo);
			switch (sessionType) {
			case SessionConstant.LOGIN_TYPE_USER:
				request.getSession(true).setAttribute(SessionConstant.LOGIN_TYPE_USER, loginSession);
				break;
			case SessionConstant.LOGIN_TYPE_MANANGER:
				request.getSession(true).setAttribute(SessionConstant.LOGIN_TYPE_MANANGER, loginSession);
				break;
			default:
				break;
			}
			String key = MemcachedConstant.getLoginKey(accountVo.getUsername(), RequestUtil.getClientIP(request),
					MemcachedConstant.PHONE_LOGIN);
			memcachedClient.set(key, MemcachedConstant.MEMCACHE_LOGIN_TIMEOUT, accountVo.getAccountId());
			return 1;
		} catch (Exception e) {
			LogUtil.operationError("保存session失败", this.getClass(), e);
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public AccountBO checkLoginAccountSession(HttpServletRequest request) {
		try {
			String uri = request.getRequestURI();
			if (request.getSession(true).getAttribute(SessionConstant.LOGIN_ACCOUNT) != null) {
				return (AccountBO) request.getSession(true).getAttribute(SessionConstant.LOGIN_ACCOUNT);
			}
			/**
			 * 安卓端
			 */
			if (uri.endsWith(".android")) {
				String username = request.getParameter(MemcachedConstant.PHONE_USERNAME);
				if (username != null) {
					String key = MemcachedConstant.getLoginKey(username, RequestUtil.getClientIP(request),
							MemcachedConstant.PHONE_LOGIN);
					if (memcachedClient.get(key) == null) {
						return null;
					}
					int accountId = (int) memcachedClient.get(key);
					AccountDO account = accountFacade.getPoByPK(accountId);
					AccountBO accountBO = new AccountBO(account);
					return accountBO;
				}
			}
			if (!Config.ENVIRONMENT_PRO.equals(Config.ENVIRONMENT)) {
				String phone = request.getParameter(ConfigTestPrarm.testUsername);
				if (!StringUtil.isNULL(phone)) {
					System.err.println("跨域测试");
					 return new AccountBO(accountFacade.getUserAccountPoByPhone(phone));
				}
				String ip = request.getHeader("x-forwarded-for");
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("WL-Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
				}
				if (("127.0.0.1").equals(ip)) {
//					System.out.println("测试固定accountId==1");
					// return accountFacade.getAccountVoByPK(Config.AdminId);
				}
//				System.out.println(ip);
			}
			return null;
		} catch (Exception e) {
			LogUtil.operationError("获取登录账号id失败", this.getClass(), e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object checkLoginSession(HttpServletRequest request, String sessionType) {
		try {
			if (!Config.ENVIRONMENT_PRO.equals(Config.ENVIRONMENT)) {
				String testUsername = request.getParameter(ConfigTestPrarm.testUsername);
				if (!StringUtil.isNULL(testUsername)) {
					System.err.println("跨域测试");
					return userFacade.getUserByAccountId(accountFacade.getUserAccountPoByPhone(testUsername).getId());
				}
			}
			String uri = request.getRequestURI();
			/**
			 * 安卓端
			 */
			if (uri.endsWith(".android")) {
				String username = request.getParameter(MemcachedConstant.PHONE_USERNAME);
				if (username != null) {
					String key = MemcachedConstant.getLoginKey(username, RequestUtil.getClientIP(request),
							MemcachedConstant.PHONE_LOGIN);
					if (memcachedClient.get(key) != null) {
						Integer accountId = (Integer) memcachedClient.get(key);
						switch (sessionType) {
							case SessionConstant.LOGIN_TYPE_USER:
								UserBO userVo = userFacade.getUserByAccountId(accountId);
								return userVo;
							default:
								return null;
						}
					}
				}
			}
			switch (sessionType) {
			case SessionConstant.LOGIN_TYPE_USER:
				return request.getSession(true).getAttribute(SessionConstant.LOGIN_TYPE_USER);
			case SessionConstant.LOGIN_TYPE_MANANGER:
				return request.getSession(true).getAttribute(SessionConstant.LOGIN_TYPE_MANANGER);
			default:
				return null;
			}
		} catch (Exception e) {
			LogUtil.operationError("保存session失败", this.getClass(), e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int removeSessionAndMemcache(HttpServletRequest request) {
		try {
			// String uri = request.getRequestURI();
			/**
			 * 退出账号（表示退出登录，即未登录），一般表示网页注销
			 */
			request.getSession(true).removeAttribute(SessionConstant.LOGIN_ACCOUNT);
			request.getSession(true).removeAttribute(SessionConstant.LOGIN_TYPE_USER);
			request.getSession(true).removeAttribute(SessionConstant.LOGIN_TYPE_MANANGER);
			request.getSession(true).removeAttribute(SessionConstant.LOGIN_TYPE_DEALER);
			request.getSession(true).removeAttribute(SessionConstant.LOGIN_TYPE_APPCOMPANY);
			/**
			 * 删除缓存中的登录id（退出登录），一般表示手机注销
			 */
			String key_accountId = MemcachedConstant.getLoginKey(
					(String) request.getParameter(MemcachedConstant.PHONE_USERNAME), RequestUtil.getClientIP(request),
					MemcachedConstant.PHONE_LOGIN);

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public String checkMemcache(String MemcacheKey) {
		return (String) memcachedClient.get(MemcacheKey);
	}

//	@Override
//	public String saveSystemParamMemcache(String MemcacheKey, Integer value) {
//		memcachedClient.set(MemcacheKey, MemcachedConstant.MEMCACHE_SYSTEMPARAM_TIMEOUT, value);
//		return null;
//	}
//	@Override
//	public Integer checkSystemParamMemcache(String MemcacheKey) {
//		return (Integer) memcachedClient.get(MemcacheKey);
//	}
}
