package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "", description = "基础界面")
@Controller
public class BasePubController {
	@RequestMapping(value = "pub/baseBlock/showMsg", method = RequestMethod.GET)
	@ApiOperation(value = "所有登录界面", httpMethod = "GET", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	public ModelAndView loginJsp(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("baseBlock/showMsg");
		return mav;
	}
}
