package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BannerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsModelBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BannerFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsModelFacade;

@Api(value = "", description = "模块")
@Controller
@RequestMapping("pub")
public class ModelPubController {
	@Autowired
	private GoodsModelFacade goodsModelFacade;
	@Autowired
	private BannerFacade bannerFacade;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private CategoryFacade categoryFacade;

//	@ResponseBody
//	@RequestMapping(value = "goodsModel/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = BannerDO.class) })
//	@ApiOperation(value = "分页查询", httpMethod = "POST", notes = "", response = ResponseUtil.class)
//	public String paging(
//			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
//			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
//			HttpServletRequest request) {
//		try {
//			// 初始化列表
//			PageEntity pageEntity = new PageEntity();
//			if (StringUtil.isNumber(pageNum)) {
//				pageEntity.setPage(Integer.parseInt(pageNum));
//			}
//			if (StringUtil.isNumber(pageSize)) {
//				pageEntity.setSize(Integer.parseInt(pageSize));
//			}
//			/**
//			 * 排序
//			 */
//			pageEntity.setOrderColumn("update_time");
//			/**
//			 * 查询条件参数
//			 */
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("deleted", 0);
//			pageEntity.setParams(param);
//			PageResult<AppModelVo> pageResult = appModelFacade.getPageAppModelVo(pageEntity);
//			return ResponseUtil.successResultString(pageResult);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
//	@ResponseBody
//	@RequestMapping(value = "goodsModel/detail", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = GoodsModelDO.class) })
//	@ApiOperation(value = "查看单个model", httpMethod = "POST", notes = "根据ID查看model", response = ResponseUtil.class)
//	public String getBannerById(
//			@ApiParam(required = false, name = "modelId", value = "modelId") @RequestParam(value = "modelId", required = false) String modelId,
//			HttpServletRequest request) {
//		try {
//			if (StringUtil.isNumber(modelId)) {
//				AppModelVo modelVo = appModelFacade.getAppModelVoByPK(Integer.parseInt(modelId));
//				if (modelVo != null) {
//					return ResponseUtil.successResultString(modelVo);
//				}
//				return ResponseUtil.resultString("未找到", ResponseCodeEnum.PARAM_ERROR);
//			}
//			return ResponseUtil.resultString("数据错误", ResponseCodeEnum.PARAM_ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
	@ResponseBody
	@RequestMapping(value = "goodsModel/index", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "app首页", response = GoodsModelDO.class) })
	@ApiOperation(value = "app首页", httpMethod = "POST", notes = "app首页", response = ResponseUtil.class)
	public String getIndex(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			/** 首页banner */
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPage(1);
			pageEntity.setSize(5);
			pageEntity.setOrderColumn("update_time");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
//			param.put("position", BannerPo.position_index);
			pageEntity.setParams(param);
			PageResult<BannerBO> pageResult = bannerFacade.getPageBannerVo(pageEntity);
			/** 模块 */
			Map<String, Object> param2 = new HashMap<String, Object>();
			param2.put("deleted", 0);
			param2.put("orderColumn", "sort");
			param2.put("orderTurn", "ASC");
			//param2.put("isShow", AppModelPo.isShow_show);
			//List<AppModelVo> model = appModelFacade.getAppModelList(param2);
			List<GoodsModelBO> model = goodsModelFacade.getGoodsModelList(param2);
			/** 分类 */
			Map<String, Object> param4 = new HashMap<String, Object>();
			param4.put("deleted", 0);
			param4.put("status", CategoryDO.STATUS_USE);
			param4.put("position", 1);
			param4.put("orderColumn", "sort");
			List<CategoryBO> category = categoryFacade.getCategoryListIndex(param4);
			if(category.size()>8){
				category = category.subList(0, 8);
			}
			/** 首页产品 */
			PageEntity pageEntity2 = new PageEntity();
			if (StringUtil.isNumber(pageNum)) {
				pageEntity2.setPage(Integer.parseInt(pageNum));
			}
			if (StringUtil.isNumber(pageSize)) {
				pageEntity2.setSize(Integer.parseInt(pageSize));
			}
			pageEntity.setOrderColumn("create_time");
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param3 = new HashMap<String, Object>();
			param3.put("deleted", 0);
			param3.put("statusAdmin", GoodsDO.STATUS_SHALL);
			param3.put("position", GoodsDO.position_home);
			pageEntity2.setParams(param3);
			PageResult<GoodsBO> goods = goodsFacade.getPageGoodsVo(pageEntity2);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("banner", pageResult);
			result.put("model", model);
			result.put("category", category);
			result.put("goods", goods);
			return ResponseUtil.successResultString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
}
