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
public class DistributorNoticeManagerController {
	@Autowired
	private NoticeFacade noticeFacade;
	@Autowired
	private HttpSession session;


	@ResponseBody
	@RequestMapping(value = "notice/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加系统推送", httpMethod = "POST", notes = "查看系统推送", response = ResponseUtil.class)
	public String addPush(
			@ApiParam(required = true, name = "title", value = "标题") @RequestParam(value = "title", required = true) String title,
			@ApiParam(required = true, name = "content", value = "内容") @RequestParam(value = "content", required = true) String content,
			@ApiParam(required = true, name = "source", value = "来源") @RequestParam(value = "source", required = true) String source,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "信息推送操作");
			if(rv.getRoleFalg().equals("1")) {
				NoticeDO noticePo = new NoticeDO();
				noticePo.setTitle(title);
				noticePo.setContent(content);
				noticePo.setAppCode("0");
				noticePo.setType(NoticeDO.type_system);
				int i = noticeFacade.insertNoticePo(noticePo);
				if(i == 1){
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "distributorNotice/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加经销商推送", httpMethod = "POST", notes = "查看经销商推送", response = ResponseUtil.class)
	public String addDistributorPush(
			@ApiParam(required = true, name = "title", value = "标题") @RequestParam(value = "title", required = true) String title,
			@ApiParam(required = true, name = "content", value = "内容") @RequestParam(value = "content", required = true) String content,
			@ApiParam(required = true, name = "source", value = "来源") @RequestParam(value = "source", required = true) String source,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "经销商公告管理添加");
			if(rv.getRoleFalg().equals("1")) {
				NoticeDO noticePo = new NoticeDO();
				noticePo.setTitle(title);
				noticePo.setContent(content);
				noticePo.setAppCode("0");
				noticePo.setType(NoticeDO.type_distributor);
				int i = noticeFacade.insertNoticePo(noticePo);
				if(i == 1){
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "businessNotice/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加商家推送", httpMethod = "POST", notes = "查看商家推送", response = ResponseUtil.class)
	public String addBusinessPush(
			@ApiParam(required = true, name = "title", value = "标题") @RequestParam(value = "title", required = true) String title,
			@ApiParam(required = true, name = "content", value = "内容") @RequestParam(value = "content", required = true) String content,
			@ApiParam(required = true, name = "source", value = "来源") @RequestParam(value = "source", required = true) String source,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家公告管理添加");
			if(rv.getRoleFalg().equals("1")) {
				NoticeDO noticePo = new NoticeDO();
				noticePo.setTitle(title);
				noticePo.setContent(content);
				noticePo.setAppCode("0");
				noticePo.setType(NoticeDO.type_business);
				int i = noticeFacade.insertNoticePo(noticePo);
				if(i == 1){
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "notice/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看系统推送", httpMethod = "POST", notes = "查看系统推送", response = ResponseUtil.class)
	public Map<String, Object> getScoreRecord(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "信息推送查看");
			if(rv.getRoleFalg().equals("1")) {
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
				param.put("type", NoticeDO.type_system);
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
			}else{
				return ResponseUtil.showMSGResultMap("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}

	@ResponseBody
	@RequestMapping(value = "distributorNotice/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看经销商推送", httpMethod = "POST", notes = "查看经销商推送", response = ResponseUtil.class)
	public Map<String, Object> getDistributorNotice(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "经销商公告管理查看");
			if(rv.getRoleFalg().equals("1")) {
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
				param.put("type", NoticeDO.type_distributor);
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
			}else{
				return ResponseUtil.showMSGResultMap("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}

	@ResponseBody
	@RequestMapping(value = "businessNotice/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看商家推送", httpMethod = "POST", notes = "查看商家推送", response = ResponseUtil.class)
	public Map<String, Object> getBusinessNotice(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家公告管理查看");
			if(rv.getRoleFalg().equals("1")) {
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
			}else{
				return ResponseUtil.showMSGResultMap("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}
//	@ResponseBody
//	@RequestMapping(value = "notice/deleted", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "删除系统推送", httpMethod = "POST", notes = "删除系统推送", response = ResponseUtil.class)
//	public String deleted(
//			@ApiParam(required = true, name = "pushId", value = "id") @RequestParam(value = "pushId", required = true) String pushId,
//			HttpServletRequest request) {
//		try {
//			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
//			RoleBO rv = new RoleBO(sess, "信息推送操作");
//			if(rv.getRoleFalg().equals("1")) {
//				if(!StringUtil.isNumber(pushId)){
//					return ResponseUtil.resultString("id错误", ResponseCodeEnum.PARAM_ERROR);
//				}
//				int i = pushFacade.deletePush(Integer.parseInt(pushId));
//				if(i == 1){
//					return ResponseUtil.successResultString("删除成功");
//				}
//				return ResponseUtil.resultString("删除失败", ResponseCodeEnum.ERROR);
//			}else{
//				return ResponseUtil.resultString("暂无权限",ResponseCodeEnum.ERROR);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
}
