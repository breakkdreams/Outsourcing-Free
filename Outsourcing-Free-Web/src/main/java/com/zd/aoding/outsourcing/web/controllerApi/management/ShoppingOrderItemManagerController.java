package com.zd.aoding.outsourcing.web.controllerApi.management;

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
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;

@Api(value = "", description = "订单详情管理")
@Controller
@RequestMapping("ad/manager")
public class ShoppingOrderItemManagerController {
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;

	@ResponseBody
	@RequestMapping(value = "orderItem/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看列表", httpMethod = "POST", notes = "查看列表", response = ResponseUtil.class)
	public Map<String, Object> getorderItemPaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			@ApiParam(required = false, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = false) String goodsId,
			@ApiParam(required = false, name = "time", value = "time") @RequestParam(value = "time", required = false) String time,
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
			//判断 去掉积分付款及未付款订单
			param.put("payType", 1);
			if(!StringUtil.isNULL(time)){
				param.put("times", time);
			}
			String orderIdList = "";
			List<ShoppingOrderDO> orderList = shoppingOrderFacade.getShoppingOrderList(param);
			if(orderList!=null && orderList.size()>0){
				for (ShoppingOrderDO shoppingOrderPo : orderList) {
					if(!StringUtil.isNULL(orderIdList)){
						orderIdList += ",";
					}
					orderIdList += shoppingOrderPo.getId();
				}
			}
			if(!StringUtil.isNULL(orderIdList)){
				param.put("orderIdList",orderIdList);
			}
			if(!StringUtil.isNULL(goodsId)){
				param.put("goodsId", goodsId);
			}
			pageEntity.setParams(param);
			/**
			 * 查询
			 */
			PageResult<ShoppingOrderItemBO> pageResult = shoppingOrderItemFacade.getPageShoppingOrderItemVo(pageEntity);
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
