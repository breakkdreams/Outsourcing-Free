package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingBookBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingBookDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingBookFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ShoppingBookService;

@Service
public class ShoppingBookFacadeImpl implements ShoppingBookFacade {
	@Autowired
	private ShoppingBookService shoppingBookService;
	// @Override
	// public PageResult<GoodsVo> getPageGoodsVo(PageEntity pageEntity) {
	// PageResult<GoodsVo> pageResult = new PageResult<GoodsVo>();
	// List<GoodsDo> list = goodsService.getMapperPage(pageEntity);
	// List<GoodsVo> listVo = new ArrayList<>();
	// if (list != null && list.size() > 0) {
	// for (GoodsDo GoodsDo : list) {
	// listVo.add(new GoodsVo(GoodsDo, GoodsVo.TO_PAGING));
	// }
	// }
	// pageResult.setResultList(listVo);
	// pageResult.setCurrentPage(pageEntity.getPage());
	// pageResult.setPageSize(pageEntity.getSize());
	// pageResult.setTotalSize(goodsService.countMapper(pageEntity.getParams()));
	// return pageResult;
	// }

	@Override
	public int insertOrderBookPo(ShoppingBookDO shoppingBookPo) {
		shoppingBookPo.insertInit();
		return shoppingBookService.insert(shoppingBookPo);
	}
	@Override
	public ShoppingBookBO getBooKVoByPK(String orderId) {
		ShoppingBookDO shoppingBookPo = shoppingBookService.get(Integer.parseInt(orderId));
		ShoppingBookBO shoppingBookVo = new ShoppingBookBO(shoppingBookPo);
		return shoppingBookVo;
	}
	@Override
	public ShoppingBookDO getBooKByPK(Integer orderId) {
		ShoppingBookDO shoppingBookPo = shoppingBookService.get(orderId);
		return shoppingBookPo;
	}
	@Override
	public int updateBook(ShoppingBookDO book) {
		return shoppingBookService.update(book);
	}
}
