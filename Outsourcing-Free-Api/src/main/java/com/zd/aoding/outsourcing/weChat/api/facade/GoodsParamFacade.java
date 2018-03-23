package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsParamBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsParamDO;


public interface GoodsParamFacade {
    /**
     * 
    * @Title: insertGoodsPo  供货商添加产品
    * @Description: TODO  
    * @param @param gpo  产品
    * @param @return
    * @return int
     */
    int insertGoodsParamPo(GoodsParamDO paramPo);
    int deleteParamPo(Integer paramPoId);
    int addOrUpdateGoodsParam(GoodsParamDO paramPo,Integer type);
    List<GoodsParamDO> getParamListByParam(Map<String, Object> map);
    List<GoodsParamBO> getParamListByGoodsId(Integer goodsId);
    PageResult<GoodsParamDO> getParamListPaging(PageEntity entity);
}
