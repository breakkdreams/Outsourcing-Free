package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsOptionBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsParamBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsParamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsParamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;

@Api(value = "", description = "产品查询")
@Controller
@RequestMapping("pub")
public class GoodsPubController {
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private GoodsSpecFacade goodsSpecFacade;
	@Autowired
	private GoodsParamFacade goodsParamFacade;
	@Autowired
	private GoodsOptionFacade goodsptionFacade;
	@Autowired
	private CategoryFacade categoryFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;

	@ResponseBody
	@RequestMapping(value = "goods/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "产品详情", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = GoodsBO.class) })
	public String goodsDetail(
			@ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNumber(goodsId)) {
				return ResponseUtil.paramErrorResultString("goodsI=" + goodsId);
			}
			//获取客服电话
			String servicePhone = "";
			SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("servicePhone");
			if(systemparamPo != null){
				servicePhone = systemparamPo.getStringVale();
			}
			GoodsBO goodsVo = goodsFacade.getGoodsVoByPK(goodsId);
			if (goodsVo != null) {
				goodsVo.setServicePhone(servicePhone);
				return ResponseUtil.successResultString(goodsVo);
			}
			return ResponseUtil.showMSGResultString("查询失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goods/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "分页查询产品", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String paging(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = false, name = "modelId", value = "模块id") @RequestParam(value = "modelId", required = false) String modelId,
			@ApiParam(required = false, name = "position", value = "==1(查询首页产品)") @RequestParam(value = "position", required = false) String position,
			@ApiParam(required = false, name = "title", value = "名称模糊搜索") @RequestParam(value = "title", required = false) String title,
			@ApiParam(required = false, name = "categoryId", value = "分类id") @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(required = false, name = "sort", value = "排序 1销量 2价格 3积分百分比 4.积分大小 ") @RequestParam(value = "sort", required = false) String sort,
			@ApiParam(required = false, name = "ascOrDesc", value = "1正序 2倒序  默认倒序") @RequestParam(value = "ascOrDesc", required = false) String ascOrDesc,
//			@ApiParam(required = false, name = "goodsType", value = "积分商城或奖金商城 0 全部 1积分 2奖金") @RequestParam(value = "goodsType", required = false) String goodsType,
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
			if(StringUtil.isNULL(sort)){
				pageEntity.setOrderColumn("sales");
			}
			if("1".equals(sort)){
				pageEntity.setOrderColumn("sales");
			}
			if("2".equals(sort)){
                pageEntity.setOrderColumn("market_price");
            }
			if("3".equals(sort)){
				pageEntity.setOrderColumn("integral_percent");
			}
			if("4".equals(sort)){
				pageEntity.setOrderColumn("integral_deductible");
			}
			if("1".equals(ascOrDesc)){
				pageEntity.setOrderTurn("asc");
			}
			if("2".equals(ascOrDesc)){
				pageEntity.setOrderTurn("desc");
			}
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("statusAdmin", GoodsDO.STATUS_SHALL);
//			if((GoodsPo.type_score+"").equals(goodsType)){
//				param.put("type", GoodsPo.type_score);
//			}
//			if((GoodsPo.type_cash+"").equals(goodsType)){
//				param.put("type", GoodsPo.type_cash);
//			}
			// 再收status>0
			param.put("sell", 0);
			if(StringUtil.isNumber(modelId)){
				param.put("modelId", modelId);
			}
			param.put("position", position);
			if (!StringUtil.isNULL(title)) {
				param.put("titleLike", title);
			}
			if (StringUtil.isNumber(categoryId)) {
				CategoryDO categoryDO = categoryFacade.getCategoryPoByPK(categoryId);
				if(categoryDO == null){
					return ResponseUtil.errorResultString("请重新选择分类");
				}
				CategoryBO category = new CategoryBO(categoryDO);
				if(CategoryDO.LEVER_FIRST.equals(category.getLever())){
					System.out.println("一级分类id=="+categoryId);
					param.put("firstCatagory", Integer.parseInt(categoryId));
				}
				if(CategoryDO.LEVER_SECOND.equals(category.getLever())){
					System.out.println("二级分类id=="+categoryId);
					param.put("secondCatagory", Integer.parseInt(categoryId));
				}
				if(CategoryDO.LEVER_THIRD.equals(category.getLever())){
					System.out.println("三级分类id=="+categoryId);
					param.put("thirdCatagory", Integer.parseInt(categoryId));
				}
			}
			pageEntity.setParams(param);
			PageResult<GoodsBO> pageResult = goodsFacade.getPageGoodsVo(pageEntity);
			return ResponseUtil.successResultString(pageResult);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goods/spec", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "所有产品配置", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称及详情", response = GoodsSpecBO.class),
			@ApiResponse(code = 2, message = "配置实际组", response = GoodsOptionDO.class) })
	public String mallGoodsSpec(
			@ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNumber(goodsId)) {
				List<GoodsSpecBO> listSpec = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
				if (listSpec != null) {
					return ResponseUtil.successResultString(listSpec);
				}
				listSpec = new ArrayList<GoodsSpecBO>();
				return ResponseUtil.successResultString(listSpec);
			} else {
				return ResponseUtil.showMSGResultString("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goods/param", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "产品属性信息", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = GoodsParamDO.class) })
	public String mallGoodsParam(
			@ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNumber(goodsId)) {
				List<GoodsParamBO> listParam = goodsParamFacade.getParamListByGoodsId(Integer.parseInt(goodsId));
				return ResponseUtil.successResultString(listParam);
			} else {
				return ResponseUtil.showMSGResultString("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goods/option", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "某个产品配置组合", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "配置实际组合", response = GoodsOptionDO.class) })
	public String mallGoodsOption(
			@ApiParam(required = true, name = "goodsid", value = "产品id") @RequestParam(value = "goodsid", required = true) String goodsid,
			@ApiParam(required = true, name = "specItemIds", value = "配置详情id(以‘_’分割)") @RequestParam(value = "specItemIds", required = true) String specItemIds,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNumber(goodsid) && !StringUtil.isNULL(specItemIds)) {
				if (!StringUtil.isNumber(specItemIds.replace("_", ""))) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				String[] specItemId = specItemIds.split("_");
				Arrays.sort(specItemId, Collections.reverseOrder());
				String s = specItemId[0];
				for (int i = 1; i < specItemId.length; i++) {
					s = s + "_" + specItemId[i];
				}
				System.out.println(s);
				GoodsOptionBO optionVo = goodsptionFacade.getOptionVoBySpecIds(s, goodsid);
				if (optionVo != null) {
					return ResponseUtil.successResultString(optionVo);
				} else {
					Arrays.sort(specItemId);
					String s1 = specItemId[0];
					for (int i = 1; i < specItemId.length; i++) {
						s1 = s1+ "_" + specItemId[i];
					}
					System.out.println(s1);
					optionVo = goodsptionFacade.getOptionVoBySpecIds(s1, goodsid);
					if (optionVo != null) {
						return ResponseUtil.successResultString(optionVo);
					}
					return ResponseUtil.showMSGResultString("请重新选择配置");
				}
			} else {
				return ResponseUtil.showMSGResultString("查询失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "goods/like", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "根据goodsId分页查询相似产品", httpMethod = "POST", notes = "根据goodsId分页查询相似产品", response = ResponseUtil.class)
	public String goodsLike(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = false, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = false) String goodsId,
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
			pageEntity.setOrderColumn("sales");
			
			if(!StringUtil.isNumber(goodsId)){
				return ResponseUtil.paramErrorResultString("goodsId错误");
			}
			GoodsDO goods = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
			if(goods == null){
				return ResponseUtil.errorResultString("未找到该产品");
			}
			Integer categoryId = goods.getThirdCatagory();
			
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("statusAdmin", GoodsDO.STATUS_SHALL);
			param.put("thirdCategory", categoryId);
			
			pageEntity.setParams(param);
			PageResult<GoodsBO> pageResult = goodsFacade.getPageGoodsVo(pageEntity);
			return ResponseUtil.successResultString(pageResult);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	public static void main(String[] args) {
		if(GoodsDO.type_score == Integer.parseInt("1")){
			
		}
		System.out.println(GoodsDO.type_score == Integer.parseInt("1"));
	}
}
