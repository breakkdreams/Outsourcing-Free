package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderItemDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ShoppingOrderItemService;

@Service
public class ShoppingOrderItemFacadeImpl implements ShoppingOrderItemFacade {
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private ShoppingOrderItemService shoppingOrderItemService;

	@Override
	public int insertOrderItem(ShoppingOrderItemDO orderItemPo) {
		orderItemPo.insertInit();
		return shoppingOrderItemService.insert(orderItemPo);
	}
	@Override
	public List<ShoppingOrderItemBO> getOrderItemVoByOrderId(Integer orderId) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderId", orderId);
		param.put("deleted", 0);
		List<ShoppingOrderItemDO> list = shoppingOrderItemService.getList(param);
		List<ShoppingOrderItemBO> listVo = new ArrayList<ShoppingOrderItemBO>();
		if(list != null && list.size() > 0){
			for(ShoppingOrderItemDO shoppingOrderItemPo : list){
				ShoppingOrderItemBO shoppingOrderItemVo = new ShoppingOrderItemBO(shoppingOrderItemPo);
				GoodsDO goods = goodsFacade.getGoodsPoByPK(shoppingOrderItemPo.getGoodsId());
				if(goods!=null){
					shoppingOrderItemVo.setRebate(goods.getRebate());
				}
				listVo.add(shoppingOrderItemVo);
			}
		}
		return listVo;
	}
	@Override
	public List<ShoppingOrderItemBO> getOrderItemVoByOrderBookId(Integer orderBookId) {
		Map<String, Object> param = new HashMap<>();
		param.put("shoppingOrderBookId", orderBookId);
		param.put("deleted", 0);
		List<ShoppingOrderItemDO> list = shoppingOrderItemService.getList(param);
		List<ShoppingOrderItemBO> listVo = new ArrayList<ShoppingOrderItemBO>();
		if(list != null && list.size() > 0){
			for(ShoppingOrderItemDO shoppingOrderItemPo : list){
				ShoppingOrderItemBO shoppingOrderItemVo = new ShoppingOrderItemBO(shoppingOrderItemPo);
				GoodsDO goods = goodsFacade.getGoodsPoByPK(shoppingOrderItemPo.getGoodsId());
				if(goods!=null){
					shoppingOrderItemVo.setRebate(goods.getRebate());
				}
				listVo.add(shoppingOrderItemVo);
			}
		}
		return listVo;
	}
	
	@Override
	public List<ShoppingOrderItemBO> getOrderItemVoByGoodsId(Integer goodsId) {
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("deleted", 0);
		List<ShoppingOrderItemDO> list = shoppingOrderItemService.getList(param);
		List<ShoppingOrderItemBO> listVo = new ArrayList<ShoppingOrderItemBO>();
		if(list != null && list.size() > 0){
			for(ShoppingOrderItemDO shoppingOrderItemPo : list){
				ShoppingOrderItemBO shoppingOrderItemVo = new ShoppingOrderItemBO(shoppingOrderItemPo);
				listVo.add(shoppingOrderItemVo);
			}
		}
		return listVo;
	}
	
	@Override
	public PageResult<ShoppingOrderItemBO> getPageShoppingOrderItemVo(PageEntity pageEntity) {
		PageResult<ShoppingOrderItemBO> pageResult = new PageResult<ShoppingOrderItemBO>();
		List<ShoppingOrderItemDO> list = shoppingOrderItemService.getPagination(pageEntity);
		List<ShoppingOrderItemBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (ShoppingOrderItemDO shoppingOrderItemPo : list) {
				listVo.add(new ShoppingOrderItemBO(shoppingOrderItemPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(shoppingOrderItemService.count(pageEntity.getParams()));
		return pageResult;
	}
	
	@Override
	public ShoppingOrderItemDO getOrderItemPoById(Integer itemId) {
		return shoppingOrderItemService.get(itemId);
	}
	
	@Override
	public List<ShoppingOrderItemDO> getPoBySQLAdapter(Map<String, Object> param) {
		String obj = param.get("times") == null ? "" : param.get("times").toString();
		String startPage = param.get("startPage") == null ? "1" : param.get("startPage").toString();
		String limitNum = param.get("limitNum") == null ? "5" : param.get("limitNum").toString();
		int limitLeft = (Integer.parseInt(startPage)-1)*Integer.parseInt(limitNum);
		int limitRight = (Integer.parseInt(startPage))*Integer.parseInt(limitNum);
		String sql = "SELECT SUM(s.quantity) as quant,DATE_FORMAT(s.create_time,'%Y-%m-%d') as time,s.goods_Id,g.title from shopping_order_item s "
				+ " left join shopping_order o on s.order_Id=o.id "
				+ " LEFT JOIN goods g on s.goods_Id=g.id where DATE_FORMAT(s.create_time,'%Y-%m-%d') like '%"+obj+"%'"
				+ " and o.pay_Type != 4 and o.pay_Type > 0 "
				+ " group by DATE_FORMAT(s.create_time,'%Y-%m-%d'),s.goods_Id order by quant desc limit "+limitLeft+","+limitRight+" ";
		SQLAdapter adapter = new SQLAdapter();
		adapter.setSql(sql);
		List<ShoppingOrderItemDO> list =shoppingOrderItemService.getBySQLAdapter(adapter);
		return list;
	}
	@Override
	public List<ShoppingOrderItemDO> getCountPoBySQLAdapter(Map<String, Object> param) {
		String obj = param.get("times") == null ? "" : param.get("times").toString();
		String sql = "SELECT SUM(s.quantity) as quant,DATE_FORMAT(s.create_time,'%Y-%m-%d') as time,s.goods_Id,g.title from shopping_order_item s "
				+ " left join shopping_order o on s.order_Id=o.id "
				+ " LEFT JOIN goods g on s.goods_Id=g.id where DATE_FORMAT(s.create_time,'%Y-%m-%d') like '%"+obj+"%'"
				+ " and o.pay_Type != 4 and o.pay_Type > 0 "
				+ " group by DATE_FORMAT(s.create_time,'%Y-%m-%d'),s.goods_Id";
		SQLAdapter adapter = new SQLAdapter();
		adapter.setSql(sql);
		List<ShoppingOrderItemDO> list =shoppingOrderItemService.getBySQLAdapter(adapter);
		return list;
	}
}
