package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingCartBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartDO;

public interface ShoppingCartFacade {
	/**
	 * @Title: getUserShoppingCartPo
	 * @Description: 查询用户购物车
	 * @param userId
	 * @return
	 * @return: ShoppingCartVo
	 */
	public ShoppingCartBO getUserShoppingCartVo(Integer userId, String appCode);
	List<ShoppingCartBO> getUserShoppingCartVoAll(Integer userId, String appCode);
	/**
	 * @Title: getUserShoppingCartVoBySupplierId
	 * @Description: 查询用户购物车中是否存在该供货商
	 * @param userId  supplierId
	 * @return
	 * @return: ShoppingCartVo
	 */
	ShoppingCartBO getUserShoppingCartVoBySupplierId(Integer userId, Integer supplierId, String appCode);
	/**
	 * @Title: getUserShoppingCartVoByDealerId
	 * @Description: 查询用户购物车中是否存在该商户
	 * @param userId  supplierId
	 * @return
	 * @return: ShoppingCartVo
	 */
	ShoppingCartBO getUserShoppingCartVoByDealerId(Integer userId, Integer dealerId, String appCode, Integer type);
	/**
	 * @Title: update
	 * @Description: 更新
	 * @param updateShoppingCartPo
	 * @return
	 * @return: int
	 */
	public int update(ShoppingCartDO updateShoppingCartPo);
	/**
	 * @Title: insert
	 * @Description: 插入
	 * @param newShoppingCartPo
	 * @return
	 * @return: int
	 */
	public int insert(ShoppingCartDO newShoppingCartPo);
	/**
	 * @Title: getPageShoppingCartVo
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<ShoppingCartVo>
	 */
	public PageResult<ShoppingCartBO> getPageShoppingCartVo(PageEntity pageEntity, Integer userId);
	/**
	 * @Title: deletedCartPo
	 * @Description: 删除购物车
	 * @param shoppingCartId
	 * @return
	 * @return: int
	 */
	public int deletedCartPo(Integer shoppingCartId);
	/** 
	 * @Title: getCartPoByPK 
	 * @Description: 根据id查询
	 * @param shoppingCartId
	 * @return
	 * @return: ShoppingCartPo
	 */
	public ShoppingCartBO getCartVoByPK(Integer shoppingCartId);
	public ShoppingCartDO getCartOnlyPoByPK(Integer shoppingCartId);
	
	public List<ShoppingCartBO> getGoodsNum(Map<String, Object> param);
}
