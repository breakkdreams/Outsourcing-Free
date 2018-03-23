package com.zd.aoding.outsourcing.web.interceptor ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zd.aoding.common.StringDate.DateUtil;

/**
 * @ClassName: PubIntercepter
 * @Description: 用户过滤拦截器
 * @author HCD
 * @date 2016年12月1日 上午10:23:14
 */
public class UserIntercepter implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String uri = request.getRequestURI();
		// if
		// (uri.contains(".web")||uri.contains(".android")||uri.contains(".apple"))
		// {
		System.err.println(DateUtil.getDateTimeST() + " 用户接口:   " + uri);
		// }
		return true;
	}
}
