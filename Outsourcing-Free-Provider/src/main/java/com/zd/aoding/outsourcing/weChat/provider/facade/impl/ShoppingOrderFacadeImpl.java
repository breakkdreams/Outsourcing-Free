package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserAddressBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BaseOrder;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MailDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.NoticeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.AddressFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.MailFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.NoticeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ShoppingOrderService;


@Service
public class ShoppingOrderFacadeImpl implements ShoppingOrderFacade {
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private AddressFacade addressFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private ShoppingOrderItemFacade itemFacade;
	@Autowired
	private GoodsOptionFacade goodsOptionFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private MailFacade mailFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private ShoppingOrderItemFacade orderItemFacade;
	@Autowired
	private NoticeFacade noticeFacade;

	@Override
	public ShoppingOrderDO getShoppingOrderPoByPK(Integer id) {
		try {
			ShoppingOrderDO shoppingOrderPo = shoppingOrderService.get(id);
			if (shoppingOrderPo != null) {
				return shoppingOrderPo;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ShoppingOrderBO getShoppingOrderVoByPK(Integer id) {
		try {
			ShoppingOrderDO shoppingOrderPo = shoppingOrderService.get(id);
			if (shoppingOrderPo != null) {
				return view(shoppingOrderPo);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ShoppingOrderBO getOrderVoByPK(Integer orderId) {
		ShoppingOrderDO orderPo = shoppingOrderService.get(orderId);
		if (orderPo != null) {
		    
			ShoppingOrderBO orderVo = new ShoppingOrderBO(orderPo, ShoppingOrderBO.TO_ALL);
			List<ShoppingOrderItemBO> shoppingOrderItemVoList = orderItemFacade.getOrderItemVoByOrderId(orderPo.getId());
			int goodsNum = 0;
			if(shoppingOrderItemVoList != null){
				for(ShoppingOrderItemBO shoppingOrderItemVo : shoppingOrderItemVoList){
					goodsNum += shoppingOrderItemVo.getQuantity();
				}
			}
			orderVo.setGoodsNum(goodsNum);
			orderVo.setShoppingOrderItemVoList(shoppingOrderItemVoList);
			String address = "";
			String consignee = "";
			String phone = "";
			UserAddressBO addressVo = addressFacade.getAddressVoByPK(orderPo.getBoughtAddressId()+"");
			if(addressVo != null){
				address = addressVo.getFullAddress();
				consignee = addressVo.getConsignee();
				phone = addressVo.getPhone();
			}
			if(!StringUtil.isNULL(orderPo.getUpdateAddress())){
				address = orderPo.getUpdateAddress();
				consignee = orderPo.getUpdateConsignee();
				phone = orderPo.getUpdatePhone();
			}
			if(!StringUtil.isNULL(orderPo.getUpdateAddress())){
				address = orderPo.getUpdateAddress();
			}
			Integer dealerId = orderPo.getDealerId();
			orderVo.setAddress(address);
			orderVo.setPhone(phone);
			orderVo.setConsignee(consignee);
			return orderVo;
		} else {
			return null;
		}
	}

	@Override
	public int updateShoppingOrderPo(ShoppingOrderDO shoppingOrderPo) {
		try {
			return shoppingOrderService.update(shoppingOrderPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int fahuoShoppingOrderPo(ShoppingOrderDO shoppingOrderPo) {
		try {
			int i = shoppingOrderService.update(shoppingOrderPo);
			if(i == 1){
				List<ShoppingOrderItemBO> itemList = itemFacade.getOrderItemVoByOrderId(shoppingOrderPo.getId());
				if(itemList != null && itemList.size() > 0){
					for(ShoppingOrderItemBO itemVo : itemList){
						/** 产品减库存 待发货数量 */
						GoodsDO goods = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
//						int goodsShowStock = goods.getShowStock() - itemVo.getQuantity();
//						int goodsTotalStock = goods.getTotalStock() - itemVo.getQuantity();
//						goods.setShowStock(goodsShowStock);
//						goods.setTotalStock(goodsTotalStock);
						goods.setWaitNum(goods.getWaitNum() - itemVo.getQuantity());
						goodsFacade.updateGoodsPo(goods);
						/** 配置减库存 */
						GoodsOptionDO option = goodsOptionFacade.getOptionPoByPK(itemVo.getOptionId());
//						int optionStock = option.getStock() - itemVo.getQuantity();
//						option.setStock(optionStock);
//						goodsOptionFacade.updateOptionByPk(option);
						/** 获取预警库存 */
						SystemparamDO systemparam = systemparamFacade.getSystemparamPoByCode("stockEarlyWarning");
						Integer stockEarlyWarning = systemparam.getIntVale();
//						Integer stockEarlyWarning = sessionFacade.checkSystemParamMemcache("stockEarlyWarning");
//						if(stockEarlyWarning == null){
//							SystemparamPo systemparam = systemparamFacade.getSystemparamPoByCode("stockEarlyWarning");
//							sessionFacade.saveSystemParamMemcache("stockEarlyWarning", systemparam.getIntVale());
//							stockEarlyWarning = systemparam.getIntVale();
//						}
						if(goods.getTotalStock() <= stockEarlyWarning){
							/** 库存不足站内信 */
							String message = "产品："+goods.getTitle()+"，库存不足，请及时补货！";
							MailDO mailPo = new MailDO(MailDO.TYPE_DEALER, shoppingOrderPo.getDealerId(), message, MailDO.STATUS_DEFAULT, MailDO.STATUS_DEFAULT, MailDO.contentType_stock);
							mailFacade.addMail(mailPo);
						}
						if(option.getStock() <= stockEarlyWarning){
							/** 库存不足站内信 */
							String message = "产品："+goods.getTitle()+"的配置："+option.getTitle()+"，库存不足，请及时补货！";
							MailDO mailPo = new MailDO(MailDO.TYPE_DEALER, shoppingOrderPo.getDealerId(), message, MailDO.STATUS_DEFAULT, MailDO.STATUS_DEFAULT, MailDO.contentType_stock);
							mailFacade.addMail(mailPo);
						}
					}
				}
				
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public PageResult<ShoppingOrderBO> getPageOrderVo(PageEntity pageEntity) {
		PageResult<ShoppingOrderBO> pageResult = new PageResult<ShoppingOrderBO>();
		List<ShoppingOrderDO> list = shoppingOrderService.getPagination(pageEntity);
		List<ShoppingOrderBO> listVo = new ArrayList<>();
		boolean bool = false;
		if (list != null && list.size() > 0) {
		    if(pageEntity.getParams().get("process") == BaseOrder.process_XiaDan){
	            bool = true;
	        }
			for (ShoppingOrderDO shoppingOrderPo : list) {
			    if(bool){
			        long time = shoppingOrderPo.getAutomaticTs();
			        long newTime = System.currentTimeMillis();
			        if(newTime >= time){
			            shoppingOrderPo.setIsPay(ShoppingOrderDO.isPay_no);
			            shoppingOrderService.update(shoppingOrderPo);
			            cancelOrder(shoppingOrderPo.getId());
			        }
			    }else{
			        List<ShoppingOrderItemBO> shoppingOrderItemVoList = orderItemFacade.getOrderItemVoByOrderId(shoppingOrderPo.getId());
	                int goodsNum = 0;
	                if(shoppingOrderItemVoList != null){
	                    for(ShoppingOrderItemBO shoppingOrderItemVo : shoppingOrderItemVoList){
	                        goodsNum += shoppingOrderItemVo.getQuantity();
	                    }
	                }
	                ShoppingOrderBO orderVo = new ShoppingOrderBO(shoppingOrderPo, ShoppingOrderBO.TO_PAGING);
	                UserAddressBO addressVo = addressFacade.getAddressVoByPK(shoppingOrderPo.getBoughtAddressId()+"");
	                orderVo.setGoodsNum(goodsNum);
	                if(addressVo != null){
	                    orderVo.setConsignee(addressVo.getConsignee());
	                }else{
	                    orderVo.setConsignee("");
	                }
	                orderVo.setShoppingOrderItemVoList(shoppingOrderItemVoList);
	                listVo.add(orderVo);
			    }
			}
			if(bool){
			    List<ShoppingOrderDO> newList = shoppingOrderService.getPagination(pageEntity);
			    for (ShoppingOrderDO shoppingOrderPo : newList) {
			        List<ShoppingOrderItemBO> shoppingOrderItemVoList = orderItemFacade.getOrderItemVoByOrderId(shoppingOrderPo.getId());
                    int goodsNum = 0;
                    if(shoppingOrderItemVoList != null){
                        for(ShoppingOrderItemBO shoppingOrderItemVo : shoppingOrderItemVoList){
                            goodsNum += shoppingOrderItemVo.getQuantity();
                        }
                    }
                    ShoppingOrderBO orderVo = new ShoppingOrderBO(shoppingOrderPo, ShoppingOrderBO.TO_PAGING);
                    UserAddressBO addressVo = addressFacade.getAddressVoByPK(shoppingOrderPo.getBoughtAddressId()+"");
                    orderVo.setGoodsNum(goodsNum);
                    if(addressVo != null){
                        orderVo.setConsignee(addressVo.getConsignee());
                    }else{
                        orderVo.setConsignee("");
                    }
                    orderVo.setShoppingOrderItemVoList(shoppingOrderItemVoList);
                    listVo.add(orderVo);
			    }
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(shoppingOrderService.count(pageEntity.getParams()));
		return pageResult;
	}
	
	@Override
	public PageResult<ShoppingOrderBO> getPageShoppingOrderVo(PageEntity pageEntity) {
		PageResult<ShoppingOrderBO> pageResult = new PageResult<ShoppingOrderBO>();
		List<ShoppingOrderDO> list = shoppingOrderService.getPagination(pageEntity);
		List<ShoppingOrderBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (ShoppingOrderDO shoppingOrderPo : list) {
				listVo.add(view(shoppingOrderPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(shoppingOrderService.count(pageEntity.getParams()));
		return pageResult;
	}
	
	private ShoppingOrderBO view(ShoppingOrderDO shoppingOrderPo){
		ShoppingOrderBO shoppingOrderVo = new ShoppingOrderBO(shoppingOrderPo,ShoppingOrderBO.TO_ALL);
		String username = "";
		String consignee = "";
		String consigneePhone = "";
		String address = "";
		String orderTime = DateUtil.Format("yyyy-MM-dd HH:mm:ss", new Date(shoppingOrderPo.getOrderTs()));
		String app = "";
		String expressNum = shoppingOrderPo.getExpressNum();
		String expressName = shoppingOrderPo.getExpressName();
		String newAddress = shoppingOrderPo.getUpdateAddress();
		int addressId = shoppingOrderPo.getBoughtAddressId();
		UserAddressBO addressVo = addressFacade.getAddressVoByPK(addressId+"");
		if(addressVo != null){
			consignee = addressVo.getConsignee();
			consigneePhone = addressVo.getPhone();
			if(addressVo.getFullAddress() != null){
				address = addressVo.getFullAddress();
			}
		}
		int userId = shoppingOrderPo.getBoughtUserId();
		UserDO user = userFacade.getUserByUserId(userId);
		if(user != null){
			username = user.getNickName();
		}
		shoppingOrderVo.setUsername(username);
		shoppingOrderVo.setConsignee(consignee);
		shoppingOrderVo.setConsigneePhone(consigneePhone);
		shoppingOrderVo.setAddress(address);
		shoppingOrderVo.setOrderTime(orderTime);
		shoppingOrderVo.setApp(app);
		shoppingOrderVo.setExpressNum(expressNum);
		shoppingOrderVo.setExpressName(expressName);
		shoppingOrderVo.setNewAddress(newAddress);
		shoppingOrderVo.setShoppingOrderPo(shoppingOrderPo);
		return shoppingOrderVo;
	}
	@Override
	public List<ShoppingOrderDO> getShoppingOrderList(Map<String, Object> param) {
		List<ShoppingOrderDO> list = shoppingOrderService.getList(param);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public int countShoppingOrder(Map<String, Object> param) {
		try {
			return shoppingOrderService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int order(ShoppingOrderDO orderPo) {
		orderPo.insertInit();
		orderPo.setOrderNum(System.currentTimeMillis());
		orderPo.setAutomaticTs(System.currentTimeMillis()+7200000);
		int i = shoppingOrderService.insert(orderPo);
		if(i == 1){
//			 /** 下单成功修改产品展示库存和待发货  */
//			changeShowStock(orderPo.getId());
		}
		return i;
	}
	
	@Override
	public int updateOrderPo(ShoppingOrderDO orderPo) {
		return shoppingOrderService.update(orderPo);
	}
	
	@Override
	public int cancelOrder(Integer shoppingOrderId) {
		ShoppingOrderDO orderPo = new ShoppingOrderDO();
		orderPo.setId(shoppingOrderId);
		orderPo.setDeleted(1);
		int i = shoppingOrderService.update(orderPo);
		if(i == 1){
			/** 取消订单修改产品展示库存和待发货  */
		    cancelShowStock(shoppingOrderId);
		}
		return i;
	}
	
	/** 取消订单修改产品展示库存和待发货  */
    private void cancelShowStock(Integer orderId){
    	List<ShoppingOrderItemBO> itemList = orderItemFacade.getOrderItemVoByOrderId(orderId);
		if(itemList != null){
			for(ShoppingOrderItemBO itemVo : itemList){
				GoodsDO goods = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
				GoodsOptionDO goodsOption = goodsOptionFacade.getOptionPoByPK(itemVo.getOptionId());
				if(goods != null){
					goods.setShowStock(goods.getShowStock() + itemVo.getQuantity());
					goods.setTotalStock(goods.getTotalStock() + itemVo.getQuantity());
					goods.setWaitNum(goods.getWaitNum() - itemVo.getQuantity());
					int j = goodsFacade.updateGoodsPo(goods);
					if(j != 1){
						LogUtil.dataError("取消订单修改产品库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
					}
				}else{
					LogUtil.dataError("未找到产品 取消订单修改产品库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
				}
				if(goodsOption != null){
					goodsOption.setShowStock(goodsOption.getShowStock() + itemVo.getQuantity());
					goodsOption.setStock(goodsOption.getStock() + itemVo.getQuantity());
					int k = goodsOptionFacade.updateOptionByPk(goodsOption);
					if(k != 1){
						LogUtil.dataError("取消订单修改配置库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
					}
				}else{
					LogUtil.dataError("未找到配置 取消订单修改配置库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
				}
			}
		}
    }

	/** 下单成功修改产品展示库存和待发货  */
    @Override
    public void changeShowStock(Integer orderId){
		List<ShoppingOrderItemBO> itemList = orderItemFacade.getOrderItemVoByOrderId(orderId);
		if(itemList != null){
			for(ShoppingOrderItemBO itemVo : itemList){
				GoodsDO goods = goodsFacade.getGoodsPoByPK(itemVo.getGoodsId());
				GoodsOptionDO goodsOption = goodsOptionFacade.getOptionPoByPK(itemVo.getOptionId());
				if(goods != null){
					goods.setShowStock(goods.getShowStock() - itemVo.getQuantity());
					goods.setTotalStock(goods.getTotalStock() - itemVo.getQuantity());
					goods.setWaitNum(goods.getWaitNum() + itemVo.getQuantity());
					int j = goodsFacade.updateGoodsPo(goods);
					if(j != 1){
						LogUtil.dataError("下单修改产品库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
					}
				}else{
					LogUtil.dataError("未找到产品 下单修改产品库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
				}
				if(goodsOption != null){
					goodsOption.setShowStock(goodsOption.getShowStock() - itemVo.getQuantity());
					goodsOption.setStock(goodsOption.getStock() - itemVo.getQuantity());
					int k = goodsOptionFacade.updateOptionByPk(goodsOption);
					if(k != 1){
						LogUtil.dataError("下单修改配置库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
					}
				}else{
					LogUtil.dataError("未找到配置 下单修改配置库存失败itemId="+itemVo.getShoppingOrderItemId(), getClass());
				}
			}
		}else{
			LogUtil.dataError("未找到orderItem 下单修改配置库存失败orderId="+orderId, getClass());
		}
    }
    
    @Override
	public int checkReceive(Integer shoppingOrderId, Integer autoShouhuo) {
		ShoppingOrderDO order = shoppingOrderService.get(shoppingOrderId);
		if(order == null){
			return 0;
		}
		ShoppingOrderDO orderPo = new ShoppingOrderDO();
		orderPo.setId(shoppingOrderId);
		orderPo.setProcess(ShoppingOrderDO.process_ShouHuo);
		orderPo.setAutoShouhuo(autoShouhuo);
		orderPo.setTakeTs(System.currentTimeMillis());
		orderPo.setAutomaticTs(System.currentTimeMillis()+60*1000*60*24*7);
		int i = shoppingOrderService.update(orderPo);
		if(i == 1){
//			AppCompanyPo company = companyFacade.getCompanyPoByAppCode(order.getAppCode());
//			if(company != null){
//				/** 获取app钱包 */
//				CompanyPursePo companyPurse = companyPurseFacade.getCompanyPursePoByCompanyId(company.getId());
//				if(companyPurse != null){
//					/** 在company钱包中添加用户已使用积分累计 */
//					if(ShoppingOrderPo.orderType_score.equals(order.getOrderType())){
//						CompanyPursePo companyPurseScore = new CompanyPursePo();
//						companyPurseScore.setId(companyPurse.getId());
//						Double scoreShopping = companyPurse.getScoreShopping() + order.getTotalFee();
//						companyPurseScore.setScoreShopping(scoreShopping);
//						int s = companyPurseFacade.updateCompanyPursePo(companyPurseScore);
//						if(s != 1){
//							LogUtil.dataError("添加用户已使用积分累计失败orderId="+shoppingOrderId, getClass());
//						}
//					}
//					/** 在company钱包中添加用户已使用奖金累计 */
//					if(ShoppingOrderPo.orderType_bonus.equals(order.getOrderType())){
//						CompanyPursePo companyPurseScore = new CompanyPursePo();
//						companyPurseScore.setId(companyPurse.getId());
//						Double bonusShopping = companyPurse.getBonusShopping() + order.getTotalFee();
//						companyPurseScore.setBonusShopping(bonusShopping);
//						int s = companyPurseFacade.updateCompanyPursePo(companyPurseScore);
//						if(s != 1){
//							LogUtil.dataError("添加用户已使用奖金累计失败orderId="+shoppingOrderId, getClass());
//						}
//					}
//					/** 判断为奖金订单 并且支付方式为支付宝或者现金余额  */
//					if(ShoppingOrderPo.orderType_bonus.equals(order.getOrderType()) && 
//							(ShoppingOrderPo.payType_ZFB.equals(order.getPayType()) || ShoppingOrderPo.payType_money.equals(order.getPayType()))){
//						
//						SystemparamPo systemparam = systemparamFacade.getSystemparamPoByCode("fanliRercentage");
//						if(systemparam != null){
//							Double fanliRercentage = (double) (systemparam.getIntVale() / 100);
//							/** 计算返利 */
//							Double num = payFacade.getTotalOriginalPrice(shoppingOrderId+"");
//							Double fanli = (order.getTotalFee() - num) * fanliRercentage;
//							/** 获取app钱包 将返利加入现金返利中 用于奖金额度 */
//							CompanyPursePo companyPurseUpdate = new CompanyPursePo();
//							Double cashFanli = companyPurse.getCashFanli();
//							companyPurseUpdate.setId(companyPurse.getId());
//							companyPurseUpdate.setCashFanli(cashFanli + fanli);
//							int k = companyPurseFacade.updateCompanyPursePo(companyPurseUpdate);
//							if(k != 1){
//								LogUtil.dataError("客户返利失败", getClass());
//							}
//						}else{
//							LogUtil.dataError("获取返利百分比失败", getClass());
//						}
//					}
//				}else{
//					LogUtil.dataError("未找到companyPurse，companyId="+company.getId(), getClass());
//				}
//			}else{
//				LogUtil.dataError("未找到company，appCode="+order.getAppCode(), getClass());
//			}
			/** 收货通知 */
			String str = "尊敬的用户，您的订单号"+order.getOrderNum()+"已确认收货。";
			NoticeDO notice = new NoticeDO(NoticeDO.type_shouhuo, "收货通知", str, str, null, order.getAppCode());
			notice.setUserId(order.getBoughtUserId());
			int a = noticeFacade.insertNoticePo(notice);
			if(a != 1){
				LogUtil.dataError("发送收货通知失败orderId="+order.getId(), getClass());
			}
		}
		return i;
	}
    
    @Override
	public int deletedOrder(Integer shoppingOrderId) {
		try {
			ShoppingOrderDO orderPo = new ShoppingOrderDO();
			orderPo.setId(shoppingOrderId);
			orderPo.setIsShow(1);
			return shoppingOrderService.update(orderPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
    
    @Override
	public Map<String, Object> countUserOrderNum(Integer userId) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("boughtUserId", userId);
			param.put("isShow", 0);
			param.put("deleted", 0);
//			/** 积分订单 */
//			param.put("orderType", ShoppingOrderPo.orderType_score);
			param.put("process", ShoppingOrderDO.process_XiaDan);
			int i = shoppingOrderService.count(param);
			result.put("scoreDaifukuan", i);
			param.put("process", ShoppingOrderDO.process_FuKuan);
			i = shoppingOrderService.count(param);
			result.put("scoreDaifahuo", i);
			param.put("process", ShoppingOrderDO.process_FaHuo);
			i = shoppingOrderService.count(param);
			result.put("scoreDaishouhuo", i);
			param.put("process", ShoppingOrderDO.process_ShouHuo);
			i = shoppingOrderService.count(param);
			result.put("scoreDaipingjia", i);
//			/** 奖金订单 */
//			param.put("orderType", ShoppingOrderPo.orderType_bonus);
//			param.put("process", ShoppingOrderPo.process_XiaDan);
//			i = shoppingOrderService.count(param);
//			result.put("bonusDaifukuan", i);
//			param.put("process", ShoppingOrderPo.process_FuKuan);
//			i = shoppingOrderService.count(param);
//			result.put("bonusDaifahuo", i);
//			param.put("process", ShoppingOrderPo.process_FaHuo);
//			i = shoppingOrderService.count(param);
//			result.put("bonusDaishouhuo", i);
//			param.put("process", ShoppingOrderPo.process_ShouHuo);
//			i = shoppingOrderService.count(param);
//			result.put("bonusDaipingjia", i);
//			Map<String, Object> param1 = new HashMap<String, Object>();
//			param1.put("userId", userId);
//			param1.put("statusFinsh", ReturnApplicationPo.STATUS_finish);
//			i = returnApplicationFacade.countReturnApplication(param1);
//			result.put("bonusReturnGoods", i);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    @Override
	public int paidOrderByShoppingOrderId(String orderBookId) {
		Map<String, Object> param = new HashMap<>();
		param.put("shoppingOrderBookId", orderBookId);
		param.put("deleted", 0);
		List<ShoppingOrderDO> list = shoppingOrderService.getList(param);
		int i = list.size() + 1;
		for (ShoppingOrderDO mapper : list) {
			i = i - paidByOrderId(mapper.getId());
		}
		return i;
	}
    
    @Override
	public int paidByOrderId(String orderId) {
		return paidByOrderId(Integer.parseInt(orderId));
	}
	public int paidByOrderId(Integer orderId) {
		ShoppingOrderDO mapper = new ShoppingOrderDO();
		mapper.setId(orderId);
		mapper.setProcess(ShoppingOrderDO.process_FuKuan);
		mapper.setPayTs(System.currentTimeMillis());
		int i = shoppingOrderService.update(mapper);
		if(i == 1){
			/** 添加下单通知 */
			ShoppingOrderDO order = shoppingOrderService.get(orderId);
			String str = "尊敬的用户，恭喜您下单成功，订单号为"+order.getOrderNum()+"商家正在为您处理订单。";
			NoticeDO notice = new NoticeDO(NoticeDO.type_xiadan, "下单通知", str, str, null, order.getAppCode());
			notice.setUserId(order.getBoughtUserId());
			noticeFacade.insertNoticePo(notice);
			/** 新订单站内信 */
			String message = "有新订单需要处理";
			MailDO mailPo = new MailDO(MailDO.TYPE_DEALER, order.getDealerId(), message, MailDO.STATUS_DEFAULT, MailDO.STATUS_DEFAULT, MailDO.contentType_newOrder);
			mailPo.setContentId(orderId);
			mailFacade.addMail(mailPo);
		}
		return i;
	}
	
	 @Override
	    public int updateOrderPayTypeByBookId(Integer bookId,Integer payType) {
	        try {
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("shoppingOrderBookId", bookId);
	            List<ShoppingOrderDO> list = shoppingOrderService.getList(map);
	            if(list != null){
	                int size = list.size();
	                for(int i = 0; i < size; i++){
	                    ShoppingOrderDO orderPo = new ShoppingOrderDO();
	                    orderPo.setPayType(payType);
	                    orderPo.setId(list.get(i).getId());
	                    shoppingOrderService.update(orderPo);
	                }
	                return 1;
	            }
	            return 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return -1;
	        }
	        
	    }
	 
	 @Override
		public PageResult<ShoppingOrderDO> getPageOrderPo(PageEntity pageEntity) {
			PageResult<ShoppingOrderDO> pageResult = new PageResult<ShoppingOrderDO>();
			List<ShoppingOrderDO> list = shoppingOrderService.getPagination(pageEntity);
			pageResult.setResultList(list);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(shoppingOrderService.count(pageEntity.getParams()));
			return pageResult;
		}
	 
	 @Override
	    public List<ShoppingOrderDO> getOrderList(Map<String, Object> map) {
	        return shoppingOrderService.getList(map);
	    }
}
