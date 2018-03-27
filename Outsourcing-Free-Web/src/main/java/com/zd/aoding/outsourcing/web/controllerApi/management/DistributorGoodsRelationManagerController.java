package com.zd.aoding.outsourcing.web.controllerApi.management;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.DistributorGoodsRelationBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorGoodsRelationDO;
import com.zd.aoding.outsourcing.weChat.api.facade.DistributorGoodsRelationFacade;
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

@Api(value = "", description = "配货表管理")
@Controller
@RequestMapping("ad/manager")
public class DistributorGoodsRelationManagerController {
	@Autowired
	private DistributorGoodsRelationFacade distributorGoodsRelationFacade;

	@ResponseBody
	@RequestMapping(value = "distributorGoodsRelation/DTPaging", method = RequestMethod.POST)
	@ApiOperation(value = "配货分页查询轮播图", httpMethod = "POST", response = ModelAndView.class)
	public Map<String, Object> distributorGoodsRelationDTPaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "当前页数") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页条数") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
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
			PageResult<DistributorGoodsRelationBO> pageResult = distributorGoodsRelationFacade.getPageDistributorGoodsRelationBO(pageEntity);
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

	@ResponseBody
	@RequestMapping(value = "distributorGoodsRelation/edit", method = RequestMethod.POST)
	@ApiOperation(value = "修改配货", httpMethod = "POST", response = ModelAndView.class)
	public String updateDistributorGoodsRelation(
			@ApiParam(required = false, name = "distributorId", value = "经销商id") @RequestParam(value = "distributorId", required = false) String distributorId,
			@ApiParam(required = false, name = "goodsId", value = "商品id") @RequestParam(value = "goodsId", required = false) String goodsId,
			@ApiParam(required = false, name = "optionId", value = "配置id") @RequestParam(value = "optionId", required = false) String optionId,
			@ApiParam(required = false, name = "price", value = "价格") @RequestParam(value = "price", required = false) String price,
			@ApiParam(required = false, name = "haveOption", value = "是否有配置.0无1有") @RequestParam(value = "haveOption", required = false) String haveOption,
			@ApiParam(required = false, name = "firstCatagory", value = "一级分类") @RequestParam(value = "firstCatagory", required = false) String firstCatagory,
			@ApiParam(required = false, name = "secondCatagory", value = "二级分类") @RequestParam(value = "secondCatagory", required = false) String secondCatagory,
			@ApiParam(required = false, name = "thirdCatagory", value = "三级分类") @RequestParam(value = "thirdCatagory", required = false) String thirdCatagory,
			@ApiParam(required = false, name = "stock", value = "库存") @RequestParam(value = "stock", required = false) String stock,
			@ApiParam(required = false, name = "distributorGoodsRelationId", value = "id") @RequestParam(value = "distributorGoodsRelationId", required = false) String distributorGoodsRelationId,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) request.getSession(true).getAttribute("menuSession");
			RoleBO rv = new RoleBO(sess, "经销商管理修改");
			if ("1".equals(rv.getRoleFalg())) {
				if (!StringUtil.isNumber(distributorGoodsRelationId) || !StringUtil.isNumber(goodsId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(StringUtil.isNULL(price)){
					return ResponseUtil.showMSGResultString("请输入价格");
				}
				if(!StringUtil.isNumber(stock)){
					return ResponseUtil.showMSGResultString("请输入库存");
				}
				DistributorGoodsRelationDO distributorGoodsRelationDO = distributorGoodsRelationFacade.getDistributorGoodsRelationDOByPK(Integer.parseInt(distributorGoodsRelationId));
				distributorGoodsRelationDO.setStock(Integer.parseInt(stock));
				distributorGoodsRelationDO.setDistributorId(Integer.parseInt(distributorId));


				int i = distributorGoodsRelationFacade.updateDistributorGoodsRelationDO(distributorGoodsRelationDO);
				if (i == 1) {
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			}
			return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "distributorGoodsRelation/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加配货", httpMethod = "POST", response = ModelAndView.class)
	public String addDistributorGoodsRelation(
			@ApiParam(required = false, name = "distributorId", value = "经销商id") @RequestParam(value = "distributorId", required = false) String distributorId,
			@ApiParam(required = false, name = "goodsId", value = "商品id") @RequestParam(value = "goodsId", required = false) String goodsId,
			@ApiParam(required = false, name = "optionId", value = "配置id") @RequestParam(value = "optionId", required = false) String optionId,
			@ApiParam(required = false, name = "price", value = "价格") @RequestParam(value = "price", required = false) String price,
			@ApiParam(required = false, name = "stock", value = "库存") @RequestParam(value = "stock", required = false) String stock,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) request.getSession(true).getAttribute("menuSession");
			RoleBO rv = new RoleBO(sess, "经销商管理添加");
			if ("1".equals(rv.getRoleFalg())) {
				if (!StringUtil.isNumber(distributorId) || !StringUtil.isNumber(goodsId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				DistributorGoodsRelationDO bPo = new DistributorGoodsRelationDO();
				bPo.setDistributorId(Integer.parseInt(distributorId));
				bPo.setGoodsId(Integer.parseInt(goodsId));
				if (!StringUtil.isNULL(price)) {
					bPo.setPrice(new BigDecimal(price).doubleValue());
				}
				if (StringUtil.isNumber(optionId)) {
					bPo.setOptionId(Integer.parseInt(optionId));
				}
				if (StringUtil.isNumber(stock)) {
					bPo.setStock(Integer.parseInt(stock));
				}
				bPo.insertInit();
				int i = distributorGoodsRelationFacade.insertDistributorGoodsRelationDO(bPo);
				if (i == 1) {
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			}
			return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
