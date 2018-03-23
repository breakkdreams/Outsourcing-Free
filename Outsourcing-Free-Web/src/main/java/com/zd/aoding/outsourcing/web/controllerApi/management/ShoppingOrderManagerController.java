package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserAddressBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.NoticeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderItemDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AddressFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.NoticeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.plugin.KuaiDiNiao.KdApiOrderDistinguish;

@Api(value = "", description = "订单管理")
@Controller
@RequestMapping("ad/manager")
public class ShoppingOrderManagerController {
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;
	@Autowired
	private HttpSession session;
	@Autowired
	private NoticeFacade noticeFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private UserFacade zxUserFacade;
	@Autowired
	private AddressFacade addressFacade;

	@ResponseBody
	@RequestMapping(value = "order/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看列表", httpMethod = "POST", notes = "查看列表", response = ResponseUtil.class)
	public Map<String, Object> getSupplierPaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			@ApiParam(required = false, name = "process", value = "process") @RequestParam(value = "process", required = false) String process,
			@ApiParam(required = false, name = "type", value = "type") @RequestParam(value = "type", required = false) String type,
			@ApiParam(required = false, name = "orderId", value = "orderId") @RequestParam(value = "orderId", required = false) String orderId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
//			RoleVo rv = new RoleVo(sess, "订单管理查看");
//			if(rv.getRoleFalg().equals("1")) {
				String sSearch = request.getParameter("sSearch");
				String userName = request.getParameter("userName");
				String addressName = request.getParameter("addressName");
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
				pageEntity.setOrderColumn("FIELD(process, 2) DESC,create_time");
				pageEntity.setOrderTurn("DESC");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				if(!StringUtil.isNULL(sSearch)){
					param.put("orderNumLike", sSearch);
				}
				if(!StringUtil.isNULL(userName)){
					UserDO zxUserPo = zxUserFacade.getUserByUserName(userName);
					if(zxUserPo!=null){
						param.put("boughtUserId", zxUserPo.getId());
					}
				}
				if(!StringUtil.isNULL(addressName)){
					String ids = "";
					List<UserAddressBO> listVo = addressFacade.getAddressVoListByName(addressName);
					if(listVo != null && listVo.size()>0){
						for (UserAddressBO userAddressVo : listVo) {
							if(ids!=""){
								ids+=",";
							}
							ids+=userAddressVo.getAddressId();
						}
					}
					if(!StringUtil.isNULL(ids)){
						param.put("boughtAddressId", ids);
					}
				}
				if(!StringUtil.isNULL(process) && !"-1".equals(process)){
					param.put("process", process);
				}
				if(!StringUtil.isNULL(type)){
					param.put("orderType", type);
				}
				if(StringUtil.isNumber(orderId)){
					param.put("orderId", orderId);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<ShoppingOrderBO> pageResult = shoppingOrderFacade.getPageShoppingOrderVo(pageEntity);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
				JSONArray jsonArray = JSON.parseArray(jsonString);
				resultMap.put("iTotalRecords", pageResult.getTotalSize());
				resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
				resultMap.put("aaData", jsonArray);
				return resultMap;
//			}else{
//				return ResponseUtil.resultMap("暂无权限",ResponseCodeEnum.ERROR);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看", httpMethod = "POST", notes = "查看", response = ResponseUtil.class)
	public String orderDetail(
			@ApiParam(required = true, name = "orderId", value = "id") @RequestParam(value = "orderId", required = true) String orderId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(orderId) && StringUtil.isNumber(orderId)) {
				ShoppingOrderDO shoppingOrderPo = shoppingOrderFacade.getShoppingOrderPoByPK(Integer.parseInt(orderId));
				if (shoppingOrderPo != null) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "查看订单详情成功", RecordsDO.RECORDTYPE_MANAGER_VIEW,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(orderId), "查看订单详情", "");
					recordFacade.insertRecordDO(recordDO);
					
					return ResponseUtil.successResultString(shoppingOrderPo);
				}
				return ResponseUtil.showMSGResultString("未找到");
			}
			return ResponseUtil.paramErrorResultString("id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "order/getKdniaoInfo", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看快递详情", httpMethod = "POST", notes = "查看快递详情", response = ResponseUtil.class)
	public String getKdniaoInfo(
			@ApiParam(required = false, name = "expressNum", value = "快递单号") @RequestParam(value = "expressNum", required = false) String expressNum,
			HttpServletRequest request) {
			try {
				KdApiOrderDistinguish api = new KdApiOrderDistinguish();
				String result = api.getOrderTracesByJson(expressNum);
				JSONObject jsStr = JSONObject.parseObject(result);
				return ResponseUtil.successResultString(jsStr);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseUtil.systemErrorResultString();
			}
	}
	
	@ResponseBody
	@RequestMapping(value = "order/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改订单", httpMethod = "POST", notes = "修改订单", response = ResponseUtil.class)
	public String editAdvertisement(
			@ApiParam(name = "expressName", value = "快递公司", required = true) @RequestParam(value = "expressName", required = false) 
			String expressName,
			@ApiParam(name = "expressNameView", value = "快递公司", required = true) @RequestParam(value = "expressNameView", required = false) 
			String expressNameView,
			@ApiParam(name = "expressNum", value = "快递单号", required = true) @RequestParam(value = "expressNum", required = false) 
			String expressNum,
			@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam(value = "orderId", required = false) 
			String orderId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
//			RoleVo rv = new RoleVo(sess, "订单管理操作");
//			if(rv.getRoleFalg().equals("1")) {
				
				if (!StringUtil.isNumber(orderId) || StringUtil.isNULL(expressNum) || StringUtil.isNULL(expressName)
						|| StringUtil.isNULL(expressNameView)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				ShoppingOrderDO orderPo = shoppingOrderFacade.getShoppingOrderPoByPK(Integer.parseInt(orderId));
				if (orderPo == null) {
					return ResponseUtil.showMSGResultString("找不到订单");
				}
				if(!StringUtil.isNULL(expressNum)){
					orderPo.setExpressNum(expressNum);
					orderPo.setSendTs(System.currentTimeMillis());
					orderPo.setAutomaticTs(System.currentTimeMillis()+60*1000*60*24*15);
					orderPo.setProcess(ShoppingOrderDO.process_FaHuo);
				}
				if(!StringUtil.isNULL(expressName)){
					orderPo.setExpressName(expressName);
				}
				orderPo.setExpressNameView(expressNameView);
				int i = shoppingOrderFacade.fahuoShoppingOrderPo(orderPo);
				if (i == 1) {
					String str = "尊敬的用户，您的订单"+orderPo.getOrderNum()+"已发货。";
					NoticeDO notice = new NoticeDO(NoticeDO.type_fahuo, "发货通知", str, str, null, orderPo.getAppCode());
					notice.setUserId(orderPo.getBoughtUserId());
					noticeFacade.insertNoticePo(notice);
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "发货成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(orderId), "订单发货", "");
					recordFacade.insertRecordDO(recordDO);
					
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
//			}else{
//				return ResponseUtil.resultString("暂无权限",ResponseCodeEnum.ERROR);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "order/editAddress", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改订单地址", httpMethod = "POST", notes = "修改订单地址", response = ResponseUtil.class)
	public String editAddress(
			@ApiParam(name = "address", value = "新地址", required = true) @RequestParam(value = "address", required = false) 
			String address,
			@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam(value = "orderId", required = false) 
			String orderId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNumber(orderId)) {
				return ResponseUtil.paramErrorResultString("orderId错误");
			}
			if(StringUtil.isNULL(address)){
				return ResponseUtil.paramErrorResultString("参数错误");
			}
			ShoppingOrderDO orderPo = shoppingOrderFacade.getShoppingOrderPoByPK(Integer.parseInt(orderId));
			if (orderPo == null) {
				return ResponseUtil.showMSGResultString("找不到订单");
			}
			orderPo.setUpdateAddress(address);
			int i = shoppingOrderFacade.updateShoppingOrderPo(orderPo);
			if (i == 1) {
				//操作日志
				AccountBO account = sessionFacade.checkLoginAccountSession(request);
				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改订单地址成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(orderId), "修改订单地址", "");
				recordFacade.insertRecordDO(recordDO);
				return ResponseUtil.successResultString("修改成功");
			}
			return ResponseUtil.showMSGResultString("修改失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "orderReport/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看列表", httpMethod = "POST", notes = "查看列表", response = ResponseUtil.class)
	public Map<String, Object> getOrderReportPaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			@ApiParam(required = false, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = false) String goodsId,
			@ApiParam(required = false, name = "time", value = "time") @RequestParam(value = "time", required = false) String time,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
//			RoleVo rv = new RoleVo(sess, "订单管理查看");
//			if(rv.getRoleFalg().equals("1")) {
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
				
				//根据所有订单id查询
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				
				/**
				 * 查询条件参数
				 */
				//根据产品id查询所有订单id
				if(StringUtil.isNumber(goodsId)){
					String orderIdList = "";
					List<ShoppingOrderItemBO> list = shoppingOrderItemFacade.getOrderItemVoByGoodsId(Integer.parseInt(goodsId));
					for (ShoppingOrderItemBO shoppingOrderItemVo : list) {
						if(!StringUtil.isNULL(orderIdList)){
							orderIdList += ",";
						}
						if(!orderIdList.contains(shoppingOrderItemVo.getOrderId()+"")){
							orderIdList += shoppingOrderItemVo.getOrderId();
						}
					}
					param.put("orderIdList", orderIdList);
				}
				if(!StringUtil.isNULL(time)){
					param.put("times", time);
				}
				//判断 去掉积分付款及未付款订单
				param.put("payType", 1);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<ShoppingOrderBO> pageResult = shoppingOrderFacade.getPageShoppingOrderVo(pageEntity);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
				JSONArray jsonArray = JSON.parseArray(jsonString);
				resultMap.put("iTotalRecords", pageResult.getTotalSize());
				resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
				resultMap.put("aaData", jsonArray);
				return resultMap;
//			}else{
//				return ResponseUtil.resultMap("暂无权限",ResponseCodeEnum.ERROR);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "countReport/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看报表", httpMethod = "POST", notes = "查看列表", response = ResponseUtil.class)
	public Map<String, Object> getOrderCountReportPaging(
			@ApiParam(required = false, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = false) String goodsId,
			@ApiParam(required = false, name = "time", value = "time") @RequestParam(value = "time", required = false) String time,
			HttpServletRequest request) {
		try {
			//根据所有订单id查询
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			/**
			 * 查询条件参数
			 */
			//根据产品id查询所有订单id
			if(StringUtil.isNumber(goodsId)){
				String orderIdList = "";
				List<ShoppingOrderItemBO> list = shoppingOrderItemFacade.getOrderItemVoByGoodsId(Integer.parseInt(goodsId));
				for (ShoppingOrderItemBO shoppingOrderItemVo : list) {
					if(!StringUtil.isNULL(orderIdList)){
						orderIdList += ",";
					}
					if(!orderIdList.contains(shoppingOrderItemVo.getOrderId()+"")){
						orderIdList += shoppingOrderItemVo.getOrderId();
					}
				}
				param.put("orderIdList", orderIdList);
			}
			if(!StringUtil.isNULL(time)){
				param.put("times", time);
			}
			//判断 去掉积分付款及未付款订单
			param.put("processInt", ShoppingOrderDO.process_XiaDan);
			DecimalFormat df = new DecimalFormat("######0.00");
			/**
			 * 查询
			 */
			int orderSum = 0;//订单数量
			int quantity = 0;//销量
			double saleNumber = 0.0;//营业额
			double scoreMoney = 0.0;//积分支付总额
			double purchasePrice = 0.0;//成本
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<ShoppingOrderDO> list = shoppingOrderFacade.getShoppingOrderList(param);
			if(list!=null && list.size()>0){
				//订单数量
				orderSum = list.size();
				for (ShoppingOrderDO shoppingOrderPo : list) {
					//销量数量
					List<String> goodsIdList = new ArrayList<String>();
					List<ShoppingOrderItemBO> saleList = shoppingOrderItemFacade.getOrderItemVoByOrderId(shoppingOrderPo.getId());
					for (ShoppingOrderItemBO shoppingOrderItemVo : saleList) {
						quantity += shoppingOrderItemVo.getQuantity();
						//获取产品
						if(!goodsIdList.contains(shoppingOrderItemVo.getGoodsId()+"")){
							goodsIdList .add(shoppingOrderItemVo.getGoodsId()+"");
						}
					}
					//营业额
					saleNumber += shoppingOrderPo.getRealTotalFee();
					//积分支付总额
					scoreMoney += shoppingOrderPo.getScoreTotal();
					//成本
					for (int i = 0; i < goodsIdList.size(); i++) {
						GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsIdList.get(i)));
						if(goodsPo != null){
							purchasePrice += goodsPo.getPurchasePrice();
						}
					}
				}
			}
			//已发快递
			param.put("process", ShoppingOrderDO.process_FaHuo);
			int countSend = shoppingOrderFacade.countShoppingOrder(param);
			
			resultMap.put("orderSize", orderSum);
			resultMap.put("quantity", quantity);
			resultMap.put("saleNumber", df.format(saleNumber));
			resultMap.put("purchasePrice", df.format(purchasePrice));
			resultMap.put("scoreMoney", df.format(scoreMoney));
			resultMap.put("netMoney", df.format(saleNumber+scoreMoney-purchasePrice));
			resultMap.put("countSend", countSend);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}
	
	@ResponseBody
    @RequestMapping(value = "goodsReportDT/DTPaging", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "分页查询产品", httpMethod = "POST", notes = "分页查询红包", response = ResponseUtil.class)
    public Map<String, Object> purSumDtPaging(
    		@ApiParam(required = false, name = "time", value = "time") @RequestParam(value = "time", required = false) String time,
    		@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
    		HttpServletRequest request) {
    	try {
    		/**
    		 * 查询条件参数
    		 */
    		Map<String, Object> param = new HashMap<String, Object>();
    		if(!StringUtil.isNULL(time)){
    			param.put("times", time);
    		}
    		if (!StringUtil.isNULL(iDisplayStart) && Integer.valueOf(iDisplayStart) > 0) {
    			int startPage = Integer.valueOf(iDisplayStart) / Integer.valueOf(iDisplayLength) + 1;
    			param.put("startPage", startPage);
			}
    		if (!StringUtil.isNULL(iDisplayLength)) {
				param.put("limitNum", iDisplayLength);
			}
    		param.put("deleted", 0);
    		/**
    		 * 查询
    		 */
    		int listSize = 0;
    		List<ShoppingOrderItemDO> list = shoppingOrderItemFacade.getPoBySQLAdapter(param);
    		
    		List<ShoppingOrderItemDO> listCount = shoppingOrderItemFacade.getCountPoBySQLAdapter(param);
    		
    		if(listCount!=null && listCount.size()>0){
    			listSize = listCount.size();
    		}
//    		if(list!=null && list.size()>0){
//    			
//    		}
    		Map<String, Object> resultMap = new HashMap<String, Object>();
    		String jsonString = JSON.toJSONString(list, true);
    		JSONArray jsonArray = JSON.parseArray(jsonString);
    		resultMap.put("iTotalRecords", listSize);
    		resultMap.put("iTotalDisplayRecords", listSize);
    		resultMap.put("aaData", jsonArray);
    		return resultMap;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseUtil.systemErrorResultMap();
    	}
    }
}
