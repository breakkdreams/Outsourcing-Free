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
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsTypeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsTypeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsTypeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "产品类型")
@Controller
@RequestMapping("ad/manager")
public class GoodsTypeManagerController {
	@Autowired
	private GoodsTypeFacade goodsTypeFacade;
	@Autowired
	private HttpSession session;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private RecordFacade recordFacade;

	@ResponseBody
	@RequestMapping(value = "goodsType/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改产品类型", httpMethod = "POST", notes = "修改产品类型", response = ResponseUtil.class)
	public String editGoodsType(
			@ApiParam(name = "goodsTypeId", value = "id", required = false) @RequestParam(value = "goodsTypeId", required = true)
			String goodsTypeId,
			@ApiParam(name = "typeName", value = "类型名称", required = false) @RequestParam(value = "typeName", required = false)
			String typeName,
			@ApiParam(name = "rebateRatio", value = "返利比例", required = false) @RequestParam(value = "rebateRatio", required = false)
			String rebateRatio,
			@ApiParam(name = "isService", value = "是否售后", required = false) @RequestParam(value = "isService", required = false)
			String isService,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商品类型管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(goodsTypeId)) {
					return ResponseUtil.showMSGResultString("类型id错误");
				}
				if (!StringUtil.isNumber(rebateRatio)) {
					return ResponseUtil.showMSGResultString("返利比例错误");
				}
				if (!StringUtil.isNumber(isService)) {
					return ResponseUtil.showMSGResultString("是否售后错误");
				}
				if (StringUtil.isNULL(typeName)) {
					return ResponseUtil.showMSGResultString("类型名称不能为空");
				}
				GoodsTypeDO goodsTypePo = goodsTypeFacade.getGoodsTypePoByPK(goodsTypeId);
				if (goodsTypePo == null) {
					return ResponseUtil.showMSGResultString("未找到该类型");
				}
				goodsTypePo.setRebateRatio(Integer.parseInt(rebateRatio));
				goodsTypePo.setIsService(Integer.parseInt(isService));
				goodsTypePo.setTypeName(typeName);

				int i = goodsTypeFacade.updateGoodsTypePo(goodsTypePo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改产品类型成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(goodsTypeId), "修改产品类型", "");
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
	@RequestMapping(value = "goodsType/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看类型", httpMethod = "POST", notes = "查看类型", response = ResponseUtil.class)
	public Map<String, Object> getGoodsType(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商品类型管理查看");
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
				PageResult<GoodsTypeBO> pageResult = goodsTypeFacade.getPageGoodsTypeVo(pageEntity);
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
	@RequestMapping(value = "goodsType/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个模块", httpMethod = "POST", notes = "查看单个模块", response = ResponseUtil.class)
	public String getMapper(
			@ApiParam(required = false, name = "goodsTypeId", value = "id") @RequestParam(value = "goodsTypeId", required = false)
					String goodsTypeId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(goodsTypeId) && StringUtil.isNumber(goodsTypeId)) {
				GoodsTypeDO goodsTypePo = goodsTypeFacade.getGoodsTypePoByPK(goodsTypeId);
				if (goodsTypePo != null) {
					return ResponseUtil.successResultString(goodsTypePo);
				}
				return ResponseUtil.showMSGResultString("参数错误");
			}
			return ResponseUtil.showMSGResultString("分类id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "goodsType/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
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
			List<GoodsTypeDO> categoryList = goodsTypeFacade.getGoodsTypePoList(param);
			return ResponseUtil.successResultString(categoryList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
