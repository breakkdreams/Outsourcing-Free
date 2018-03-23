package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;

public interface ShoppingOrderFacade {


	/**
	 * 根据主键查询
	 * @param getShoppingOrderPoByPK
	 * @return
	 */
	ShoppingOrderDO getShoppingOrderPoByPK(Integer id);
	ShoppingOrderBO getShoppingOrderVoByPK(Integer id);
	
	/**
	 * 更新
	 * @param shoppingOrderPo
	 * @return
	 */
	int updateShoppingOrderPo(ShoppingOrderDO shoppingOrderPo);
	int fahuoShoppingOrderPo(ShoppingOrderDO shoppingOrderPo);
	
	ShoppingOrderBO getOrderVoByPK(Integer orderId);
	
	/**
	 * @Title: getPageShoppingOrderVo 
	 * @Description: 条件分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<ShoppingOrderVo>
	 */
	PageResult<ShoppingOrderBO> getPageShoppingOrderVo(PageEntity pageEntity);
	
	PageResult<ShoppingOrderBO> getPageOrderVo(PageEntity pageEntity);
	
	List<ShoppingOrderDO> getShoppingOrderList(Map<String, Object> param);
	
	/**
	 * @Title: countBanner 
	 * @Description: 根据条件统计
	 * @param param
	 * @return
	 * @return: int
	 */
	int countShoppingOrder(Map<String, Object> param);
	
	int order(ShoppingOrderDO orderPo);
	
	int updateOrderPo(ShoppingOrderDO orderPo);
	
	void changeShowStock(Integer orderId);
	
	int checkReceive(Integer shoppingOrderId, Integer autoShouhuo);
	
	int cancelOrder(Integer shoppingOrderId);
	
	int deletedOrder(Integer shoppingOrderId);
	
	Map<String, Object> countUserOrderNum(Integer userId);
	
	/** 
	 * @Title: paidOrderByShoppingOrderId 
	 * @Description: 订单簿完成支付
	 * @param orderBookId
	 * @return
	 * @return: int
	 */
	int paidOrderByShoppingOrderId(String orderBookId);
	/** 
	 * @Title: paidByOrderId 
	 * @Description: 订单完成支付
	 * @param orderId
	 * @return: void
	 */
	int paidByOrderId(String orderId);
	/** 
	 * @Title: updateOrderPayTypeByBookId 
	 * @Description: 根据bookId修改支付类型
	 * @param userId
	 * @return
	 * @return: int
	 */
	int updateOrderPayTypeByBookId(Integer bookId,Integer payType);
	
	PageResult<ShoppingOrderDO> getPageOrderPo(PageEntity pageEntity);
	
	List<ShoppingOrderDO> getOrderList(Map<String, Object> map);
	
}
