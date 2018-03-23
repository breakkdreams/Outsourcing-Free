package com.zd.aoding.outsourcing.weChat.provider.facade.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecItemFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsSpecItemService;

@Service
public class GoodsSpecItemFacadeImpl implements GoodsSpecItemFacade {
	@Autowired
	private GoodsSpecItemService specItemService;

    @Override
    public int insertGoodsSpecItemPo(GoodsSpecItemDO specItemPo) {
        return specItemService.insert(specItemPo);
    }

    @Override
    public int updateGoodsSpecItemPo(GoodsSpecItemDO specItemPo) {
        return specItemService.update(specItemPo);
    }

    @Override
    public GoodsSpecItemDO getGoodsSpecItemPoById(Integer itemId) {
        return specItemService.get(itemId);
    }

    @Override
    public List<GoodsSpecItemDO> getGoodsSpecItemPoByParam(
            Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<GoodsSpecItemDO> itemList = specItemService.getList(map);
        return itemList;
    }
    
    @Override
	public List<GoodsSpecItemBO> getListMallGoodItemBOByMap(Map<String, Object> param) {
		try {
			List<GoodsSpecItemDO> list = specItemService.getList(param);
			List<GoodsSpecItemBO> listVo = new ArrayList<GoodsSpecItemBO>();
			if(list != null && list.size() > 0){
				for(GoodsSpecItemDO goodsSpecItemPo : list){
					GoodsSpecItemBO goodsSpecItemVo = new GoodsSpecItemBO(goodsSpecItemPo);
					listVo.add(goodsSpecItemVo);
				}
			}
			return listVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
    public PageResult<GoodsSpecItemDO> getGoodsSpecItemPaging(
            PageEntity entity) {
        List<GoodsSpecItemDO> list = specItemService.getPagination(entity);
        int count = specItemService.count(entity.getParams());
        PageResult<GoodsSpecItemDO> result = new PageResult<GoodsSpecItemDO>();
        result.setCurrentPage(entity.getPage());
        result.setPageSize(entity.getSize());
        result.setTotalSize(count);
        result.setResultList(list);
        return result;
    }


}
