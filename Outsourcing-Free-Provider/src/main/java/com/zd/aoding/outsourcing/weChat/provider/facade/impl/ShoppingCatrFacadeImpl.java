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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingCartBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingCartItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingCartFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingCartItemFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ShoppingCartService;

@Service
public class ShoppingCatrFacadeImpl implements ShoppingCartFacade {
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ShoppingCartItemFacade shoppingCartItemFacade;

	@Override
	public ShoppingCartBO getUserShoppingCartVo(Integer userId, String appCode) {
		Map<String, Object> param = new HashMap<>();
		param.put("boughtUserId", userId);
		param.put("appCode", appCode);
		param.put("deleted", 0);
		ShoppingCartDO shoppingCartPo = getOnlyOneByMap(param);
		if(shoppingCartPo != null){
			return new ShoppingCartBO(shoppingCartPo, ShoppingCartBO.TO_ALL);
		}
		return null;
	}
	
	@Override
	public List<ShoppingCartBO> getUserShoppingCartVoAll(Integer userId,
			String appCode) {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("boughtUserId", userId);
			param.put("appCode", appCode);
			param.put("deleted", 0);
			List<ShoppingCartDO> list = shoppingCartService.getList(param);
			List<ShoppingCartBO> listVo = new ArrayList<ShoppingCartBO>();
			if(list != null && list.size() > 0){
				for(ShoppingCartDO shoppingCartPo : list){
					ShoppingCartBO shoppingCartVo = new ShoppingCartBO(shoppingCartPo, ShoppingCartBO.TO_ALL);
					listVo.add(shoppingCartVo);
				}
			}
			return listVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ShoppingCartBO getUserShoppingCartVoBySupplierId(Integer userId,
			Integer supplierId, String appCode) {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("boughtUserId", userId);
			param.put("supplierId", supplierId);
			param.put("appCode", appCode);
			param.put("deleted", 0);
			ShoppingCartDO shoppingCartPo = getOnlyOneByMap(param);
			if(shoppingCartPo != null){
				return new ShoppingCartBO(shoppingCartPo, ShoppingCartBO.TO_ALL);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ShoppingCartBO getUserShoppingCartVoByDealerId(Integer userId,
			Integer dealerId, String appCode, Integer type) {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("boughtUserId", userId);
//			param.put("dealerId", dealerId);
//			param.put("appCode", appCode);
//			param.put("type", type);
			param.put("deleted", 0);
			ShoppingCartDO shoppingCartPo = getOnlyOneByMap(param);
			if(shoppingCartPo != null){
				return new ShoppingCartBO(shoppingCartPo, ShoppingCartBO.TO_ALL);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ShoppingCartDO getOnlyOneByMap(Map<String, Object> param) {
		// 防止未填写删除条件（后期注意）
		param.put("deleted", 0);
		List<ShoppingCartDO> listCart = shoppingCartService.getList(param);
		if (listCart != null && listCart.size() > 0) {
			if (listCart.size() == 1) {
				return listCart.get(0);
			} else if (listCart.size() > 1) {
				LogUtil.dataError("购物是数据错误", this.getClass());
				for (int i = 1; i < listCart.size(); i++) {
					// 删除多余的
					int j = deletedCartPo(listCart.get(i).getId());
				}
				return listCart.get(0);
			}
		}
		return null;
	}
	@Override
	public int deletedCartPo(Integer shoppingCartId) {
		ShoppingCartDO updateShoppingCartPo = new ShoppingCartDO();
		updateShoppingCartPo.setId(shoppingCartId);
		updateShoppingCartPo.setDeleted(1);
		return shoppingCartService.update(updateShoppingCartPo);
	}
	@Override
	public int update(ShoppingCartDO updateShoppingCartPo) {
		// TODO Auto-generated method stub
		return shoppingCartService.update(updateShoppingCartPo);
	}
	@Override
	public int insert(ShoppingCartDO newShoppingCartPo) {
		newShoppingCartPo.insertInit();
		return shoppingCartService.insert(newShoppingCartPo);
	}
	@Override
	public PageResult<ShoppingCartBO> getPageShoppingCartVo(PageEntity pageEntity, Integer userId) {
		PageResult<ShoppingCartBO> pageResult = new PageResult<ShoppingCartBO>();
		List<ShoppingCartDO> list = shoppingCartService.getPagination(pageEntity);
		List<ShoppingCartBO> listVo = new ArrayList<>();
		int totalSize = 0;
		if (list != null && list.size() > 0) {
			for (ShoppingCartDO shoppingCartPo : list) {
			    //默认为奖金产品
//			    boolean bool = true;
				ShoppingCartBO cartVo = new ShoppingCartBO(shoppingCartPo,ShoppingCartBO.TO_PAGING);
//				Integer type = cartVo.getType();
//				if(type == ShoppingCartPo.type_score){
//				    bool = false;
//				}
				List<ShoppingCartItemBO> shoppingCartItemVoList = shoppingCartItemFacade.getListByCartId(cartVo.getShoppingCartId());
				// 判断是否有详情
				if (shoppingCartItemVoList != null && shoppingCartItemVoList.size() > 0) {
				    int size = shoppingCartItemVoList.size();
				    
				    for(int i = 0; i < size; i++){
			            ShoppingCartItemBO itemVo = shoppingCartItemVoList.get(i);
                        shoppingCartItemVoList.get(i).setBonusPrice(0.00);
			            shoppingCartItemVoList.get(i).setScorePrice(0.00);
			            shoppingCartItemVoList.get(i).setIntegralPercent(itemVo.getIntegralPercent()*100);
			            shoppingCartItemVoList.get(i).setMarketPrice(itemVo.getActualPrice());
                    }
				    
//				    if(bool){
//				        for(int i = 0; i < size; i++){
//				            ShoppingCartItemVo itemVo = shoppingCartItemVoList.get(i);
//	                        shoppingCartItemVoList.get(i).setBonusPrice(itemVo.getActualPrice());
//				            shoppingCartItemVoList.get(i).setScorePrice(0.00);
//	                    }
//				    }else{
//				        for(int i = 0; i < size; i++){
//                            ShoppingCartItemVo itemVo = shoppingCartItemVoList.get(i);
//                            shoppingCartItemVoList.get(i).setBonusPrice(0.00);
//                            shoppingCartItemVoList.get(i).setScorePrice(itemVo.getActualPrice());
//                        }
//				    }
					cartVo.setShoppingCartItemVoList(shoppingCartItemVoList);
					listVo.add(cartVo);
					totalSize += shoppingCartItemVoList.size();
				} else {
					// 无则删除购物车
					deletedCartPo(cartVo.getShoppingCartId());
				}
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(totalSize);
		return pageResult;
	}
	@Override
	public ShoppingCartBO getCartVoByPK(Integer shoppingCartId) {
		ShoppingCartDO cart = shoppingCartService.get(shoppingCartId);
		return view(cart);
	}
	@Override
	public ShoppingCartDO getCartOnlyPoByPK(Integer shoppingCartId) {
		return shoppingCartService.get(shoppingCartId);
	}
	@Override
	public List<ShoppingCartBO> getGoodsNum(Map<String, Object> param) {
		List<ShoppingCartDO> list = shoppingCartService.getList(param);
		List<ShoppingCartBO> listVo = new ArrayList<ShoppingCartBO>();
		if(list != null){
			for(ShoppingCartDO shoppingCartPo : list){
				ShoppingCartBO shoppingCartVo = view(shoppingCartPo);
				listVo.add(shoppingCartVo);
			}
			return listVo;
		}
		return null;
	}
	private ShoppingCartBO view(ShoppingCartDO shoppingCartPo){
		ShoppingCartBO shoppingCartVo = new ShoppingCartBO(shoppingCartPo, ShoppingCartBO.TO_PAGING);
		String dealerName = "";
		String dealerPhone = "";
		shoppingCartVo.setDealerPhone(dealerPhone);
		shoppingCartVo.setDealerName(dealerName);
		return shoppingCartVo;
	}
}
