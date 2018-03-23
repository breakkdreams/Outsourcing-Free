/**   
 * Copyright © 2017 嘉兴市奥丁网络科技有限公司. All rights reserved.
 * 
 * @Title: ManagerIntercepter.java 
 * @Prject: Outsourcing-WeChat-Web
 * @Package: com.zd.aoding.outsourcing.web.interceptor 
 * @Description: TODO
 * @author: HCD   
 * @date: 2017年12月28日 上午9:58:12 
 * @version: V1.0   
 */
package com.zd.aoding.outsourcing.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

/** 
 * @ClassName: ManagerIntercepter 
 * @Description: 后台管理员拦截接口
 * @author: HCD
 * @date: 2017年12月28日 上午9:58:12  
 */
public class ManagerIntercepter implements HandlerInterceptor{
	@Autowired
	private SessionFacade sessionFacade;

	/* (non Javadoc) 
	 * @Title: afterCompletion
	 * @Description: TODO
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception 
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception) 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non Javadoc) 
	 * @Title: postHandle
	 * @Description: TODO
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView) 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView view)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non Javadoc) 
	 * @Title: preHandle
	 * @Description: TODO
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws Exception 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object) 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String uri = request.getRequestURI();
		AccountBO account = sessionFacade.checkLoginAccountSession(request);
		if (account != null && account.getType() == AccountDO.TYPE_BUSINESS) {
			return true;
		}
		response.sendRedirect("/Outsourcing-Zangcha-Web/login/managerLogin.web");
		return false;
	}
}
