package com.zd.aoding.outsourcing.weChat.provider.facade.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecItemFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsSpecService;

@Service
public class GoodsSpecFacadeImpl implements GoodsSpecFacade {
	@Autowired
	private GoodsSpecService specService;
	@Autowired
	private GoodsSpecItemFacade goodsSpecItemFacade;

    @Override
    public int insertGoodsSpecPo(GoodsSpecDO specPo) {
        return specService.insert(specPo);
    }

    @Override
    public int updateGoodsSpecPo(GoodsSpecDO specPo) {
        return specService.update(specPo);
    }

    @Override
    public List<GoodsSpecDO> getGoodsList(Map<String, Object> map) {
        return specService.getList(map);
    }

    @Override
    public GoodsSpecDO getGoodsSpecPoByPoId(Integer specPoId) {
        GoodsSpecDO specPo = specService.get(specPoId);
        return specPo;
    }

    @Override
    public List<GoodsSpecBO> getGoodsSpecVoByGoodId(String goodsId) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("goodsId", goodsId);
        param.put("deleted", 0);
        List<GoodsSpecBO> listSpecVo = new ArrayList<>();
        List<GoodsSpecDO> listSpecPo = specService.getList(param);
        if (listSpecPo != null) {
            int size = listSpecPo.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    GoodsSpecBO specVo = new GoodsSpecBO(listSpecPo.get(i));
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("specId", listSpecPo.get(i).getId());
                    map.put("deleted", 0);
                    List<GoodsSpecItemBO> specItem = goodsSpecItemFacade.getListMallGoodItemBOByMap(map);
                    if (specItem.size() > 0) {
                        specVo.setListGoodsSpecItem(specItem);
                    }
                    listSpecVo.add(specVo);
                }
            }
            return listSpecVo;
        }
        return null;
    }
}
