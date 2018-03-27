package com.zd.aoding.outsourcing.weChat.api.facade;


import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.DistributorGoodsRelationBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorGoodsRelationDO;

import java.util.Map;

public interface DistributorGoodsRelationFacade {

    int insertDistributorGoodsRelationDO(DistributorGoodsRelationDO distributorGoodsRelationDO);

    DistributorGoodsRelationDO getDistributorGoodsRelationDOByPK(Integer distributorGoodsRelationId);
    DistributorGoodsRelationBO getDistributorGoodsRelationBOByPK(Integer distributorGoodsRelationId);

    int updateDistributorGoodsRelationDO(DistributorGoodsRelationDO distributorGoodsRelationDO);

    PageResult<DistributorGoodsRelationBO> getPageDistributorGoodsRelationBO(PageEntity pageEntity);

    int countDistributorGoodsRelation(Map<String, Object> param);
}
