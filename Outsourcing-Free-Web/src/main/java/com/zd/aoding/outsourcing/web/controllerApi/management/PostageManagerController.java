package com.zd.aoding.outsourcing.web.controllerApi.management;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PostageBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PostageDO;
import com.zd.aoding.outsourcing.weChat.api.facade.PostageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "", description = "邮费管理")
@Controller
@RequestMapping("ad/manager")
public class PostageManagerController {
	@Autowired
	private PostageFacade postageFacade;

	@ResponseBody
	@RequestMapping(value = "postage/edit", method = RequestMethod.POST)
	@ApiOperation(value = "修改邮费", httpMethod = "POST", response = ModelAndView.class)
	public String updatePostage(
			@ApiParam(required = false, name = "postageId", value = "id") @RequestParam(value = "postageId", required = false) String postageId,
			@ApiParam(required = false, name = "money", value = "邮费") @RequestParam(value = "money", required = false) String money,
			HttpServletRequest request) {
		try {
				if (!StringUtil.isNumber(postageId)) {
					return ResponseUtil.showMSGResultString("参数错误");
				}
				PostageDO postageDO = postageFacade.getPostageDOByPK(Integer.parseInt(postageId));
				if(postageDO == null ){
					return ResponseUtil.showMSGResultString("参数错误");
				}
				if (!StringUtil.isNULL(money)) {
					postageDO.setMoney(new BigDecimal(money).doubleValue());
				}
				int i = postageFacade.updatePostageDO(postageDO);
				if (i == 1) {
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}


	@ResponseBody
	@RequestMapping(value = "postage/detail", method = RequestMethod.POST)
	@ApiOperation(value = "查看信息", httpMethod = "POST", response = ModelAndView.class)
	public String postageDetail(
			@ApiParam(required = false, name = "postageId", value = "id") @RequestParam(value = "postageId", required = false)
			String postageId,
			HttpServletRequest request) {
		try {
			if(!StringUtil.isNumber(postageId)){
				return ResponseUtil.showMSGResultString("参数错误");
			}
			PostageBO postageBO = postageFacade.getPostaBOByPK(Integer.parseInt(postageId));
			if(postageBO == null){
				return ResponseUtil.showMSGResultString("未找到");
			}
			return ResponseUtil.successResultString(postageBO);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "postage/allList", method = RequestMethod.POST)
	@ApiOperation(value = "查看所有列表", httpMethod = "POST", response = ModelAndView.class)
	public String postageAllList(
			HttpServletRequest request) {
		try {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("deleted", 0 );
			List<PostageDO> list = postageFacade.getAllPostageList(param);
			if(list != null && list.size()>0){
				return ResponseUtil.successResultString(list);
			}
			return ResponseUtil.showMSGResultString("未找到");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
