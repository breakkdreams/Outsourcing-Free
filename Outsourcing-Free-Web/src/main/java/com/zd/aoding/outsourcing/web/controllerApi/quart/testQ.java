package com.zd.aoding.outsourcing.web.controllerApi.quart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.StatisticDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.StatisticFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;

/**
 * @ClassName: UserVIPTeamQuarzt
 * @Description: 月 底统计 用户团队 达标返利
 * @author: HCD
 * @date: 2017年3月28日 上午10:48:28
 */
public class testQ {
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private StatisticFacade statisticFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private RecordFacade recordFacade;

	/**
	 * @Title: shouhuoWork
	 * @Description:每日执行自动收货15天前的所有订单
	 * @return: void
	 */
	public void shouhuoWork() {
		Map<String, Object> param = new HashMap<String, Object>();
		/**
		 * 获取当前时间的15天前时间
		 */
		int day = 15;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		/**
		 * 获取这一天开始时间和结束时间
		 */
		Date beginTime = DateUtil.getBeginDateTime(cal.getTime());
		Date endTime = DateUtil.getEndDateTime(cal.getTime());
		param.put("beginTime", beginTime.getTime());
		param.put("endTime", endTime.getTime());
		param.put("process", ShoppingOrderDO.process_FaHuo);
		param.put("deleted", 0);
		/**
		 *  do...while遍历
		 */
		PageEntity pageEntity = new PageEntity(1, 20, param);
		int i = 0;
		int j = 1;
		do {
			pageEntity.setPage(j);
			j++;
			PageResult<ShoppingOrderDO> pageResult = shoppingOrderFacade.getPageOrderPo(pageEntity);
			List<ShoppingOrderDO> list = pageResult.getResultList();
			if (list != null && list.size() > 0) {
				for (ShoppingOrderDO shoppingOrderPo : list) {
					System.out.println("shoppingOrderPo = " + shoppingOrderPo);
					int k = shoppingOrderFacade.checkReceive(shoppingOrderPo.getId(), 1);
					if(k != 1){
						LogUtil.dataError("自动收货失败 orderId="+shoppingOrderPo.getId(), getClass());
					}
				}
			}else{
				System.out.println(beginTime+"-----"+endTime+"无记录");
			}
			i = pageResult.getResultList().size();
		} while (i > 0);
	}
	
	/**
	 * @Title: haopingWork
	 * @Description:每日执行自动好评7天前的所有订单
	 * @return: void
	 */
	public void haopingWork() {
		
	}
	public void testWork() {
//		System.out.println("aaaaaaaaaaaaaaaaaaaa");
//		System.out.println("ccccccccccccccccccc");
	}
	public void baobiaoWork() {
		//获取上一天日期
		 Date date=new Date();//取时间
	     Calendar calendar = new GregorianCalendar();
	     calendar.setTime(date);
	     calendar.add(calendar.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
	     date=calendar.getTime(); 
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	     String dateString = formatter.format(date);
	     
	     //参数
	 	Map<String, Object> param = new HashMap<String, Object>();
		param.put("deleted", 0);
		param.put("times", dateString);
		param.put("processInt", ShoppingOrderDO.process_XiaDan);
		
		//查询
		int orderSum = 0;//订单数量
		int quantity = 0;//销量
		double saleNumber = 0.0;//营业额
		double scoreMoney = 0.0;//积分支付总额
		double purchasePrice = 0.0;//成本
		List<ShoppingOrderDO> list = shoppingOrderFacade.getOrderList(param);
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
		
		//插入数据库操作
		StatisticDO statisticPo = new StatisticDO();
		statisticPo.setOrderCount(orderSum);
		statisticPo.setSaleCount(quantity);
		statisticPo.setTurnover(saleNumber);
		statisticPo.setCost(purchasePrice);
		statisticPo.setNetProfit(scoreMoney+saleNumber-purchasePrice);
		statisticPo.setTime(dateString);
		statisticPo.setOrderMoney(scoreMoney);
		int i = statisticFacade.insertStatisticPo(statisticPo);
	}
	public static void main(String[] args) {
		/**
		 * 获取当前时间的7天前时间
		 */
		int day = 15;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		/**
		 * 获取这一天开始时间和结束时间
		 */
		Date beginTime = DateUtil.getBeginDateTime(cal.getTime());
		Date endTime = DateUtil.getEndDateTime(cal.getTime());
		System.out.println(beginTime);
		System.out.println(endTime);
	}
	
	/**
	 * @Title: payTimeWork
	 * @Description: 订单支付超时自动取消订单
	 * @return: void
	 */
	public void payTimeWork() {
		Long paytime = Long.valueOf(7200000L);
		//获取兑换比例
		String proportion = "1";
		SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
		if(systemparamPo != null){
			proportion = systemparamPo.getStringVale();
		}
		double d_proportion = Double.valueOf(proportion).doubleValue();
		
		Map<String, Object> param = new HashMap<String, Object>();
		/**
		 * 获取当前时间的两小时以前  查询出在这之前创建的待支付订单
		 */
		Date startTime = new Date(System.currentTimeMillis() - paytime);
		param.put("process", ShoppingOrderDO.process_XiaDan);
		param.put("deleted", 0);
		param.put("payTime", System.currentTimeMillis() - paytime);
		/**
		 *  do...while遍历
		 */
		PageEntity pageEntity = new PageEntity(1, 20, param);
		int i = 0;
		int j = 1;
		do {
			pageEntity.setPage(j);
			j++;
			PageResult<ShoppingOrderDO> pageResult = shoppingOrderFacade.getPageOrderPo(pageEntity);
			List<ShoppingOrderDO> list = pageResult.getResultList();
			if (list != null && list.size() > 0) {
				for (ShoppingOrderDO shoppingOrderPo : list) {
					System.out.println("shoppingOrderPo = " + shoppingOrderPo);
					int k = shoppingOrderFacade.cancelOrder(shoppingOrderPo.getId());
					if(k == 1){
						//用户积分
						double scoreCheck = 0.0;
						UserPurseBO userPurse = userPurseFacade.getUserPurseByUserId(shoppingOrderPo.getBoughtUserId(), "0");
						if(userPurse!=null){
							scoreCheck = userPurse.getScore();
						}
						int orderScore = shoppingOrderPo.getScoreTotal();//获取订单积分
						UserPurseDO userPurseDO = userPurseFacade.getPursePoByUserId(shoppingOrderPo.getBoughtUserId(), "0");
						userPurseDO.setScore(scoreCheck+ (orderScore / d_proportion));
						int p = userPurseFacade.updatePurse(userPurseDO);
						if(p == 1){
							if(orderScore>0){
								// 加入日志
								RecordPursesDO recordPursesDO = new RecordPursesDO("", "返回积分成功", 
										RecordPursesDO.PURSETYPE_USER,shoppingOrderPo.getId(), 
										shoppingOrderPo.getBoughtUserId(), RecordPursesDO.PURSETYPE_SCORE, RecordPursesDO.TYPE_CUT, 
										orderScore, "score", "您在商城取消订单返回积分:"+orderScore);
								int u = recordFacade.insertRecordPurseDO(recordPursesDO);
							}
						}
						System.out.println("取消订单成功");
					}else{
						LogUtil.dataError("取消订单失败 orderId="+shoppingOrderPo.getId(), getClass());
					}
				}
			}else{
				System.out.println(startTime+"-----"+"该时间以前无未支付订单记录");
			}
			i = pageResult.getResultList().size();
		} while (i > 0);
	}
}
