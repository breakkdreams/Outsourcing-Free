package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderItemDO;

public interface ShoppingOrderItemFacade {
	/**
	 * @Title: insertOrderItem
	 * @Description: 插入订单详情
	 * @param orderItemPo
	 * @return
	 * @return: int
	 */
	int insertOrderItem(ShoppingOrderItemDO orderItemPo);
	/**
	 * @Title: getOrderItemVoByOrderId
	 * @Description: 根据订单Id查询订单详情
	 * @param orderId
	 * @return
	 * @return: List<ShoppingOrderItemVo>
	 */
	List<ShoppingOrderItemBO> getOrderItemVoByOrderId(Integer orderId);
	List<ShoppingOrderItemBO> getOrderItemVoByOrderBookId(Integer orderBookId);
	
	List<ShoppingOrderItemBO> getOrderItemVoByGoodsId(Integer goodsId);
	ShoppingOrderItemDO getOrderItemPoById(Integer itemId);
	
	List<ShoppingOrderItemDO> getPoBySQLAdapter(Map<String, Object> param);
	List<ShoppingOrderItemDO> getCountPoBySQLAdapter(Map<String, Object> param);
	
	PageResult<ShoppingOrderItemBO> getPageShoppingOrderItemVo(PageEntity pageEntity);
}
