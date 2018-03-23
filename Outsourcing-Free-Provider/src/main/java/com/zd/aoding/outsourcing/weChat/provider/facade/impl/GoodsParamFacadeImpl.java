package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsParamBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsParamDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsParamFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsParamService;

@Service
public class GoodsParamFacadeImpl implements GoodsParamFacade {
    @Autowired
    private GoodsParamService paramService;

    @Override
    public int insertGoodsParamPo(GoodsParamDO paramPo) {
        return paramService.insert(paramPo);
    }

    @Override
    public int addOrUpdateGoodsParam(GoodsParamDO paramPo, Integer type) {
        try {
            if (type == 1) {
                int i = paramService.update(paramPo);
                if (i == 0) {
                    i = paramService.update(paramPo);
                }
                return i;
            }
            paramPo.insertInit();
            int i = paramService.insert(paramPo);
            if (i == 0) {
                i = paramService.insert(paramPo);
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deleteParamPo(Integer paramPoId) {
        GoodsParamDO paramPo = new GoodsParamDO();
        paramPo.setId(paramPoId);
        paramPo.setDeleted(1);
        int i = paramService.update(paramPo);
        return i;
    }

    @Override
    public List<GoodsParamDO> getParamListByParam(Map<String, Object> map) {
        List<GoodsParamDO> list = paramService.getList(map);
        return list;
    }

    @Override
    public List<GoodsParamBO> getParamListByGoodsId(Integer goodsId) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("goodsId", goodsId);
        map.put("deleted", 0);
        List<GoodsParamDO> list = paramService.getList(map);
        if (list != null && list.size() > 0) {
            List<GoodsParamBO> voList = new ArrayList<GoodsParamBO>();
            int len = list.size();
            GoodsParamBO vo = null;
            for(int i = 0; i < len; i++){
                vo = new GoodsParamBO(list.get(i));
                voList.add(vo);
            }
            return voList;
        }
        return null;
    }

    @Override
    public PageResult<GoodsParamDO> getParamListPaging(PageEntity entity) {
        List<GoodsParamDO> poList = paramService.getPagination(entity);
        PageResult<GoodsParamDO> result = new PageResult<GoodsParamDO>();
        result.setCurrentPage(entity.getPage());
        result.setPageSize(entity.getSize());
        result.setResultList(poList);
        int count = paramService.count(entity.getParams());
        result.setTotalSize(count);
        return result;
    }

}
