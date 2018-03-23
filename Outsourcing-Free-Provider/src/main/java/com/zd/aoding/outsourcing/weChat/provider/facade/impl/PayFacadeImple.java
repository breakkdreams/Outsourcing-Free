package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountRefereeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BaseOrder;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingBookDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ZFBOrder;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigLocal;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountRefereeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.CashPayFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.PayFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingBookFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ZFBOrderService;
import com.zd.aoding.outsourcing.weChat.api.utils.file.ConfigTemp;
import com.zd.aoding.outsourcing.weChat.api.utils.file.WXConf;
import com.zd.aoding.outsourcing.weChat.api.utils.file.WXManager;
import com.zd.aoding.plugin.pay.alipay.app.AppAlipaySign;

@Service
public class PayFacadeImple implements PayFacade {
    @Autowired
    private ShoppingBookFacade shoppingBookFacade;
    @Autowired
    private ShoppingOrderFacade orderFacade;
    @Autowired
    private ShoppingOrderItemFacade shoppingOrderItemFacade;
    @Autowired
    private AppAlipaySign appAlipaySign;
    @Autowired
    private CashPayFacade cashPayFacade;
    @Autowired
    private ZFBOrderService zfbOrderService;
    @Autowired
    private WXManager wxManager;
    @Autowired
    private UserPurseFacade userPurseFacade;
    @Autowired
    private GoodsFacade goodsFacade;
    @Autowired
    private SystemparamFacade systemparamFacade;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    private AccountRefereeFacade accountRefereeFacade;
    @Autowired
	private RecordFacade recordFacade;

    @Override
    public boolean checkZfBOrderIsInsert(String notify_id) {
        Map<String, Object> param = new HashMap<>();
        param.put("notify_id", notify_id);
        List<ZFBOrder> list = zfbOrderService.getMapperList(param);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Double getTotalFee(String orderId, String boughtWhat) {
//        if (Config.isOnline == 0) {
//            System.out.println("价格写死0.01");
//            return 0.01;
//        }
        switch (boughtWhat) {
        case ConfigLocal.pay_orderBook:
            ShoppingBookDO shoppingBookPo = shoppingBookFacade
                    .getBooKByPK(Integer.parseInt(orderId));
            if (shoppingBookPo != null) {
                return shoppingBookPo.getTotalFee();
            }
            break;
        case ConfigLocal.pay_order:
            ShoppingOrderBO orderVo = orderFacade.getShoppingOrderVoByPK(Integer.parseInt(orderId));
            if (orderVo != null) {
                return orderVo.getRealTotalFee();
            }
            break;
        }
        return 0D;
    }
    
    @Override
    public Double getCashTotalFee(String orderId, String boughtWhat) {
    	System.err.println("orderId:"+orderId+",boughtWhat:"+boughtWhat);
    	switch (boughtWhat) {
    	case ConfigLocal.pay_orderBook:
    		ShoppingBookDO shoppingBookPo = shoppingBookFacade.getBooKByPK(Integer.parseInt(orderId));
    		if (shoppingBookPo != null) {
    			return shoppingBookPo.getTotalFee();
    		}
    		break;
    	case ConfigLocal.pay_order:
    		ShoppingOrderBO orderVo = orderFacade.getOrderVoByPK(Integer.parseInt(orderId));
    		if (orderVo != null) {
    			System.err.println("orderVo:"+orderVo.getRealTotalFee());
    			return orderVo.getRealTotalFee();
    		}
    		break;
    	}
    	return 0D;
    }

    @Override
    public Double getTotalOriginalPrice(String orderId) {
        List<ShoppingOrderItemBO> list = shoppingOrderItemFacade
                .getOrderItemVoByOrderId(Integer.parseInt(orderId));
        Double num = 0.00;
        for (int i = 0; i < list.size(); i++) {
        	if(list.get(i).getCostPrice() != null){
        		num += list.get(i).getCostPrice() * list.get(i).getQuantity();
        	}else{
        		LogUtil.dataError("获取产品成本价失败 goodsId = "+list.get(i).getGoodsId(), getClass());
        	}
        }
        return num;
    }

    @Override
    public Map<String, Object> preparePay(String payWay, String orderId,
            String boughtWhat, UserBO user, String appCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        Double allFee = getTotalFee(orderId, boughtWhat);
        if (allFee == null) {
            map.put("code", "-1");
            // 价格出错
            return map;
        }
        Map<String, Object> mapp = choosePaidWay(payWay, orderId, boughtWhat,
                user, allFee, appCode);
        map.put("payWay", payWay);
        map.put("code", mapp.get("code"));
        if ("200".equals(mapp.get("code"))) {
            map.put("code", "200");
            map.put("result", mapp.get("result"));
        }
        if ("201".equals(mapp.get("code"))) {
            map.put("code", "200");
            map.put("result", "余额支付成功");
        }
        if ("202".equals(mapp.get("code"))) {
            map.put("code", "202");
            map.put("result", "余额支付不足");
        }
        if ("301".equals(mapp.get("code"))) {
            map.put("code", "200");
            map.put("result", "余额支付成功");
        }
        if ("302".equals(mapp.get("code"))) {
            map.put("code", "202");
            map.put("result", "余额支付不足");
        }
        if ("203".equals(mapp.get("code"))) {
            map.put("code", "203");
            map.put("result", "prepayId == null");
        }
        return map;
    }

    private Map<String, Object> choosePaidWay(String payWay, String orderId,
            String boughtWhat, UserBO user, Double allFee, String appCode) {
        Map<String, Object> mapp = new HashMap<>();
        System.out.println("改提示语");
        String pay = "商品购买";
        try {
            String type = null;
            String out_trade_no = getOutTradeNo(orderId, boughtWhat, user);
            switch (payWay) {
            case "1":
                String sign = appAlipaySign
                        .Getpays(pay, boughtWhat,
                        		ConfigTemp.DoubleBigDecimal(allFee, 2) + "",
                                out_trade_no);
                mapp.put("code", "200");
                mapp.put("result", sign);
                return mapp;
            case "3":
                int i = cashPayFacade.useBonusPaidOrders(orderId, boughtWhat,
                        user, appCode);
                if (i == 1) {
                    mapp.put("code", "201");
                    return mapp;
                } else if (i == 2) {
                    mapp.put("code", "202");
                    return mapp;
                } else {
                    mapp.put("code", "300");
                    return mapp;
                }
            case "5":
                int j = cashPayFacade.useMoneyPaidOrders(orderId, boughtWhat,
                        user, appCode);
                System.err.println("j================:"+j);
                if (j == 1) {
                    mapp.put("code", "301");
                    return mapp;
                } else if (j == 2) {
                    mapp.put("code", "302");
                    return mapp;
                } else {
                    mapp.put("code", "300");
                    return mapp;
                }
            case "2":
                String spbill_create_ip = "127.0.0.1";
                String notify_url = WXConf.notify_url;
                String trade_type = WXConf.trade_type;
                if (out_trade_no.endsWith("_")) {
                    out_trade_no = out_trade_no + 1;
                }
                String prepayId = wxManager.unifiedorder(boughtWhat,
                        out_trade_no, orderId,
                        (int) (ConfigTemp.DoubleBigDecimal(allFee, 2) * 100),
                        spbill_create_ip, trade_type);
                System.out.println(prepayId);
                if (prepayId == null) {
                    mapp.put("code", "203");
                    mapp.put("result", prepayId);
                    return mapp;
                } else {
                    Map<String, Object> _res = wxManager.prepay(prepayId);
                    mapp.put("code", "200");
                    mapp.put("result", _res);
                    return mapp;
                }
            }
            mapp.put("code", "300");
            return mapp;
        } catch (Exception e) {
            e.printStackTrace();
            mapp.put("code", "999");
            return mapp;
        }
    }

    @Override
    public String getOutTradeNo(String orderId, String boughtWhat, UserBO user) {
        String type = "0";
        if (ConfigLocal.pay_orderBook.endsWith(boughtWhat)) {
            type = "1";
        }
        if (ConfigLocal.pay_order.endsWith(boughtWhat)) {
            type = "2";
        }
        return orderId + "_" + type + "_" + user.getUserId();
    }
    @Override
    public String getOutTradeNo(String orderId, String boughtWhat, AccountBO account) {
    	String type = "0";
    	if (ConfigLocal.pay_orderBook.endsWith(boughtWhat)) {
    		type = "1";
    	}
    	if (ConfigLocal.pay_order.endsWith(boughtWhat)) {
    		type = "2";
    	}
    	return orderId + "_" + type + "_" + account.getAccountId();
    }

    @Override
    public int finshPaid(String orderIdOrOrderBookId, String boughtAccountId,
            String boughtWhat, Integer paytype) {
        int i = 0;
        BigDecimal num100 = new BigDecimal(100);
        if (ConfigLocal.pay_orderBook.endsWith(boughtWhat)) {
            ShoppingBookDO bookPo = shoppingBookFacade.getBooKByPK(Integer
                    .parseInt(orderIdOrOrderBookId));
            if (bookPo != null) {
                ShoppingBookDO book = new ShoppingBookDO();
                book.setId(Integer.parseInt(orderIdOrOrderBookId));
                book.setStatus(ShoppingBookDO.status_Paid);
                shoppingBookFacade.updateBook(book);
                if (ShoppingBookDO.type_Shopping.equals(bookPo.getType())) {
                    i = orderFacade.paidOrderByShoppingOrderId(orderIdOrOrderBookId);
                    int y = orderFacade.updateOrderPayTypeByBookId(Integer.parseInt(orderIdOrOrderBookId), paytype);
                    if(y ==1){
                    	Double needFee = getCashTotalFee(book.getId()+"", boughtWhat);
                    	BigDecimal totalBig = new BigDecimal(needFee);//价格金额
                    	//奖金记录
                    	if(paytype == BaseOrder.payType_money){
                    		RecordPursesDO recordPursesDO_0 = new RecordPursesDO("", "余额支付成功", 
    								RecordPursesDO.PURSETYPE_USER, Integer.parseInt(orderIdOrOrderBookId), 
    								Integer.parseInt(boughtAccountId), RecordPursesDO.PURSETYPE_CASH, RecordPursesDO.TYPE_CUT, 
    								totalBig.intValue(), "money", "商城消费余额:"+totalBig.setScale(2, BigDecimal.ROUND_HALF_DOWN)+"元.");
        					recordFacade.insertRecordPurseDO(recordPursesDO_0);
                    	}
                    	ShoppingOrderDO shoppingOrderDO = new ShoppingOrderDO();
                    	Map<String, Object> mapam = new HashMap<String, Object>();
                		mapam.put("deleted",0);
                		mapam.put("shoppingOrderBookId",orderIdOrOrderBookId);
                		List<ShoppingOrderDO> listOrder = orderFacade.getShoppingOrderList(mapam);
                		if(listOrder!=null && listOrder.size()>0){
                			shoppingOrderDO = listOrder.get(0);
                		}
                    	
                    	int tt = 0;
                    	List<ShoppingOrderItemBO> list = shoppingOrderItemFacade.getOrderItemVoByOrderId(shoppingOrderDO.getId());
                    	for (ShoppingOrderItemBO shoppingOrderItemBO : list) {
                    		int goodsid = shoppingOrderItemBO.getGoodsId();
                    		GoodsDO goods = goodsFacade.getGoodsPoByPK(goodsid);
                    		if(goods!=null){
                    			if(goods.getRebate() == 1){
                    				tt += 1;
                    			}
                    		}
        				}
                    	
                    	if(tt>0){
                    		//获取返利级数
                    		int rebat_tatal = 0;
                    		SystemparamDO systemParamDO_tatal = systemparamFacade.getSystemparamPoByCode("rebateLevel");
                    		if(systemParamDO_tatal!=null){
                    			rebat_tatal = Integer.parseInt(systemParamDO_tatal.getStringVale());
                    		}
                        	//返利
                            UserDO userDO = userFacade.getUserByUserId(shoppingOrderDO.getBoughtUserId());
                            if(userDO!=null){
                            	
                            	for (int j = 0; j < rebat_tatal; j++) {
                            		String code  = "level"+(j+1);
                            		SystemparamDO systemParamDO_code = systemparamFacade.getSystemparamPoByCode(code);
                            		if(systemParamDO_code != null){
                            			AccountRefereeDO accountRefree_code = accountRefereeFacade.getAccountRefereePoByBeAccountId(userDO.getAccountId());
                            			if(accountRefree_code != null){
                            				userDO = userFacade.getUserPoByAccountId(accountRefree_code.getRefereeAccountId());
                                    		if(userDO!=null){
                                    			UserPurseDO userPurse_code = userPurseFacade.getPursePoByUserId(userDO.getId(), "0");
                                    			if(userPurse_code != null){
                                    				BigDecimal a_code = new BigDecimal(systemParamDO_code.getStringVale());//系统参数一级比率
                                    				BigDecimal resu1 = a_code.divide(num100).multiply(totalBig);//返利金额
                                    				BigDecimal userMoney_code = new BigDecimal(Double.toString(userPurse_code.getMoney()));//用户钱包金额
                                    				BigDecimal userlvMoney_code = new BigDecimal(Double.toString(userPurse_code.getLevelOneMoney()));//用户一级钱包金额
                                    				userPurse_code.setMoney(resu1.add(userMoney_code).doubleValue());
                                    				userPurse_code.setLevelOneMoney(resu1.add(userlvMoney_code).doubleValue());
                                    				int s = userPurseFacade.updatePurse(userPurse_code);
                                    				//关系表返利总额
                                    				BigDecimal total = new BigDecimal(Double.toString(accountRefree_code.getFanliTotal()));//用户钱包金额
                                    				accountRefree_code.setFanliTotal(resu1.add(total).doubleValue());
                                    				accountRefereeFacade.upadteAccountRefereePo(accountRefree_code);
                                    				if(s == 1){
                                    					//加入日志
                                    					RecordPursesDO recordPursesDO = new RecordPursesDO("", "返利成功", 
                        										RecordPursesDO.PURSETYPE_USER, 1, 
                        										userDO.getId(), RecordPursesDO.PURSETYPE_REBATE, RecordPursesDO.TYPE_ADD, 
                        										resu1.intValue(), "money", userDO.getPhone()+"消费返利回金额:"+resu1+",返利比率:"+a_code+"%。");
                                    					recordFacade.insertRecordPurseDO(recordPursesDO);
                                    				}
                                    			}
                                    		}
                            			}
                            		}
								}
                            }
                    	}
                    }
                } 
                //else if (ShoppingBookDO.type_change_money.equals(bookPo.getType())) {
                    // UserPursePo purse =
                    // userPurseFacade.getUserPursePoByUserId(bookPo.getboughtUserId(),
                    // bookPo.getAppCode());
                    // UserPursePo updatePurse = new UserPursePo();
                    // updatePurse.setId(purse.getId());
                    // updatePurse.setMoney(purse.getMoney() +
                    // bookPo.getTotalFee());
                    // i = userPurseFacade.updateUserPursePo(updatePurse);
                //}
            }
        }
        if (ConfigLocal.pay_order.endsWith(boughtWhat)) {
            i = orderFacade.paidByOrderId(orderIdOrOrderBookId);
            ShoppingOrderDO orderPo = new ShoppingOrderDO();
            orderPo.setId(Integer.parseInt(orderIdOrOrderBookId));
            orderPo.setPayType(paytype);
            int y = orderFacade.updateOrderPo(orderPo);
            if(y==1){
            	Double needFee = getCashTotalFee(orderPo.getId()+"", boughtWhat);
            	BigDecimal totalBig = new BigDecimal(needFee);//价格金额
            	
            	//奖金记录
            	if(paytype == BaseOrder.payType_money){
            		RecordPursesDO recordPursesDO_0 = new RecordPursesDO("", "余额支付成功", 
    						RecordPursesDO.PURSETYPE_USER, Integer.parseInt(orderIdOrOrderBookId), 
    						Integer.parseInt(boughtAccountId), RecordPursesDO.PURSETYPE_CASH, RecordPursesDO.TYPE_CUT, 
    						totalBig.intValue(), "money", "商城消费余额:"+totalBig.setScale(2, BigDecimal.ROUND_HALF_DOWN)+"元.");
    				recordFacade.insertRecordPurseDO(recordPursesDO_0);
            	}
				
            	int tt = 0;
            	List<ShoppingOrderItemBO> list = shoppingOrderItemFacade.getOrderItemVoByOrderId(Integer.parseInt(orderIdOrOrderBookId));
            	for (ShoppingOrderItemBO shoppingOrderItemBO : list) {
            		int goodsid = shoppingOrderItemBO.getGoodsId();
            		GoodsDO goods = goodsFacade.getGoodsPoByPK(goodsid);
            		if(goods!=null){
            			if(goods.getRebate() == 1){
            				tt += 1;
            			}
            		}
				}
            	if(tt>0){
            		//获取返利级数
            		int rebat_tatal = 0;
            		SystemparamDO systemParamDO_tatal = systemparamFacade.getSystemparamPoByCode("rebateLevel");
            		if(systemParamDO_tatal!=null){
            			rebat_tatal = Integer.parseInt(systemParamDO_tatal.getStringVale());
            		}
            		ShoppingOrderDO shoppingOrderDO = orderFacade.getShoppingOrderPoByPK(Integer.parseInt(orderIdOrOrderBookId));
                	//返利
                    UserDO userDO = userFacade.getUserByUserId(shoppingOrderDO.getBoughtUserId());
                    if(userDO!=null){
                    	
                    	for (int j = 0; j < rebat_tatal; j++) {
                    		String code  = "level"+(j+1);
                    		SystemparamDO systemParamDO_code = systemparamFacade.getSystemparamPoByCode(code);
                    		if(systemParamDO_code != null){
                    			AccountRefereeDO accountRefree_code = accountRefereeFacade.getAccountRefereePoByBeAccountId(userDO.getAccountId());
                    			if(accountRefree_code != null){
                    				userDO = userFacade.getUserPoByAccountId(accountRefree_code.getRefereeAccountId());
                            		if(userDO!=null){
                            			UserPurseDO userPurse_code = userPurseFacade.getPursePoByUserId(userDO.getId(), "0");
                            			if(userPurse_code != null){
                            				BigDecimal a_code = new BigDecimal(systemParamDO_code.getStringVale());//系统参数一级比率
                            				BigDecimal resu1 = a_code.divide(num100).multiply(totalBig);//返利金额
                            				BigDecimal userMoney_code = new BigDecimal(Double.toString(userPurse_code.getMoney()));//用户钱包金额
                            				BigDecimal userlvMoney_code = new BigDecimal(Double.toString(userPurse_code.getLevelOneMoney()));//用户一级钱包金额
                            				userPurse_code.setMoney(resu1.add(userMoney_code).doubleValue());
                            				userPurse_code.setLevelOneMoney(resu1.add(userlvMoney_code).doubleValue());
                            				int s = userPurseFacade.updatePurse(userPurse_code);
                            				//关系表返利总额
                            				BigDecimal total = new BigDecimal(Double.toString(accountRefree_code.getFanliTotal()));//用户钱包金额
                            				accountRefree_code.setFanliTotal(resu1.add(total).doubleValue());
                            				accountRefereeFacade.upadteAccountRefereePo(accountRefree_code);
                            				if(s == 1){
                            					//加入日志
                            					RecordPursesDO recordPursesDO = new RecordPursesDO("", "返利成功", 
                										RecordPursesDO.PURSETYPE_USER, 1, 
                										userDO.getId(), RecordPursesDO.PURSETYPE_REBATE, RecordPursesDO.TYPE_ADD, 
                										resu1.intValue(), "money", userDO.getPhone()+"消费返利回金额:"+resu1+",返利比率:"+a_code+"%。");
                            					recordFacade.insertRecordPurseDO(recordPursesDO);
                            				}
                            			}
                            		}
                    			}
                    		}
						}
                    }
            	}
            }
        }
        return i;
    }
    
//    private void getRebate(Integer boughtUserId,BigDecimal totalBig) {
//    	
//	}
}
