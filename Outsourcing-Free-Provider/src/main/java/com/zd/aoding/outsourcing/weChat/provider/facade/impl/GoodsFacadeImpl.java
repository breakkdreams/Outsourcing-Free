package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsModelFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsService;


@Service
public class GoodsFacadeImpl implements GoodsFacade {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryFacade categoryFacade;
	@Autowired
	private GoodsModelFacade goodsModelFacade;
	@Autowired
	private GoodsOptionFacade goodsOptionFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;

	@Override
	public int insertGoodsPo(GoodsDO goodsPo) {
		goodsPo.insertInit();
		int i = goodsService.insert(goodsPo);
		return i;
	}
	@Override
	public GoodsBO getGoodsVoByPK(String goodsId) {
		GoodsDO goodsPo = goodsService.get(Integer.parseInt(goodsId));
		if (goodsPo != null) {
			GoodsBO goodsVo = view(goodsPo);
			return goodsVo;
		}
		return null;
	}
	@Override
	public GoodsDO getGoodsPoByPK(Integer goodsId) {
		return goodsService.get(goodsId);
	}
	@Override
	public PageResult<GoodsBO> getPageGoodsVo(PageEntity pageEntity) {
		PageResult<GoodsBO> pageResult = new PageResult<GoodsBO>();
		List<GoodsDO> list = goodsService.getPagination(pageEntity);
		List<GoodsBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (GoodsDO goodsPo : list) {
				
				listVo.add(view(goodsPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(goodsService.count(pageEntity.getParams()));
		return pageResult;
	}
	
	@Override
	public PageResult<GoodsBO> getPageGoodsVoByParam(PageEntity pageEntity) {
		PageResult<GoodsBO> pageResult = new PageResult<GoodsBO>();
		List<GoodsDO> list = goodsService.getPagination(pageEntity);
		List<GoodsBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (GoodsDO goodsPo : list) {
				listVo.add(priview(goodsPo,pageEntity.getParams()));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(goodsService.count(pageEntity.getParams()));
		return pageResult;
	}
	
	private GoodsBO priview(GoodsDO goodsPo,Map<String,Object> param){
		GoodsBO goodsVo = new GoodsBO(goodsPo, GoodsBO.TO_PAGING);
		
		String orderIdList = "";
		List<ShoppingOrderItemBO> itemlist = shoppingOrderItemFacade.getOrderItemVoByGoodsId(goodsVo.getGoodsId());
		for (ShoppingOrderItemBO shoppingOrderItemVo : itemlist) {
			if(!StringUtil.isNULL(orderIdList)){
				orderIdList += ",";
			}
			if(!orderIdList.contains(shoppingOrderItemVo.getOrderId()+"")){
				orderIdList += shoppingOrderItemVo.getOrderId();
			}
		}
		param.put("orderIdList", orderIdList);
		
		int saleCount = 0;
		List<ShoppingOrderDO> list = shoppingOrderFacade.getShoppingOrderList(param);
		if(list!=null && list.size()>0){
			for (ShoppingOrderDO shoppingOrderPo : list) {
				List<ShoppingOrderItemBO> saleList = shoppingOrderItemFacade.getOrderItemVoByOrderId(shoppingOrderPo.getId());
				//销量数量
				for (ShoppingOrderItemBO shoppingOrderItemVo : saleList) {
					saleCount += shoppingOrderItemVo.getQuantity();
				}
			}
		}
		goodsVo.setSaleCount(saleCount);
		return goodsVo;
	}

	@Override
	public int changeGoodsPoStatus(Integer goodsId, Integer status) {
		GoodsDO goodsPo = new GoodsDO();
		goodsPo.setId(goodsId);
		goodsPo.setStatus(status);
		return goodsService.update(goodsPo);
	}
	private GoodsDO getOneGoodsPo(Map<String, Object> param) {
		List<GoodsDO> list = goodsService.getList(param);
		if (list != null) {
			if (list.size() == 1) {
				return list.get(0);
			} else if (list.size() > 1) {
				LogUtil.dataError(list + "", this.getClass());
				return list.get(0);
			}
		}
		return null;
	}
	@Override
	public int updateGoodsPo(GoodsDO goodsPo) {
		return goodsService.update(goodsPo);
	}
	/*@Override
	public int agreeGoods(Integer goodsId) {
		try {
			GoodsPo goodsPo = new GoodsPo();
			goodsPo.setId(goodsId);
			goodsPo.setStatus(GoodsPo.STATUS_AGREE);
			return goodsService.update(goodsPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int refuseGoods(Integer goodsId, String rejectreason) {
		try {
			GoodsPo goodsPo = new GoodsPo();
			goodsPo.setId(goodsId);
			goodsPo.setStatus(GoodsPo.status_refuse);
			goodsPo.setRejectreason(rejectreason);
			return goodsService.update(goodsPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}*/
	@Override
	public List<GoodsDO> getGoodsList(Map<String, Object> param) {
		List<GoodsDO> list = goodsService.getList(param);
		if (list != null) {
			return list;
		}
		return null;
	}
	@Override
	public int countModelGoods(Integer modelId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("modelId", modelId);
			return goodsService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	private GoodsDO findGoodsByBarCode(String barCode){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("barCode", barCode);
		param.put("deleted", 0);
		List<GoodsDO> list = goodsService.getList(param);
		if(list != null && list.size() == 1){
			return list.get(0);
		}
		if(list != null && list.size() > 1){
			LogUtil.dataError("根据条形码查出多个商品", getClass());
			return list.get(0);
		}
		return null;
	}
	private GoodsBO view(GoodsDO goodsPo){
		GoodsBO goodsVo = new GoodsBO(goodsPo, GoodsBO.TO_PAGING);
		String supplierName = "";
		String categoryName = "";
		String dealerName = "";
		String modelName = "";
		CategoryDO category = categoryFacade.getCategoryPoByPK(goodsVo.getFirstCatagory()+"");
		if(category != null){
			categoryName = category.getName();
		}
		GoodsModelDO goodsModel = goodsModelFacade.getGoodsModelPoByPK(goodsPo.getModelId()+"");
		if(goodsModel != null){
			modelName = goodsModel.getModelName();
		}
		goodsVo.setDealerName(dealerName);
		goodsVo.setSupplierName(supplierName);
		goodsVo.setCategoryName(categoryName);
		goodsVo.setModelName(modelName);
		return goodsVo;
	}
	@Override
	public List<Map<String, Object>> saveExcel(File file) {
		return null;
//		String[] cols = { "barCode", "title", "stock", "isOption" };
//		List<Map<String, Object>> list_result = new ArrayList<Map<String, Object>>();
//		try {
//			List<Map<String, Object>> list = ExcelUtil.readExcel(file, 0, cols, false);
//			System.out.println("list.size()="+list.size());
//			System.out.println("list="+list);
//			for (Map<String, Object> map : list) {
//				String barCode = (map.get("barCode") + "").toUpperCase();
//				String isOption = map.get("isOption") + "";
//				System.out.println("isOption="+isOption);
//				if("否".equals(isOption)){
//					GoodsDO goods = findGoodsByBarCode(barCode);
//					System.out.println("goods = "+goods);
//					try {
//						if (goods == null) {
//							System.out.println("goods == null");
//							list_result.add(map);
//						} else {
//							System.out.println("goods == else");
//							Integer stock = Integer.parseInt(map.get("stock")+"");
//							Integer goodsStock = goods.getTotalStock();
//							goods.setTotalStock(goodsStock + stock);
//							goodsService.update(goods);
//						}
//					} catch (Exception e) {
//						list_result.add(map);
//						e.printStackTrace();
//					}
//				} else if ("是".equals(isOption)){
//					GoodsOptionDO goodsOption = goodsOptionFacade.findGoodsOptionByBarCode(barCode);
//					System.out.println("goodsOption = "+goodsOption);
//					try {
//						if (goodsOption == null) {
//							System.out.println("goodsOption == null");
//							list_result.add(map);
//						} else {
//							System.out.println("goodsOption == else");
//							Integer stock = Integer.parseInt(map.get("stock")+"");
//							Integer goodsOptionStock = goodsOption.getStock();
//							goodsOption.setStock(goodsOptionStock + stock);
//							goodsOptionFacade.updateOptionByPk(goodsOption);
//						}
//					} catch (Exception e) {
//						list_result.add(map);
//						e.printStackTrace();
//					}
//				} else {
//					System.out.println("else....");
//					list_result.add(map);
//				}
//				
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list_result;
	}
	@Override
	public int countGoods(Map<String, Object> param) {
		try {
			return goodsService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	 @Override
	    public int changeGoodsDoStatus(Integer goodsId, Integer status) {
	        GoodsDO goodsPo = new GoodsDO();
	        goodsPo.setId(goodsId);
	        goodsPo.setStatus(status);
	        goodsPo.setStatusAdmin(status);
	        if(status == GoodsDO.STATUS_XIAJIA){
	            goodsPo.setStatusAdmin(GoodsDO.STATUS_XIAJIA);
	        }
	        return goodsService.update(goodsPo);
	    }
}
