package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BusinessTypeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BusinessTypeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BusinessTypeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BusinessTypeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "商家类型")
@Controller
@RequestMapping("ad/manager")
public class BusinessTypeManagerController {
	@Autowired
	private BusinessTypeFacade businessTypeFacade;
	@Autowired
	private HttpSession session;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private RecordFacade recordFacade;
	
	@ResponseBody
	@RequestMapping(value = "businessType/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加商家类型", httpMethod = "POST", notes = "添加商家类型", response = ResponseUtil.class)
	public String addBusinessType(
			@ApiParam(name = "typeName", value = "名称", required = false) @RequestParam(value = "typeName", required = false)
			String typeName,
//			@ApiParam(name = "sort", value = "排序值", required = false) @RequestParam(value = "sort", required = false)
//			String sort,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家类型管理添加");
			if(rv.getRoleFalg().equals("1")) {
//				if(!StringUtil.isNumber(sort)){
//					return ResponseUtil.showMSGResultString("排序值错误");
//				}
				if(StringUtil.isNULL(typeName)){
					return ResponseUtil.showMSGResultString("类型名称不能为空");
				}
//				Map<String, Object> param = new HashMap<String, Object>();
//				param.put("deleted", 0);
//				param.put("sort",sort);
//				int resu = businessTypeFacade.countBusinessType(param);
//				if(resu>0){
//					return ResponseUtil.showMSGResultString("该序号已存在");
//				}
				BusinessTypeDO businessTypePo = new BusinessTypeDO(typeName,0);
				int i = businessTypeFacade.insertBusinessTypePo(businessTypePo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加商家类型成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),businessTypePo.getId(), "添加商家类型", "");
					recordFacade.insertRecordDO(recordDO);
					
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "businessType/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改商家类型", httpMethod = "POST", notes = "修改商家类型", response = ResponseUtil.class)
	public String editBusinessType(
			@ApiParam(name = "businessTypeId", value = "id", required = false) @RequestParam(value = "businessTypeId", required = true)
			String businessTypeId,
			@ApiParam(name = "typeName", value = "类型名称", required = false) @RequestParam(value = "typeName", required = false)
			String typeName,
//			@ApiParam(name = "sort", value = "排序", required = false) @RequestParam(value = "sort", required = false)
//			String sort,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家类型管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(businessTypeId)) {
					return ResponseUtil.showMSGResultString("类型id错误");
				}
//				if (!StringUtil.isNumber(sort)) {
//					return ResponseUtil.showMSGResultString("排序值错误");
//				}
				if (StringUtil.isNULL(typeName)) {
					return ResponseUtil.showMSGResultString("类型名称不能为空");
				}
				BusinessTypeDO businessTypePo = businessTypeFacade.getBusinessTypePoByPK(businessTypeId);
				if (businessTypePo == null) {
					return ResponseUtil.showMSGResultString("未找到该类型");
				}
				businessTypePo.setTypeName(typeName);

				int i = businessTypeFacade.updateBusinessTypePo(businessTypePo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改商家类型成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(businessTypeId), "修改商家类型", "");
					recordFacade.insertRecordDO(recordDO);
					
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "businessType/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除商家类型", httpMethod = "POST", notes = "删除商家类型", response = ResponseUtil.class)
	public String deletedBusinessType(
			@ApiParam(required = true, name = "businessTypeId", value = "id") @RequestParam(value = "businessTypeId", required = true)
					String businessTypeId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家类型管理删除");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNULL(businessTypeId) && StringUtil.isNumber(businessTypeId)) {
					BusinessTypeDO businessTypePo = businessTypeFacade.getBusinessTypePoByPK(businessTypeId);
					if (businessTypePo == null) {
						return ResponseUtil.showMSGResultString("未找到该类型");
					}
					businessTypePo.setDeleted(1);
					int i = businessTypeFacade.updateBusinessTypePo(businessTypePo);
					if (i == 1) {
						//操作日志
						AccountBO account = sessionFacade.checkLoginAccountSession(request);
						RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除商家类型成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
							RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(businessTypeId), "删除商家类型", "");
						recordFacade.insertRecordDO(recordDO);
						
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.showMSGResultString("缺少需要删除的businessTypeId=" + businessTypeId);
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "businessType/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看商家类型", httpMethod = "POST", notes = "查看商家类型", response = ResponseUtil.class)
	public Map<String, Object> getBusinessType(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家类型管理查看");
			if(rv.getRoleFalg().equals("1")) {
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
				pageEntity.setOrderColumn("id");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<BusinessTypeBO> pageResult = businessTypeFacade.getPageBusinessTypeVo(pageEntity);
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
	@RequestMapping(value = "businessType/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个商家类型", httpMethod = "POST", notes = "查看单个商家类型", response = ResponseUtil.class)
	public String getMapper(
			@ApiParam(required = false, name = "businessTypeId", value = "id") @RequestParam(value = "businessTypeId", required = false)
					String businessTypeId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(businessTypeId) && StringUtil.isNumber(businessTypeId)) {
				BusinessTypeDO businessTypePo = businessTypeFacade.getBusinessTypePoByPK(businessTypeId);
				if (businessTypePo != null) {
					return ResponseUtil.successResultString(businessTypePo);
				}
				return ResponseUtil.showMSGResultString("未找到该商家类型");
			}
			return ResponseUtil.showMSGResultString("分类id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "businessType/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询全部类型", httpMethod = "POST", notes = "查询全部类型", response = ResponseUtil.class)
	public String getAllList(
			HttpServletRequest request) {
		try {
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			/**
			 * 查询
			 */
			List<BusinessTypeDO> categoryList = businessTypeFacade.getBusinessTypePoList(param);
			return ResponseUtil.successResultString(categoryList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
