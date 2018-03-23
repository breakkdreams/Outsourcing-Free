package com.zd.aoding.outsourcing.web.controllerApi.app.pub;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.facade.VersionFacade;


/** 
 * @ClassName: VersionController 
 * @Description: 版本号
 * @author: HCD
 * @date: 2017年2月15日 下午5:27:56  
 */
@Controller
@RequestMapping("ad/version")
public class VersionController {

	@Autowired
	private VersionFacade versionFacade;
	
	
	@ResponseBody
	@RequestMapping(value = "/fineVersion", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	public String fineVersion(HttpServletRequest request,
	        @ApiParam(required = false, name = "versionNum", value = "版本号") @RequestParam(value = "versionNum", required = false) String versionNum) {
		try {
			if("".equals(request.getParameter("versionNum")) || request.getParameter("versionNum") == null){
				return ResponseUtil.resultString("请输入版本号!", ResponseCodeEnum.PARAM_ERROR);
			}
			String vNum = request.getParameter("versionNum");
			String result = versionFacade.fineVersion(request,versionNum);
			return result;

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseUtil.resultString(null, ResponseCodeEnum.SYSTEM_ERROR);
			}
		
		
	}
	
}
