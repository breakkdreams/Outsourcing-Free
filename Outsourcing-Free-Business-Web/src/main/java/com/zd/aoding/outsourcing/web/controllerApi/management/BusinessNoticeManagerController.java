package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.NoticeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.NoticeDO;
import com.zd.aoding.outsourcing.weChat.api.facade.NoticeFacade;

@Api(value = "", description = "系统推送")
@Controller
@RequestMapping("ad/manager")
public class BusinessNoticeManagerController {
	@Autowired
	private NoticeFacade noticeFacade;


	@ResponseBody
	@RequestMapping(value = "businessNotice/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看商家推送", httpMethod = "POST", notes = "查看商家推送", response = ResponseUtil.class)
	public Map<String, Object> getBusinessNotice(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			String sSearch = request.getParameter("sSearch");
			// 初始化列表
			PageEntity pageEntity = new PageEntity();
			if (!StringUtil.isNULL(iDisplayStart) && Integer.valueOf(iDisplayStart) > 0) {
				pageEntity.setPage(Integer.valueOf(iDisplayStart) / Integer.valueOf(iDisplayLength) + 1);
			}
			if (!StringUtil.isNULL(iDisplayLength)) {
				pageEntity.setSize(Integer.valueOf(iDisplayLength));
			}
			/**
			 * 排序
			 */
			pageEntity.setOrderColumn("create_time");
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("type", NoticeDO.type_business);
			if(!StringUtil.isNULL(sSearch)){
				param.put("titleLike", sSearch);
			}
			pageEntity.setParams(param);
			/**
			 * 查询
			 */
			PageResult<NoticeBO> pageResult = noticeFacade.getPageNoticeVo(pageEntity);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
			JSONArray jsonArray = JSON.parseArray(jsonString);
			resultMap.put("iTotalRecords", pageResult.getTotalSize());
			resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
			resultMap.put("aaData", jsonArray);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}
}
