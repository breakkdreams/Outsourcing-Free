package com.zd.aoding.outsourcing.weChat.api.facade;


import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;


public interface GoodsSpecItemFacade {
    /**
     * 
    * @Title: insertGoodsPo  供货商添加产品
    * @Description: TODO  
    * @param @param gpo  产品
    * @param @return
    * @return int
     */
    int insertGoodsSpecItemPo(GoodsSpecItemDO specItemPo);
    GoodsSpecItemDO getGoodsSpecItemPoById(Integer itemId);
    List<GoodsSpecItemDO> getGoodsSpecItemPoByParam(Map<String, Object> map);
    List<GoodsSpecItemBO> getListMallGoodItemBOByMap(Map<String, Object> map);
    int updateGoodsSpecItemPo(GoodsSpecItemDO specItemPo);
    PageResult<GoodsSpecItemDO> getGoodsSpecItemPaging(PageEntity entity);
}
