//package com.zd.aoding.outsourcing.weChat.provider.services.menmCache;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.zd.aoding.common.log.LogUtil;
//import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
//import com.zd.aoding.outsourcing.weChat.api.services.menmCache.MenmCachePoolService;
//import com.zd.aoding.outsourcing.weChat.api.services.mysql.AccountService;
//import com.zd.aoding.outsourcing.weChat.provider.constant.MemcachedKeyConstant;
//import com.zd.aoding.outsourcing.weChat.provider.constant.SessionConstant;
//import com.zd.aoding.outsourcing.weChat.provider.utils.file.RequestUtil;
//import com.zd.aoding.outsourcing.weChat.provider.utils.file.SpringBeanFactory;
//
//import net.spy.memcached.MemcachedClient;
//
//
///** 
// * @ClassName: MenmCachePoolServiceImpl 
// * @Description: 缓存信息
// * @author: HCD
// * @date: 2017年12月26日 下午2:57:12  
// */
//@Service
//public class MenmCachePoolServiceImpl implements MenmCachePoolService {
//
//	@Autowired
//	private MemcachedClient memcachedClient;
//
//
//
//	@Autowired
//	private AccountService accountService;
//
//	/** 登入保存时间 */
//	public static final Integer MEMCACHE_LOGIN_TIMEOUT = 1800;
//
//	@Override
//	public int saveClientLoginSession(int userType , AccountDO accountDO , HttpServletRequest request) {
//		try {
//			/**
//			 * 缓存中保存登录accountId
//			 */
//			String key_accountId = MemcachedKeyConstant.getLoginKey(accountDO.getUsername(), RequestUtil.getClientIP(request), "accountId");
//			memcachedClient.set(key_accountId, MEMCACHE_LOGIN_TIMEOUT, accountDO.getId());
//			//用户登录
//			request.getSession().setAttribute(SessionConstant.TYPE_N_LOGIN, userType);
//			request.getSession().setAttribute(SessionConstant.ACCOUNT_N_LOGIN, accountDO);
//			return 1;
//			
//		}
//		catch (Exception e) {
//			LogUtil.error("session保存失败", this.getClass());
//			e.printStackTrace();
//			return -1;
//		}
//	}
//
//	@Override
//	public Object checkClientLoginSession(HttpServletRequest request) {
//		String userType = (String) request.getSession().getAttribute(SessionConstant.TYPE_N_LOGIN);
//		Object loginUCP = null;
//		try {
//			String uri = request.getRequestURI();
//			if(uri.endsWith(".android") || uri.endsWith(".apple")) {
//				if(!"".equals(request.getParameter("userName")) || request.getParameter("userName") != null) {
//					String key_accountId = MemcachedKeyConstant.getLoginKey(request.getParameter("userName"), RequestUtil.getClientIP(request), "accountId");
//					Integer accountId = (Integer) SpringBeanFactory.getMemcacheClient().get(key_accountId);
//					if(accountId == null) {
//						loginUCP = request.getSession().getAttribute(String.valueOf(SessionConstant.USER_LOGIN));
//					}
//					else {
//						/*Map<String, Object> map = new HashMap<String, Object>();
//						map.put("accountId", accountId);
//						map.put("isDeleted", 0);
//						loginUCP = usersService.getMapperList(map).get(0);*/
//					}
//				}
//				else {
//					loginUCP = request.getSession().getAttribute(String.valueOf(SessionConstant.USER_LOGIN));
//				}
//				return loginUCP;
//			}
//			else {
//				System.out.println("未保存session:userType=" + userType);
//				LogUtil.info("获取登录session失败", this.getClass());
//				return loginUCP;
//			}
//		}
//		catch (Exception e) {
//			LogUtil.error("获取登录session失败", this.getClass());
//			e.printStackTrace();
//			return loginUCP;
//		}
//	}
//
//	@Override
//	public AccountDO checkClientLoginAccountSession(HttpServletRequest request) {
//		try {
//			AccountDO account = (AccountDO) request.getSession().getAttribute("accountDO");
//			if(request.getSession().getAttribute("accountDO") != null) {
//				if(account.getId() != null) {
//					return account;
//				}
//			}
//			String uri = request.getRequestURI();
//			if(uri.endsWith(".android")) {
//				String userName = request.getParameter("userName");
//				if(userName != null) {
//					String key = MemcachedKeyConstant.getLoginKey(userName, RequestUtil.getClientIP(request), "accountId");
//					if(memcachedClient.get(key) == null) {
//						System.out.println("未登录");
//						return null;
//					}
//					int accountId = (int) memcachedClient.get(key);
//					AccountDO account1 = accountService.get(accountId);
//					request.getSession().setAttribute("accountMapper", account1);
//					return account1;
//				}
//			}
//			return null;
//		}
//		catch (Exception e) {
//			LogUtil.error("获取登录账号id失败", this.getClass());
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	@Override
//	public int removeSessionAndMemory(HttpServletRequest request) {
//		try {
//			/**
//			 * 取消登录类型session（表示退出登录，即未登录）
//			 */
//			request.getSession().removeAttribute("userType");
//			/**
//			 * deleted 缓存中的登录id（表示退出登录）
//			 */
//			String key_accountId = MemcachedKeyConstant.getLoginKey(request.getAttribute("userName") + "", RequestUtil.getClientIP(request), "accountId");
//			memcachedClient.delete(key_accountId);
//			return 1;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
//
//	/*
//	 * (non Javadoc)
//	 * @Title: checkAccountTypeSession
//	 * @Description: 获取 登录类型
//	 * @param request
//	 * @return
//	 * @see
//	 * com.zhixiao.zx.global.servise.MenmCachePoolService#checkAccountTypeSession(javax.servlet.http
//	 * .HttpServletRequest)
//	 */
//	@Override
//	public String checkAccountTypeSession(HttpServletRequest request) {
//		String uri = request.getRequestURI();
//		String accountType = "";
//		System.err.println("用户webUser:" + uri);
//		if(uri.endsWith(".android") || uri.endsWith(".apple")) {
//			// 手机端原生开发 取不到 session
//			String key_accountType = MemcachedKeyConstant.getAccountTypeKey(request.getParameter("userName"), RequestUtil.getClientIP(request), "userType");
//			accountType = (String) SpringBeanFactory.getMemcacheClient().get(key_accountType);
//			if(accountType == null || "".equals(accountType)) {
//				accountType = (String) request.getSession().getAttribute("userType");
//			}
//		}
//		else {
//			accountType = (String) request.getSession().getAttribute("userType");
//		}
//		return accountType;
//	}
//}
