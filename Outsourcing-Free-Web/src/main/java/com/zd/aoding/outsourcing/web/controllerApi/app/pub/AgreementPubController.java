package com.zd.aoding.outsourcing.web.controllerApi.app.pub;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AgreementBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AgreementDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AgreementFacade;

@Api(value = "", description = "协议说明管理")
@Controller
@RequestMapping("pub")
public class AgreementPubController {
	@Autowired
	private AgreementFacade agreementFacade;

	/**
	 * 前台接口传type得到相应的内容
	 * @param reques
	 * @param session
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "agreement/detail", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = AgreementDO.class) })
	@ApiOperation(value = "协议说明前台接口传ID") 
	public String getAgreement(
			@ApiParam(required = true, name = "type", value = "协议type(5.钱包说明,6提现)") @RequestParam(value = "type", required = true) String type
			){
				try {
				if(StringUtil.isNumber(type)){
				 AgreementBO apormbp =  agreementFacade.getAgreementVoByPK(Integer.parseInt(type));
				 if(apormbp!=null){
					 return ResponseUtil.successResultString(apormbp);
				 }
				 return ResponseUtil.showMSGResultString("暂无数据");
				}
				 return ResponseUtil.showMSGResultString("暂无数据");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseUtil.systemErrorResultString(); 
				}
	}
}
