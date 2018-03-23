package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsOptionBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingCartItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartItemDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingCartItemFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ShoppingCartItemService;

@Service
public class ShoppingCatrItemFacadeImpl implements ShoppingCartItemFacade {
	@Autowired
	private ShoppingCartItemService shoppingCartItemService;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private GoodsOptionFacade goodsOptionFacade;

	@Override
	public ShoppingCartItemBO getShoppingCartItemVoByGoodsIdAndOptionId(String goodsId, String optionId, Integer shoppingCartId) {
		Map<String, Object> param = new HashMap<>();
		param.put("shoppingCartId", shoppingCartId);
		param.put("optionId", optionId);
		param.put("goodsId", goodsId);
		param.put("deleted", 0);
		ShoppingCartItemDO cartItemPo = getOnlyOneDoByMap(param);
		if(cartItemPo != null){
			return view(cartItemPo);
		}
		return null;
	}
	private ShoppingCartItemDO getOnlyOneDoByMap(Map<String, Object> param) {
		List<ShoppingCartItemDO> listItem = shoppingCartItemService.getList(param);
		if (listItem != null && listItem.size() > 0) {
			if (listItem.size() == 1) {
				return listItem.get(0);
			} else if (listItem.size() > 1) {
				LogUtil.dataError("购物item数据错误", this.getClass());
				for (int i = 1; i < listItem.size(); i++) {
					// 删除多余的
					int j = deletedCartItemPo(listItem.get(i).getId());
				}
				return listItem.get(0);
			}
		}
		return null;
	}
	@Override
	public int deletedCartItemPo(Integer cartItemId) {
		ShoppingCartItemDO shoppingCartItemPo = new ShoppingCartItemDO();
		shoppingCartItemPo.setId(cartItemId);
		shoppingCartItemPo.setDeleted(1);
		return shoppingCartItemService.update(shoppingCartItemPo);
	}
	@Override
	public int updateCarItem(ShoppingCartItemDO shoppingCartItemPo) {
		return shoppingCartItemService.update(shoppingCartItemPo);
	}
	@Override
	public int insertCarItem(ShoppingCartItemDO newShopCartItem) {
		newShopCartItem.insertInit();
		return shoppingCartItemService.insert(newShopCartItem);
	}
	@Override
	public List<ShoppingCartItemBO> getListByCartId(Integer shoppingCartId) {
		Map<String, Object> param = new HashMap<>();
		param.put("shoppingCartId", shoppingCartId);
		param.put("deleted", 0);
		List<ShoppingCartItemDO> list = shoppingCartItemService.getList(param);
		List<ShoppingCartItemBO> listVo = new ArrayList<ShoppingCartItemBO>();
		if(list != null && list.size() > 0){
			for(ShoppingCartItemDO shoppingCartItemPo : list){
				ShoppingCartItemBO shoppingCartItemVo = view(shoppingCartItemPo);
				listVo.add(shoppingCartItemVo);
			}
		}
		return listVo;
	}
	@Override
	public ShoppingCartItemBO getItemVoByPK(Integer cartItemId) {
		ShoppingCartItemDO cartItem = shoppingCartItemService.get(cartItemId);
		return view(cartItem);
	}
	@Override
	public ShoppingCartItemDO getCartItemPoByPK(Integer cartItemId) {
		return shoppingCartItemService.get(cartItemId);
	}
	@Override
	public PageResult<ShoppingCartItemBO> getPageShoppingCartItemVo(
			PageEntity pageEntity) {
		PageResult<ShoppingCartItemBO> pageResult = new PageResult<ShoppingCartItemBO>();
		List<ShoppingCartItemDO> list = shoppingCartItemService.getPagination(pageEntity);
		List<ShoppingCartItemBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (ShoppingCartItemDO shoppingCartItemPo : list) {
				ShoppingCartItemBO cartItemVo = view(shoppingCartItemPo);
				listVo.add(cartItemVo);
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(shoppingCartItemService.count(pageEntity.getParams()));
		return pageResult;
	}
	@Override
	public int countCartItem(Integer userId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("deleted", 0);
			return shoppingCartItemService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ShoppingCartItemBO> getListCartItemByMap(Map<String, Object> param) {
		List<ShoppingCartItemDO> list = shoppingCartItemService.getList(param);
		List<ShoppingCartItemBO> listVo = new ArrayList<ShoppingCartItemBO>();
		if(list != null && list.size() > 0){
			for(ShoppingCartItemDO shoppingCartItemPo : list){
				ShoppingCartItemBO shoppingCartItemVo = new ShoppingCartItemBO(shoppingCartItemPo);
				listVo.add(shoppingCartItemVo);
			}
		}
		return listVo;
	}
	
	private ShoppingCartItemBO view(ShoppingCartItemDO shoppingCartItemPo){
		ShoppingCartItemBO cartItemVo = new ShoppingCartItemBO(shoppingCartItemPo);
		int goodsType = 0;
		int rebate = 0;
		double scorePrice = 0.0;
		double bonusPrice = 0.0;
		String dealerName = "";
		Integer goodsStatus = 0;
		GoodsDO goods = goodsFacade.getGoodsPoByPK(shoppingCartItemPo.getGoodsId());
		Integer stock = 0;
		if(goods != null){
			goodsType = goods.getType();
			if((GoodsDO.STATUS_SHALL+"").equals(goods.getStatusAdmin()+"")){
				goodsStatus = 1;
			}else{
				goodsStatus = 2;
			}
			rebate = goods.getRebate();
		}
		GoodsOptionBO option = goodsOptionFacade.getOptionVoByPK(shoppingCartItemPo.getOptionId());
		if(option != null){
			scorePrice = option.getScorePrice();
			bonusPrice = option.getBonusPrice();
			stock = option.getStock();
		}else{
		    scorePrice = goods.getScorePrice();
            bonusPrice = goods.getBonusPrice();
			stock = goods.getShowStock();
		}
		cartItemVo.setDealerName(dealerName);
		cartItemVo.setIntegralDeductible(goods.getIntegralDeductible());
		cartItemVo.setIntegralPercent(goods.getIntegralPercent());
		cartItemVo.setStock(stock);
		cartItemVo.setGoodsType(goodsType);
		cartItemVo.setScorePrice(scorePrice);
		cartItemVo.setBonusPrice(bonusPrice);
		cartItemVo.setGoodsStatus(goodsStatus);
		cartItemVo.setRebate(rebate);
		return cartItemVo;
	}
}
