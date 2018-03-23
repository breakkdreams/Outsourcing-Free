package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingCartItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartItemDO;

public interface ShoppingCartItemFacade {
	/**
	 * @Title: getShoppingCartItemVoByGoodsIdAndOptionId
	 * @Description: 查看用户购物车中是否有该配置产品
	 * @param goodsId
	 * @param optionId
	 * @param shoppingCartId
	 * @return
	 * @return: ShoppingCartItemVo
	 */
	ShoppingCartItemBO getShoppingCartItemVoByGoodsIdAndOptionId(String goodsId, String optionId, Integer shoppingCartId);
	/**
	 * @Title: updateItem
	 * @Description: 更新购物车明细
	 * @param shoppingCartItemPo
	 * @return
	 * @return: int
	 */
	int updateCarItem(ShoppingCartItemDO shoppingCartItemPo);
	/**
	 * @Title: insertCarItem
	 * @Description: 插入购物车明细
	 * @param newShopCartItem
	 * @return
	 * @return: int
	 */
	int insertCarItem(ShoppingCartItemDO newShopCartItem);
	/**
	 * @Title: getListByCartId
	 * @Description: 根据购物车id查询明细
	 * @param shoppingCartId
	 * @return
	 * @return: List<ShoppingCartItemPo>
	 */
	List<ShoppingCartItemBO> getListByCartId(Integer shoppingCartId);
	/**
	 * @Title: getItemPoByPK
	 * @Description: id查询
	 * @param cartItemId
	 * @return
	 * @return: ShoppingCartItemPo
	 */
	ShoppingCartItemBO getItemVoByPK(Integer cartItemId);
	ShoppingCartItemDO getCartItemPoByPK(Integer cartItemId);
	/**
	 * @Title: deletedCartItemPo
	 * @Description: 删除
	 * @param cartItemId
	 * @return
	 * @return: int
	 */
	int deletedCartItemPo(Integer cartItemId);
	/**
	 * @Title: getPageShoppingCartItemVo
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<ShoppingCartItemVo>
	 */
	PageResult<ShoppingCartItemBO> getPageShoppingCartItemVo(PageEntity pageEntity);
	
	int countCartItem(Integer userId);
	
	List<ShoppingCartItemBO> getListCartItemByMap(Map<String, Object> param);
}
