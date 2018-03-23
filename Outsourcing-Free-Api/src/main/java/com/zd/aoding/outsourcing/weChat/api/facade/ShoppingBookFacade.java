package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingBookBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingBookDO;

public interface ShoppingBookFacade {
	/**
	 * @Title: insertOrderBookPo
	 * @Description: 插入book
	 * @param shoppingBookPo
	 * @return
	 * @return: int
	 */
	int insertOrderBookPo(ShoppingBookDO shoppingBookPo);
	/**
	 * @Title: getBooKByPK
	 * @Description: id查询
	 * @param orderId
	 * @return
	 * @return: ShoppingBookPo
	 */
	ShoppingBookBO getBooKVoByPK(String orderId);
	ShoppingBookDO getBooKByPK(Integer orderId);
	int updateBook(ShoppingBookDO book);
}
