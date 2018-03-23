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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "分类")
@Controller
@RequestMapping("ad/manager")
public class CategoryManagerController {
	@Autowired
	private CategoryFacade categoryFacade;
	@Autowired
	private HttpSession session;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;

	/**
	 * @Title: addCategory 
	 * @Description: 添加分类
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "category/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加分类", httpMethod = "POST", notes = "添加分类", response = ResponseUtil.class)
	public String addCategory(
			@ApiParam(name = "logoImgUrl", value = "分类Logo图", required = false) @RequestParam(value = "logoImgUrl", required = false) 
			String logoImgUrl,
			@ApiParam(name = "name", value = "分类名字", required = false) @RequestParam(value = "name", required = false) 
			String name,
			@ApiParam(name = "parentCategoryId", value = "上级分类id", required = false) @RequestParam(value = "parentCategoryId", required = false) 
			String parentCategoryId,
			@ApiParam(name = "lever", value = "层级", required = false) @RequestParam(value = "lever", required = false) 
			String lever,
			@ApiParam(name = "sort", value = "排序", required = false) @RequestParam(value = "sort", required = false) String sort,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "分类管理添加");
			if(rv.getRoleFalg().equals("1")) {
				if(StringUtil.isNULL(parentCategoryId) && !StringUtil.isNumber(parentCategoryId)){
					CategoryDO categoryPoByPK = categoryFacade.getCategoryPoByPK(parentCategoryId);
					if(categoryPoByPK == null ){
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(!StringUtil.isNumber(lever)){
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				CategoryDO categoryPo = new CategoryDO(logoImgUrl,name,Integer.parseInt(parentCategoryId),Integer.parseInt(lever));
				if(StringUtil.isNumber(sort)){
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("deleted", 0);
					param.put("sort", sort);
					int j = categoryFacade.countCategoryPo(param);
					if(j>0){
						return ResponseUtil.showMSGResultString("该序号已存在");
					}
					categoryPo.setSort(Integer.parseInt(sort));
				}
				int i = categoryFacade.insertCategoryPo(categoryPo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), categoryPo.getId(), "添加分类", "");
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
	@RequestMapping(value = "category/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改分类", httpMethod = "POST", notes = "修改分类", response = ResponseUtil.class)
	public String editCategory(
			@ApiParam(name = "logoImgUrl", value = "分类Logo图", required = false) @RequestParam(value = "logoImgUrl", required = false) String logoImgUrl,
			@ApiParam(name = "name", value = "分类名字", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(name = "parentCategoryId", value = "上级分类id", required = false) @RequestParam(value = "parentCategoryId", required = false) String parentCategoryId,
			@ApiParam(name = "lever", value = "层级", required = false) @RequestParam(value = "lever", required = false) String lever,
			@ApiParam(name = "status", value = "状态", required = false) @RequestParam(value = "status", required = false) String status,
			@ApiParam(name = "categoryId", value = "分类id", required = false) @RequestParam(value = "categoryId", required = true) String categoryId,
			@ApiParam(name = "position", value = "首页", required = false) @RequestParam(value = "position", required = false) String position,
			@ApiParam(name = "sort", value = "排序", required = false) @RequestParam(value = "sort", required = false) String sort,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "分类管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(categoryId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				CategoryDO categoryPo = categoryFacade.getCategoryPoByPK(categoryId);
				if (categoryPo == null) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(!StringUtil.isNULL(logoImgUrl)){
					categoryPo.setLogoImgUrl(logoImgUrl);
				}
				if(!StringUtil.isNULL(name)){
					categoryPo.setName(name);
				}
				if(!StringUtil.isNULL(status)){
					categoryPo.setStatus(Integer.parseInt(status));
				}
				if(!StringUtil.isNULL(parentCategoryId) && StringUtil.isNumber(parentCategoryId)){
					CategoryDO categoryParentPo = categoryFacade.getCategoryPoByPK(parentCategoryId);
					if(categoryParentPo == null ){
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					categoryPo.setParentCategoryId(Integer.parseInt(parentCategoryId));
				}
				if(!StringUtil.isNULL(lever) && StringUtil.isNumber(lever)){
					categoryPo.setLever(Integer.parseInt(lever));
				}
				if(StringUtil.isNumber(position)){
					categoryPo.setPosition(Integer.parseInt(position));
				}
				if(StringUtil.isNumber(sort)){
					if(!sort.equals(categoryPo.getSort()+"")){
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("deleted", 0);
						param.put("sort", sort);
						int j = categoryFacade.countCategoryPo(param);
						if(j>0){
							return ResponseUtil.showMSGResultString("该序号已存在");
						}
					}
					categoryPo.setSort(Integer.parseInt(sort));
				}else{
					categoryPo.setSort(999);
				}

				int i = categoryFacade.updateCategoryPo(categoryPo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), categoryPo.getId(), "修改分类", "");
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
	@RequestMapping(value = "categoryIndex/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改分类", httpMethod = "POST", notes = "修改分类", response = ResponseUtil.class)
	public String editCategoryIndex(
			@ApiParam(name = "status", value = "状态", required = false) @RequestParam(value = "status", required = false) String status,
			@ApiParam(name = "categoryId", value = "分类id", required = false) @RequestParam(value = "categoryId", required = true) String categoryId,
			@ApiParam(name = "position", value = "首页", required = false) @RequestParam(value = "position", required = false) String position,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "分类管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(categoryId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				CategoryDO categoryPo = categoryFacade.getCategoryPoByPK(categoryId);
				if (categoryPo == null) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(!StringUtil.isNULL(status)){
					categoryPo.setStatus(Integer.parseInt(status));
				}
				if(StringUtil.isNumber(position)){
					categoryPo.setPosition(Integer.parseInt(position));
				}
				
				int i = categoryFacade.updateCategoryPo(categoryPo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
							RecordBase.DEALTYPE_MANAGER, account.getAccountId(), categoryPo.getId(), "修改分类", "");
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
	/**
	 * @Title: deletedCategory 
	 * @Description: 删除分类
	 * @param categoryId
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "category/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除分类", httpMethod = "POST", notes = "根据分类id删除分类", response = ResponseUtil.class)
	public String deletedCategory(
			@ApiParam(required = true, name = "categoryId", value = "分类Id") @RequestParam(value = "categoryId", required = true) String categoryId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "分类管理删除");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNULL(categoryId) && StringUtil.isNumber(categoryId)) {
					CategoryDO categoryPo = categoryFacade.getCategoryPoByPK(categoryId);
					if (categoryPo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					categoryPo.setDeleted(1);
					int i = categoryFacade.updateCategoryPo(categoryPo);
					if (i == 1) {
						//操作日志
						AccountBO account = sessionFacade.checkLoginAccountSession(request);
						RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
							RecordBase.DEALTYPE_MANAGER, account.getAccountId(), categoryPo.getId(), "删除分类", "");
						recordFacade.insertRecordDO(recordDO);
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.paramErrorResultString("缺少需要删除的分类categoryId=" + categoryId);
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	/**
	 * @Title: getCategory 
	 * @Description: TOPo
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "category/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看分类", httpMethod = "POST", notes = "查看分类", response = ResponseUtil.class)
	public Map<String, Object> getCategory(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "分类管理查看");
			if(rv.getRoleFalg().equals("1")) {
				String sSearch = request.getParameter("sSearch");
				String parentCategoryId = request.getParameter("parentCategoryId");
				String lever = request.getParameter("lever");
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
				if (!StringUtil.isNULL(sSearch)) {
					param.put("name", sSearch);
				}
				if (!StringUtil.isNULL(lever)) {
					param.put("lever", lever);
				}
				if (!StringUtil.isNULL(parentCategoryId) && StringUtil.isNumber(parentCategoryId)) {
					param.put("parentCategoryId", parentCategoryId);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<CategoryBO> pageResult = categoryFacade.getPageCategoryVo(pageEntity);
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
	@RequestMapping(value = "category/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个分类", httpMethod = "POST", notes = "根据地址id查看单个分类", response = ResponseUtil.class)
	public String getCategoryMapper(
			@ApiParam(required = false, name = "categoryId", value = "分类id") @RequestParam(value = "categoryId", required = false) String categoryId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(categoryId) && StringUtil.isNumber(categoryId)) {
				CategoryDO categoryPoByPK = categoryFacade.getCategoryPoByPK(categoryId);
				if (categoryPoByPK != null) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "查询分类详情成功", RecordsDO.RECORDTYPE_MANAGER_VIEW,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(categoryId), "查询分类详情", "");
					recordFacade.insertRecordDO(recordDO);
					
					return ResponseUtil.successResultString(categoryPoByPK);
				}
				return ResponseUtil.paramErrorResultString("参数错误");
			}
			return ResponseUtil.paramErrorResultString("分类id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "category/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询全部分类", httpMethod = "POST", notes = "查询全部分类", response = ResponseUtil.class)
	public String getAllList(
			@ApiParam(required = false, name = "parentCategoryId", value = "父级id") @RequestParam(value = "parentCategoryId", required = false) String parentCategoryId,
			@ApiParam(required = false, name = "lever", value = "层级") @RequestParam(value = "lever", required = false) String lever,
			HttpServletRequest request) {
		try {
			/**
			 * 查询条件参数
			 */
//			String parentCategoryId = request.getParameter("parentCategoryId");
//			String lever = request.getParameter("lever");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			if(!StringUtil.isNULL(parentCategoryId) && StringUtil.isNumber(parentCategoryId)){
				param.put("parentCategoryId", parentCategoryId);
			}
			if(!StringUtil.isNULL(lever) ){
				param.put("lever", lever);
			}
			/**
			 * 查询
			 */
			List<CategoryDO> categoryList = categoryFacade.getCategoryList(param);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String jsonString = JSON.toJSONString(categoryList, true);
			JSONArray jsonArray = JSON.parseArray(jsonString);
			resultMap.put("aaData", jsonArray);
			return ResponseUtil.successResultString(categoryList);
//			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "category/allFirstList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询全部分类", httpMethod = "POST", notes = "查询全部分类", response = ResponseUtil.class)
	public String getAllFirstList(
			HttpServletRequest request) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("parentCategoryId", 0);
			param.put("lever", CategoryDO.LEVER_FIRST);
			List<CategoryDO> categoryList = categoryFacade.getCategoryList(param);
			if(categoryList != null){
				return ResponseUtil.successResultString(categoryList);
			}
			return ResponseUtil.showMSGResultString("未找到一级分类");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
