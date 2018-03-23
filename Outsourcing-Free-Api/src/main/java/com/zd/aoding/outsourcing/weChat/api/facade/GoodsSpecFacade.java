package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecDO;


public interface GoodsSpecFacade {
    /**
     * 
    * @Title: insertGoodsPo  供货商添加产品
    * @Description: TODO  
    * @param @param gpo  产品
    * @param @return
    * @return int
     */
    int insertGoodsSpecPo(GoodsSpecDO specPo);
    int updateGoodsSpecPo(GoodsSpecDO specPo);
    GoodsSpecDO getGoodsSpecPoByPoId(Integer specPoId);
    List<GoodsSpecDO> getGoodsList(Map<String, Object> map);
    List<GoodsSpecBO> getGoodsSpecVoByGoodId(String goodsId);
}
