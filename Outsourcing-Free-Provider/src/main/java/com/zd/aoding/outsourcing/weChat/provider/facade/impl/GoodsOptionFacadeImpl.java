package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsOptionBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecItemFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsOptionService;

@Service
public class GoodsOptionFacadeImpl implements GoodsOptionFacade {
	@Autowired
	private GoodsOptionService goodsOptionService;
	@Autowired
	private GoodsSpecFacade goodsSpecFacade;
	@Autowired
	private GoodsSpecItemFacade goodsSpecItemFacade;
	@Autowired
	private GoodsFacade goodsFacade;

	@Override
	public GoodsOptionBO getOptionVoBySpecIds(String specItemIds, String goodsId) {
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", Integer.parseInt(goodsId));
		param.put("specIds", specItemIds);
		param.put("deleted", 0);
		GoodsOptionDO goodsOptionPo = getOneGoodsOptionPo(param);
		if (goodsOptionPo != null) {
			if(StringUtil.isNULL(goodsOptionPo.getThumb())){
				GoodsDO goodsDo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
				if(goodsDo!=null){
					goodsOptionPo.setThumb(goodsDo.getCoverImgUrl());
				}
			}
			return new GoodsOptionBO(goodsOptionPo);
		} else {
			return null;
		}
	}
	@Override
	public List<GoodsOptionDO> getOptionPoByGoodsId(Integer goodsId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("goodsId", goodsId);
		param.put("deleted", 0);
		List<GoodsOptionDO> list = goodsOptionService.getList(param);
		return list;
	}
	@Override
	public List<GoodsOptionBO> getOptionBOByGoodsId(Integer goodsId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("goodsId", goodsId);
		param.put("deleted", 0);
		List<GoodsOptionDO> list = goodsOptionService.getList(param);
		List<GoodsOptionBO> listVo = new ArrayList<>();
		if (list!=null && list.size()>0){
			for (GoodsOptionDO goodsOptionDO : list) {
				listVo.add(new GoodsOptionBO(goodsOptionDO));
			}
		}
		return listVo;
	}

	private GoodsOptionDO getOneGoodsOptionPo(Map<String, Object> param) {
		List<GoodsOptionDO> list = goodsOptionService.getList(param);
		if (list != null) {
			if (list.size() == 1) {
				return list.get(0);
			} else if (list.size() > 1) {
				return list.get(0);
			}
		}
		return null;
	}
	@Override
	public GoodsOptionBO getOptionVoByPK(String optionId) {
		try {
			return getOptionVoByPK(Integer.parseInt(optionId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public GoodsOptionBO getOptionVoByPK(Integer optionId) {
		try {
			GoodsOptionDO goodsOptionPo = goodsOptionService.get(optionId);
			GoodsOptionBO goodsOptionVo = new GoodsOptionBO(goodsOptionPo);
			return goodsOptionVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public GoodsOptionDO getOptionPoByPK(Integer optionId) {
		try {
			GoodsOptionDO goodsOptionPo = goodsOptionService.get(optionId);
			return goodsOptionPo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int insertOptionPo(GoodsOptionDO goodsOptionPo) {
		goodsOptionPo.insertInit();
		return goodsOptionService.insert(goodsOptionPo);
	}
	
	
	@Override
	public PageResult<GoodsOptionBO> getPageOption(PageEntity pageEntity) {
		try {
			PageResult<GoodsOptionBO> pageResult = new PageResult<GoodsOptionBO>();
			List<GoodsOptionDO> list = goodsOptionService.getPagination(pageEntity);
			List<GoodsOptionBO> listVo = new ArrayList<GoodsOptionBO>();
			if(list != null && list.size() > 0){
				for(GoodsOptionDO goodsOptionPo : list){
					GoodsOptionBO goodsOptionVo = new GoodsOptionBO(goodsOptionPo);
					listVo.add(goodsOptionVo);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(goodsOptionService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int updateAllOptionByGoodsId(String goodsId, Double marketPrice, Double purchasePrice, Integer stock, Integer showStock) {
		Map<String, Object> param = new HashMap<>();
		param.put("goodsId", goodsId);
		param.put("deleted", 0);
		List<GoodsOptionDO> list = goodsOptionService.getList(param);
		GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
        //默认为奖金产品
        boolean bool = true;
        if(goodsPo.getType() == GoodsDO.type_score){
            bool = false;
        }
        int size = list.size();
        int i = size + 1;
        int totalStock = size * stock;
        int totalShowStock = size * showStock;
        Integer trueTotalStock = goodsPo.getTotalStock();
        if(totalStock > trueTotalStock){
            return -2;
        }
        if(totalShowStock > trueTotalStock){
            return -3;
        }
        for (GoodsOptionDO goodsOptionDo : list) {
            goodsOptionDo.setMarketPrice(marketPrice);
            goodsOptionDo.setPurchasePrice(purchasePrice);
            if(bool){
                goodsOptionDo.setOptionBonusPrice(marketPrice);
                goodsOptionDo.setOptionScorePrice(0.00);
            }else{
                goodsOptionDo.setOptionBonusPrice(0.00);
                goodsOptionDo.setOptionScorePrice(marketPrice);
            }
            goodsOptionDo.setStock(stock);
            goodsOptionDo.setShowStock(showStock);
            i = i - updateGoodsOptionPo(goodsOptionDo);
        }
        return i;
	}
	
	 @Override
	    public int updateGoodsOptionPo(GoodsOptionDO optionPo) {
	        GoodsOptionDO goodsOptionPo = goodsOptionService.get(optionPo.getId());
	        Integer goodsId = goodsOptionPo.getGoodsId();
	        GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(goodsId);
	        boolean bool = true;
	        if(goodsPo.getType() == GoodsDO.type_score){
	            bool = false;
	        }
	        Double marketPrice = optionPo.getMarketPrice();
	        if(bool){
	            optionPo.setOptionBonusPrice(marketPrice);
	            optionPo.setOptionScorePrice(0.00);
	        }else{
	            optionPo.setOptionBonusPrice(0.00);
	            optionPo.setOptionScorePrice(marketPrice);
	        }
	        int i = goodsOptionService.update(optionPo);
	        return i;
	    }
	
	@Override
	public int updateOptionByPk(GoodsOptionDO goodsOptionPo) {
		return goodsOptionService.update(goodsOptionPo);
	}
	@Override
	public GoodsOptionDO findGoodsOptionByBarCode(String barCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("barCode", barCode);
		param.put("deleted", 0);
		List<GoodsOptionDO> list = goodsOptionService.getList(param);
		if(list != null && list.size() == 1){
			return list.get(0);
		}
		if(list != null && list.size() > 1){
			LogUtil.dataError("根据条形码查出多个商品配置", getClass());
			return list.get(0);
		}
		return null;
	}
	
	@Override
    public int deletedAllOptionByGoodsId(String goodsId) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("goodsId", goodsId);
        param.put("deleted", 0);
        List<GoodsOptionDO> list = goodsOptionService.getList(param);
        int i = 1;
        for (GoodsOptionDO goodsOptionDo : list) {
            i = i + deletedOptionByPk(goodsOptionDo.getId());
        }
        List<GoodsSpecBO> goodsSpec = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
        if(goodsSpec != null){
            for (GoodsSpecBO goodsSpecVo : goodsSpec) {
                List<GoodsSpecItemBO> goodsSpecItem = goodsSpecVo.getListGoodsSpecItem();
                if(goodsSpecItem != null){
                    for(GoodsSpecItemBO goodsSpecItemDo : goodsSpecItem){
                    	GoodsSpecItemDO update = new GoodsSpecItemDO();
                        update.setId(goodsSpecItemDo.getId());
                        update.setIsShow(0);
                        goodsSpecItemFacade.updateGoodsSpecItemPo(update);
                    }
                }
            }
        }
        return i - list.size();
    }
	
	private int deletedOptionByPk(Integer optionId) {
        GoodsOptionDO mapper = new GoodsOptionDO();
        mapper.setId(optionId);
        mapper.setDeleted(1);
        return goodsOptionService.update(mapper);
    }
	
	@Override
    public List<GoodsOptionDO> getGoodsOptionByPatam(Map<String, Object> param) {
        List<GoodsOptionDO> optionList = goodsOptionService.getList(param);
        return optionList;
    }
}
