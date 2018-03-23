/**   
 * Copyright © 2017 嘉兴市奥丁网络科技有限公司. All rights reserved.
 * @Title: BasePageController.java 
 * @Prject: Outsourcing-WeChat-Web
 * @Package: com.zd.aoding.outsourcing.web.controllerApi.management 
 * @Description: TODO
 * @author: HCD   
 * @date: 2017年12月28日 上午9:10:49 
 * @version: V1.0   
 */
package com.zd.aoding.outsourcing.web.controllerApi.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/** 
 * @ClassName: BasePageController 
 * @Description: 后台管理 页面跳转控制层
 * @author: HCD
 * @date: 2017年12月28日 上午9:10:49  
 */
@Controller
@Api(value="",description="后台管理页面控制")
@RequestMapping("admin/")
public class BasePageController {
	
	
	/** 
	 * @Title: BasePage 
	 * @Description: 所有基础页面之间跳转
	 * @author: HCD
	 * @param jspName
	 * @param request
	 * @return 页面
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "pub/{jspName}", method = RequestMethod.GET)
	@ApiOperation(value = "跳转页面控制")
	public ModelAndView BasePage(@ApiParam(name = "jspName", value = "页面名称") @PathVariable(value = "jspName") String jspName,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("management/"+jspName);
		return mav;
	}
	
}
