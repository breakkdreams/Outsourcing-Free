package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "分类")
@Controller
@RequestMapping("pub")
public class CategoryPubController {
	@Autowired
	private CategoryFacade categoryFacade;
	@Autowired
	private SessionFacade sessionService;

	@ResponseBody
	@RequestMapping(value = "category/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "分页查询", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String paging(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			// 初始化列表
			PageEntity pageEntity = new PageEntity();
			if (StringUtil.isNumber(pageNum)) {
				pageEntity.setPage(Integer.parseInt(pageNum));
			}
			if (StringUtil.isNumber(pageSize)) {
				pageEntity.setSize(Integer.parseInt(pageSize));
			}
			/**
			 * 排序
			 */
			pageEntity.setOrderColumn("update_time");
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", CategoryDO.STATUS_USE);
			param.put("deleted", 0);
			pageEntity.setParams(param);
			PageResult<CategoryBO> pageResult = categoryFacade.getPageCategoryVo(pageEntity);
			return ResponseUtil.successResultString(pageResult);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	/**
	 * @Title: getCategoryMapper 
	 * @Description: 根据地址id查看单个分类
	 * @param categoryId
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "category/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个分类", httpMethod = "POST", notes = "根据地址id查看单个分类", response = ResponseUtil.class)
	public String getCategoryMapper(
			@ApiParam(required = false, name = "categoryId", value = "分类id") @RequestParam(value = "categoryId", required = false) String categoryId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(categoryId) && StringUtil.isNumber(categoryId)) {
				CategoryDO category = categoryFacade.getCategoryPoByPK(categoryId);
				if (category != null) {
					CategoryBO categoryBO = new CategoryBO(category);
					return ResponseUtil.successResultString(categoryBO);
				}
				return ResponseUtil.showMSGResultString("未找到记录");
			}
			return ResponseUtil.errorResultString("分类id为空或格式不正确");
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
			param.put("status", CategoryDO.STATUS_USE);
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
	
	/**
	 * @Title: getAllList 
	 * @Description: TODO
	 * @param parentCategoryId
	 * @param lever
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "category/allListThird", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询全部分类", httpMethod = "POST", notes = "查询全部分类", response = ResponseUtil.class)
	public String getAllListThird(
			HttpServletRequest request) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("lever", 1);
			param.put("status", CategoryDO.STATUS_USE);
			/**
			 * 查询
			 */
			List<Map<String, Object>> finalResultList = new ArrayList<>();
			
			List<CategoryBO> categoryList = categoryFacade.getCategoryBOList(param);
			if(categoryList != null){
				for (int i = 0; i < categoryList.size(); i++) {
					Map<String, Object> finalResultMap = new HashMap<>();
					int id = categoryList.get(i).getCategoryId();
					String name = categoryList.get(i).getName();
					Map<String, Object> map = new HashMap<>();
					map.put("deleted", 0);
					map.put("parentCategoryId", id);
					map.put("status", CategoryDO.STATUS_USE);
					finalResultMap.put("name", name);
					finalResultMap.put("id", id);
					finalResultMap.put("lever", categoryList.get(i).getLever());
					finalResultList.add(finalResultMap);
					List<CategoryBO> categoryList_1 = categoryFacade.getCategoryBOList(map);
					List<Map<String, Object>> secondlist = new ArrayList<>();
					for (int j = 0; j < categoryList_1.size(); j++) {
						Map<String, Object> resultMapThird = new HashMap<>();
						int thi_id = categoryList_1.get(j).getCategoryId();
						String thi_name = categoryList_1.get(j).getName();
						resultMapThird.put("name", thi_name);
						resultMapThird.put("id", thi_id);
						resultMapThird.put("lever", categoryList_1.get(j).getLever());
						secondlist.add(resultMapThird);
						finalResultMap.put("secondLever", secondlist);
						Map<String, Object> map_3 = new HashMap<>();
						map_3.put("deleted", 0);
						map_3.put("parentCategoryId", thi_id);
						map_3.put("status", CategoryDO.STATUS_USE);
						List<Map<String, Object>> thirdlist = new ArrayList<>();
						List<CategoryBO> categoryList_2 = categoryFacade.getCategoryBOList(map_3);
						for (int k = 0; k < categoryList_2.size(); k++) {
							int fin_id = categoryList_2.get(k).getCategoryId();
							String fin_name = categoryList_2.get(k).getName();
							String fin_logo = categoryList_2.get(k).getLogoImgUrl();
							Map<String, Object> finalMap = new HashMap<>();
							finalMap.put("name", fin_name);
							finalMap.put("id", fin_id);
							finalMap.put("logo", fin_logo);
							finalMap.put("lever", categoryList_2.get(k).getLever());
							thirdlist.add(finalMap);
							resultMapThird.put("thirdLever", thirdlist);
						}
					}
				}
			}
			
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			String jsonString = JSON.toJSONString(categoryList, true);
//			JSONArray jsonArray = JSON.parseArray(jsonString);
//			resultMap.put("aaData", jsonArray);
			return ResponseUtil.successResultString(finalResultList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
