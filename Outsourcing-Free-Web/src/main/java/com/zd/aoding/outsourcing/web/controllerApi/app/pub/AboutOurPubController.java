package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AboutOurBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AboutOurFacade;

@Api(value = "", description = "查看关于我们")
@Controller
@RequestMapping("pub")
public class AboutOurPubController {
	@Autowired
	private AboutOurFacade aboutOurFacade;

	@ResponseBody
	@RequestMapping(value = "aboutOur/detail", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = GoodsModelDO.class) })
	@ApiOperation(value = "查看关于我们", httpMethod = "POST", notes = "查看关于我们", response = ResponseUtil.class)
	public String getBannerById(
			HttpServletRequest request) {
		try {
			AboutOurBO aboutVo = aboutOurFacade.getAboutOurByAppCode("0");
				if (aboutVo != null) {
					return ResponseUtil.successResultString(aboutVo);
				}
				return ResponseUtil.showMSGResultString("暂无关于我们");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
