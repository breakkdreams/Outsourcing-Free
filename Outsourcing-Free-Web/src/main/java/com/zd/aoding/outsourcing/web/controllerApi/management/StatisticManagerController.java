package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.StatisticBO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.StatisticFacade;

@Api(value = "", description = "统计管理")
@Controller
@RequestMapping("ad/manager")
public class StatisticManagerController {
    @Autowired
    private StatisticFacade statisticFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;
	@Autowired
	private GoodsFacade goodsFacade;

    @ResponseBody
    @RequestMapping(value = "statistic/DTPaging", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询轮播图", httpMethod = "POST", response = ModelAndView.class)
    public Map<String, Object> userDTPaging(
            @ApiParam(required = false, name = "iDisplayStart", value = "当前页数") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
            @ApiParam(required = false, name = "iDisplayLength", value = "分页条数") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
            HttpServletRequest request) {
        try {
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
                PageResult<StatisticBO> pageResult = statisticFacade.getPageStatisticVo(pageEntity);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                String jsonString = JSON.toJSONString(
                        pageResult.getResultList(), true);
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
    @RequestMapping(value = "statistic/detail", method = RequestMethod.POST)
    @ApiOperation(value = "查看详情", httpMethod = "POST", response = ModelAndView.class)
    public String showGoodsDetail(
            @ApiParam(required = false, name = "timmStr", value = "日期") @RequestParam(value = "timmStr") String timmStr,
            HttpServletRequest request) {
        try {
                if(!StringUtil.isNULL(timmStr)){
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("deleted", 0);
                    param.put("times", timmStr);
                    StatisticBO statisticVo = statisticFacade.getStatisticVoByMap(param);
                    if(statisticVo==null){
                    	return null;
                    }
                    return ResponseUtil.successResultString(statisticVo);
                }
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
   
    /**
	 * @Title: oneDay
	 * @Description: 每日统计
	 * @return: void
	 */
//    @Scheduled(cron = "0 10 0 ? * *")
//	public void oneDay() {
//		StopWatch watch = new StopWatch();
//		watch.start();
//		
//		//获取上一天日期
//		 Date date=new Date();//取时间
//	     Calendar calendar = new GregorianCalendar();
//	     calendar.setTime(date);
//	     calendar.add(calendar.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
//	     date=calendar.getTime(); 
//	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//	     String dateString = formatter.format(date);
//	     
//	     //参数
//	 	Map<String, Object> param = new HashMap<String, Object>();
//		param.put("deleted", 0);
//		param.put("times", dateString);
//		param.put("payType", 1);
//		
//		//查询
//		int orderSum = 0;//订单数量
//		int quantity = 0;//销量
//		double saleNumber = 0.0;//营业额
//		double purchasePrice = 0.0;//成本
//		List<ShoppingOrderPo> list = shoppingOrderFacade.getShoppingOrderList(param);
//		if(list!=null && list.size()>0){
//			//订单数量
//			orderSum = list.size();
//			for (ShoppingOrderPo shoppingOrderPo : list) {
//				//销量数量
//				List<String> goodsIdList = new ArrayList<String>();
//				List<ShoppingOrderItemVo> saleList = shoppingOrderItemFacade.getOrderItemVoByOrderId(shoppingOrderPo.getId());
//				for (ShoppingOrderItemVo shoppingOrderItemVo : saleList) {
//					quantity += shoppingOrderItemVo.getQuantity();
//					//获取产品
//					if(!goodsIdList.contains(shoppingOrderItemVo.getGoodsId()+"")){
//						goodsIdList .add(shoppingOrderItemVo.getGoodsId()+"");
//					}
//				}
//				//营业额和毛利
//				saleNumber += shoppingOrderPo.getTotalFee();
//				//成本
//				for (int i = 0; i < goodsIdList.size(); i++) {
//					GoodsPo goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsIdList.get(i)));
//					if(goodsPo != null){
//						purchasePrice += goodsPo.getPurchasePrice();
//					}
//				}
//			}
//		}
//		
//		//插入数据库操作
//		StatisticPo statisticPo = new StatisticPo();
//		statisticPo.setOrderCount(orderSum);
//		statisticPo.setSaleCount(quantity);
//		statisticPo.setTurnover(saleNumber);
//		statisticPo.setCost(purchasePrice);
//		statisticPo.setNetProfit(saleNumber);
//		int i = statisticFacade.insertStatisticPo(statisticPo);
//		
//		watch.stop();
//
//	}
}
