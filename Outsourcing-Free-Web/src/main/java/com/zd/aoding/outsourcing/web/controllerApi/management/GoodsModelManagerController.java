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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsModelBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsModelFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "产品模块")
@Controller
@RequestMapping("ad/manager")
public class GoodsModelManagerController {
	@Autowired
	private GoodsModelFacade goodsModelFacade;
	@Autowired
	private HttpSession session;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;


	@ResponseBody
	@RequestMapping(value = "goodsModel/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加产品模块", httpMethod = "POST", notes = "添加产品模块", response = ResponseUtil.class)
	public String addGoodsModel(
			@ApiParam(name = "modelName", value = "模块名称", required = false) @RequestParam(value = "modelName", required = false)
			String modelName,
			@ApiParam(name = "summary", value = "模块描述", required = false) @RequestParam(value = "summary", required = false)
			String summary,
			@ApiParam(name = "sort", value = "排序值", required = false) @RequestParam(value = "sort", required = false)
			String sort,
			@ApiParam(name = "appCode", value = "appCode", required = false) @RequestParam(value = "appCode", required = false)
			String appCode,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "模块设置添加");
			if(rv.getRoleFalg().equals("1")) {
				if(!StringUtil.isNumber(sort)){
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				param.put("sort",sort);
				int resu = goodsModelFacade.countGoodsModel(param);
				if(resu>0){
					return ResponseUtil.showMSGResultString("该序号已存在");
				}
				GoodsModelDO goodsModelPo = new GoodsModelDO(modelName,Integer.parseInt(sort), "0");
				goodsModelPo.setSummary(summary);
				int i = goodsModelFacade.insertGoodsModelPo(goodsModelPo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加产品模块成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),goodsModelPo.getId(), "添加产品模块", "");
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
	@RequestMapping(value = "goodsModel/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改产品模块", httpMethod = "POST", notes = "修改产品模块", response = ResponseUtil.class)
	public String editGoodsModel(
			@ApiParam(name = "modelName", value = "模块名称", required = false) @RequestParam(value = "modelName", required = false)
					String modelName,
			@ApiParam(name = "sort", value = "排序值", required = false) @RequestParam(value = "sort", required = false)
					String sort,
			@ApiParam(name = "goodsModelId", value = "id", required = false) @RequestParam(value = "goodsModelId", required = true)
					String goodsModelId,
			@ApiParam(name = "summary", value = "描述", required = false) @RequestParam(value = "summary", required = false)
					String summary,
			@ApiParam(name = "imgOne", value = "图片1", required = false) @RequestParam(value = "imgOne", required = false)
					String imgOne,
			@ApiParam(name = "imgTwo", value = "id", required = false) @RequestParam(value = "imgTwo", required = false)
					String imgTwo,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "模块设置修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(goodsModelId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				GoodsModelDO goodsModelPo = goodsModelFacade.getGoodsModelPoByPK(goodsModelId);
				if (goodsModelPo == null) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(!StringUtil.isNULL(modelName)){
					goodsModelPo.setModelName(modelName);
				}
				if(!StringUtil.isNULL(sort)){
					if(!sort.equals(goodsModelPo.getSort()+"")){
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("deleted", 0);
						param.put("sort",sort);
						int resu = goodsModelFacade.countGoodsModel(param);
						if(resu>0){
							return ResponseUtil.showMSGResultString("该序号已存在");
						}
					}
					goodsModelPo.setSort(Integer.parseInt(sort));
				}
				if(!StringUtil.isNULL(summary)){
					goodsModelPo.setSummary(summary);
				}
				if(!StringUtil.isNULL(imgOne)){
					goodsModelPo.setImgOne(imgOne);
				}
				if(!StringUtil.isNULL(imgTwo)){
					goodsModelPo.setImgTwo(imgTwo);
				}

				int i = goodsModelFacade.updateGoodsModelPo(goodsModelPo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改产品模块成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(goodsModelId), "修改产品模块", "");
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
	@RequestMapping(value = "goodsModel/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除模块", httpMethod = "POST", notes = "删除模块", response = ResponseUtil.class)
	public String deletedGoodsModel(
			@ApiParam(required = true, name = "goodsModelId", value = "id") @RequestParam(value = "goodsModelId", required = true)
					String goodsModelId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "模块设置删除");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNULL(goodsModelId) && StringUtil.isNumber(goodsModelId)) {
					GoodsModelDO goodsModelPo = goodsModelFacade.getGoodsModelPoByPK(goodsModelId);
					if (goodsModelPo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					goodsModelPo.setDeleted(1);
					int i = goodsModelFacade.updateGoodsModelPo(goodsModelPo);
					if (i == 1) {
						//操作日志
						AccountBO account = sessionFacade.checkLoginAccountSession(request);
						RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除产品模块成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
							RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(goodsModelId), "删除产品模块", "");
						recordFacade.insertRecordDO(recordDO);
						
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.paramErrorResultString("缺少需要删除的goodsModelId=" + goodsModelId);
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "goodsModel/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看模块", httpMethod = "POST", notes = "查看模块", response = ResponseUtil.class)
	public Map<String, Object> getGoodsModel(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "模块设置查看");
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
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<GoodsModelBO> pageResult = goodsModelFacade.getPageGoodsModelVo(pageEntity);
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
	@RequestMapping(value = "goodsModel/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个模块", httpMethod = "POST", notes = "查看单个模块", response = ResponseUtil.class)
	public String getMapper(
			@ApiParam(required = false, name = "goodsModelId", value = "id") @RequestParam(value = "goodsModelId", required = false)
					String goodsModelId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(goodsModelId) && StringUtil.isNumber(goodsModelId)) {
				GoodsModelDO goodsModelPo = goodsModelFacade.getGoodsModelPoByPK(goodsModelId);
				if (goodsModelPo != null) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "查看模块详情成功", RecordsDO.RECORDTYPE_MANAGER_VIEW,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(goodsModelId), "查看模块详情", "");
					recordFacade.insertRecordDO(recordDO);
					
					return ResponseUtil.successResultString(goodsModelPo);
				}
				return ResponseUtil.showMSGResultString("参数错误");
			}
			return ResponseUtil.paramErrorResultString("分类id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "goodsModel/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询全部模块", httpMethod = "POST", notes = "查询全部模块", response = ResponseUtil.class)
	public String getAllList(
			@ApiParam(required = false, name = "sort", value = "排序") @RequestParam(value = "sort", required = false)
					String sort,
			@ApiParam(required = false, name = "inSelf", value = "不包含id") @RequestParam(value = "inSelf", required = false)
					String inSelf,
			HttpServletRequest request) {
		try {
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			if(!StringUtil.isNULL(sort)){
				param.put("sort",sort);
			}
			if(!StringUtil.isNULL(inSelf)){
				param.put("inSelf",inSelf);
			}
			/**
			 * 查询
			 */
			List<GoodsModelDO> categoryList = goodsModelFacade.getGoodsModelPoList(param);
			return ResponseUtil.successResultString(categoryList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
