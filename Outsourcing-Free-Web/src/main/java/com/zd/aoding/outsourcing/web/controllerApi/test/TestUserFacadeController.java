//package com.zd.aoding.outsourcing.web.controllerApi.test;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
//
//@Api(value = "", description = "用户信息管理")
//@Controller
//public class TestUserFacadeController {
//
//	@Autowired
//	private UserFacade userFacade;
//
//	@RequestMapping(value = "test/{jspName}", method = RequestMethod.GET)
//	@ApiOperation(value = "hello欢迎")
//	public ModelAndView test(@ApiParam(name = "jspName", value = "页面") @PathVariable(value = "jspName") String jspName,
//			HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("/test/dubbo");
//		//System.out.println(userFacade.getUserByAccountId(2));
//		//System.out.println(userFacade.getUserByUserId(Integer.parseInt(jspName)));
//		return mav;
//	}
//
//	@RequestMapping(value = "test1")
//	@ApiOperation(value = "hello欢迎")
//	public ModelAndView test1(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		//System.out.println(userFacade.getUserByUserId(1));
//		mav.setViewName("/test/dubbo");
//		return mav;
//	}
//
//}
