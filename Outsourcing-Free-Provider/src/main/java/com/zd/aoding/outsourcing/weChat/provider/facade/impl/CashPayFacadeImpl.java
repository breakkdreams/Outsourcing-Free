package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BaseOrder;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingBookDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigLocal;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountRefereeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.CashPayFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.PayFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingBookFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.UserPurseService;

@Service
public class CashPayFacadeImpl implements CashPayFacade {
	@Autowired
	private UserPurseService purseService;
	@Autowired
	private PayFacade payFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;
	@Autowired
    private ShoppingBookFacade orderBookFacade;
	@Autowired
	private AccountRefereeFacade accountRefereeFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private GoodsFacade goodsFacade;

	@Override
	public int useScorePaidOrders(String orderId, String boughtWhat, UserBO user, String appCode) {
//		Double needFee = payFacade.getTotalFee(orderId, boughtWhat);
		Double needFee = 0.0;
		if(ConfigLocal.pay_orderBook.equals(boughtWhat)){
			ShoppingBookDO shoppingBookPo = orderBookFacade.getBooKByPK(Integer.parseInt(orderId));
			if (shoppingBookPo != null) {
				needFee = shoppingBookPo.getTotalFee();
			}
        }else{
        	ShoppingOrderBO orderVo = shoppingOrderFacade.getShoppingOrderVoByPK(Integer.parseInt(orderId));
        	if (orderVo != null) {
        		needFee = orderVo.getRealTotalFee();
            }
        }
		// 总价是否为0
		if (needFee < 0) {
			return -1;
		}
		if(needFee>0){
			return -2;
		}
		// 完成支付后改变订单支付状态
		int i = payFacade.finshPaid(orderId, user.getUserId() + "", boughtWhat, BaseOrder.payType_Score);
		if(i == 1){
		    if(ConfigLocal.pay_orderBook.equals(boughtWhat)){
	            GoodsDO goodsPo = new GoodsDO();
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("shoppingOrderBookId", Integer.parseInt(orderId));
	            map.put("deletec", 0);
	            List<ShoppingOrderDO> list = shoppingOrderFacade.getShoppingOrderList(map);
	            int size = list.size();
	            for(int k = 0; k < size; k++){
	                ShoppingOrderDO orderPo = list.get(k);
	                List<ShoppingOrderItemBO> itemList = shoppingOrderItemFacade.getOrderItemVoByOrderId(orderPo.getId());
	                int itemSize = itemList.size();
	                for(int l = 0; l < itemSize; l++){
	                    ShoppingOrderItemBO itemVo = itemList.get(l);
	                    GoodsDO  newGoodsPo = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
	                    newGoodsPo.setSales(newGoodsPo.getSales() + itemVo.getQuantity());
	                    goodsFacade.updateGoodsPo(newGoodsPo);
	                }
	            }
	            goodsFacade.updateGoodsPo(goodsPo);
	        }else{
	            List<ShoppingOrderItemBO> itemList = shoppingOrderItemFacade.getOrderItemVoByOrderId(Integer.parseInt(orderId));
                int itemSize = itemList.size();
                for(int l = 0; l < itemSize; l++){
                    ShoppingOrderItemBO itemVo = itemList.get(l);
                    GoodsDO  newGoodsPo = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
                    newGoodsPo.setSales(newGoodsPo.getSales() + itemVo.getQuantity());
                    goodsFacade.updateGoodsPo(newGoodsPo);
                }
	        }
		}
		return i;
	}
	@Override
	public int useBonusPaidOrders(String orderId, String boughtWhat, UserBO user, String appCode) {
		Double needFee = payFacade.getTotalFee(orderId, boughtWhat);
		// 总价是否为0
		if (needFee <= 0) {
			return -1;
		}
		UserPurseDO purse = userPurseFacade.getPursePoByUserId(user.getUserId(), appCode);
		System.out.println(purse);
		boolean moneyEnough = true;
		moneyEnough = purse.getBonus() < needFee;
		// 钱包钱够不够
		if (moneyEnough) {
			//不够
			return 2;
		}
		UserPurseDO userPurseMapper = new UserPurseDO();
		userPurseMapper.setId(purse.getId());
		userPurseMapper.setBonus(purse.getBonus() - needFee);
		int j = purseService.update(userPurseMapper);
		// 完成支付后改变订单支付状态
		int i = payFacade.finshPaid(orderId, user.getUserId() + "", boughtWhat, BaseOrder.payType_Bonus);
		if(ConfigLocal.pay_orderBook.equals(boughtWhat)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("shoppingOrderBookId", Integer.parseInt(orderId));
            map.put("deletec", 0);
            List<ShoppingOrderDO> list = shoppingOrderFacade.getShoppingOrderList(map);
            int size = list.size();
            for(int k = 0; k < size; k++){
                ShoppingOrderDO orderPo = list.get(k);
                List<ShoppingOrderItemBO> itemList = shoppingOrderItemFacade.getOrderItemVoByOrderId(orderPo.getId());
                int itemSize = itemList.size();
                for(int l = 0; l < itemSize; l++){
                    ShoppingOrderItemBO itemVo = itemList.get(l);
                    GoodsDO  newGoodsPo = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
                    newGoodsPo.setSales(newGoodsPo.getSales() + itemVo.getQuantity());
                    goodsFacade.updateGoodsPo(newGoodsPo);
                }
            }
//            UserPurseRecordDO userPurseRecordPo = new UserPurseRecordDO();
//            userPurseRecordPo.setChangeNum(needFee);
//            userPurseRecordPo.setAddOrCut(UserPurseRecordDO.addOrCut_cut);
//            userPurseRecordPo.setRecordType(UserPurseRecordDO.recordType_SwitchToBonus);
//            userPurseRecordPo.setPurseId(purse.getId());
//            userPurseRecordPo.setUserId(user.getUserId());
//            userPurseRecordPo.setFiledName("bonus");
//            userPurseRecordPo.setSummary("尊敬的用户，您于"+sdf.format(new Date())+"消费了"+needFee+"奖金");
//            userPurseRecordPo.setAppCode(appCode);
//            userPurseRecordFacade.insertUserPurseRecord(userPurseRecordPo);
        }else{
            ShoppingOrderDO shoppingOrderPo = shoppingOrderFacade.getShoppingOrderPoByPK(Integer.parseInt(orderId));
            List<ShoppingOrderItemBO> itemList = shoppingOrderItemFacade.getOrderItemVoByOrderId(Integer.parseInt(orderId));
            int itemSize = itemList.size();
            for(int l = 0; l < itemSize; l++){
                ShoppingOrderItemBO itemVo = itemList.get(l);
                GoodsDO  newGoodsPo = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
                newGoodsPo.setSales(newGoodsPo.getSales() + itemVo.getQuantity());
                goodsFacade.updateGoodsPo(newGoodsPo);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            UserPurseRecordDO userPurseRecordPo = new UserPurseRecordDO();
//            userPurseRecordPo.setChangeNum(needFee);
//            userPurseRecordPo.setAddOrCut(UserPurseRecordPo.addOrCut_cut);
//            userPurseRecordPo.setRecordType(UserPurseRecordPo.recordType_SwitchToBonus);
//            userPurseRecordPo.setPurseId(purse.getId());
//            userPurseRecordPo.setUserId(user.getUserId());
//            userPurseRecordPo.setFiledName("bonus");
//            userPurseRecordPo.setSummary("尊敬的用户，您于"+sdf.format(new Date())+"因订单"+shoppingOrderPo.getOrderNum()+"消费了"+needFee+"奖金");
//            userPurseRecordPo.setAppCode(appCode);
//            userPurseRecordFacade.insertUserPurseRecord(userPurseRecordPo);
        }
		//消费返利
		return i;
	}
	@Override
	public int useMoneyPaidOrders(String orderId, String boughtWhat, UserBO user, String appCode) {
		Double needFee = payFacade.getCashTotalFee(orderId, boughtWhat);
		System.err.println("needFee:"+needFee);
		// 总价是否为0
		if (needFee <= 0) {
			return -1;
		}
		UserPurseDO purse = userPurseFacade.getPursePoByUserId(user.getUserId(), appCode);
		boolean moneyEnough = true;
		moneyEnough = purse.getMoney() < needFee;
		// 钱包钱够不够
		if (moneyEnough) {
			//不够
			return 2;
		}
		UserPurseDO userPurseMapper = new UserPurseDO();
		userPurseMapper.setId(purse.getId());
		userPurseMapper.setMoney(purse.getMoney() - needFee);
		int j = purseService.update(userPurseMapper);
		// 完成支付后改变订单支付状态
		int i = payFacade.finshPaid(orderId, user.getUserId() + "", boughtWhat, BaseOrder.payType_money);
		if(ConfigLocal.pay_orderBook.equals(boughtWhat)){
		    Map<String, Object> map = new HashMap<String, Object>();
            map.put("shoppingOrderBookId", Integer.parseInt(orderId));
            map.put("deletec", 0);
            List<ShoppingOrderDO> list = shoppingOrderFacade.getShoppingOrderList(map);
            int size = list.size();
            for(int k = 0; k < size; k++){
                ShoppingOrderDO orderPo = list.get(k);
                List<ShoppingOrderItemBO> itemList = shoppingOrderItemFacade.getOrderItemVoByOrderId(orderPo.getId());
                int itemSize = itemList.size();
                for(int l = 0; l < itemSize; l++){
                    ShoppingOrderItemBO itemVo = itemList.get(l);
                    GoodsDO  newGoodsPo = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
                    newGoodsPo.setSales(newGoodsPo.getSales() + itemVo.getQuantity());
                    goodsFacade.updateGoodsPo(newGoodsPo);
                }
            }
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            UserPurseRecordPo userPurseRecordPo = new UserPurseRecordPo();
//            userPurseRecordPo.setChangeNum(needFee);
//            userPurseRecordPo.setAddOrCut(UserPurseRecordPo.addOrCut_cut);
//            userPurseRecordPo.setRecordType(UserPurseRecordPo.recordType_SwitchToMoney);
//            userPurseRecordPo.setPurseId(purse.getId());
//            userPurseRecordPo.setUserId(user.getUserId());
//            userPurseRecordPo.setFiledName("money");
//            userPurseRecordPo.setSummary("尊敬的用户，您于"+sdf.format(new Date())+"消费了"+needFee+"现金");
//            userPurseRecordPo.setAppCode(appCode);
//            userPurseRecordFacade.insertUserPurseRecord(userPurseRecordPo);
        }else{
            ShoppingOrderDO shoppingOrderPo = shoppingOrderFacade.getShoppingOrderPoByPK(Integer.parseInt(orderId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<ShoppingOrderItemBO> itemList = shoppingOrderItemFacade.getOrderItemVoByOrderId(Integer.parseInt(orderId));
            int itemSize = itemList.size();
            for(int l = 0; l < itemSize; l++){
                ShoppingOrderItemBO itemVo = itemList.get(l);
                GoodsDO  newGoodsPo = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
                newGoodsPo.setSales(newGoodsPo.getSales() + itemVo.getQuantity());
                goodsFacade.updateGoodsPo(newGoodsPo);
            }
//            UserPurseRecordPo userPurseRecordPo = new UserPurseRecordPo();
//            userPurseRecordPo.setChangeNum(needFee);
//            userPurseRecordPo.setAddOrCut(UserPurseRecordPo.addOrCut_cut);
//            userPurseRecordPo.setRecordType(UserPurseRecordPo.recordType_SwitchToMoney);
//            userPurseRecordPo.setPurseId(purse.getId());
//            userPurseRecordPo.setUserId(user.getUserId());
//            userPurseRecordPo.setFiledName("money");
//            userPurseRecordPo.setSummary("尊敬的用户，您于"+sdf.format(new Date())+"因订单"+shoppingOrderPo.getOrderNum()+"消费了"+needFee+"现金");
//            userPurseRecordPo.setAppCode(appCode);
//            userPurseRecordFacade.insertUserPurseRecord(userPurseRecordPo);
        }
		return i;
	}
	
}
