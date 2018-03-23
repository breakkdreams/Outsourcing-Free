package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsModelBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsModelFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsModelService;


@Service
public class GoodsModelFacadeImpl implements GoodsModelFacade {
	@Autowired
	private GoodsModelService goodsModelService;
	@Autowired
	private GoodsFacade goodsFacade;

	@Override
	public int insertGoodsModelPo(GoodsModelDO goodsModelPo) {
		goodsModelPo.insertInit();
		return goodsModelService.insert(goodsModelPo);
	}

	@Override
	public GoodsModelDO getGoodsModelPoByPK(String goodsModelId) {
		GoodsModelDO goodsModelPo = goodsModelService.get(Integer.parseInt(goodsModelId));
		if (goodsModelPo != null) {
			return goodsModelPo;
		}
		return null;
	}

	@Override
	public GoodsModelBO getGoodsModelVoByPK(Integer bannerId) {
		GoodsModelDO goodsModelPo = goodsModelService.get(bannerId);
		if (goodsModelPo != null) {
			GoodsModelBO goodsModelVo = new GoodsModelBO(goodsModelPo);
			return goodsModelVo;
		}
		return null;
	}

	@Override
	public int updateGoodsModelPo(GoodsModelDO goodsModelPo) {
		return goodsModelService.update(goodsModelPo);
	}

	@Override
	public PageResult<GoodsModelBO> getPageGoodsModelVo(PageEntity pageEntity) {
		PageResult<GoodsModelBO> pageResult = new PageResult<GoodsModelBO>();
		List<GoodsModelDO> list = goodsModelService.getPagination(pageEntity);
		List<GoodsModelBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (GoodsModelDO goodsModelPo : list) {
				listVo.add(view(goodsModelPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(goodsModelService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countGoodsModel(Map<String, Object> param) {
		try {
			return goodsModelService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<GoodsModelDO> getGoodsModelPoList(Map<String, Object> param) {
		List<GoodsModelDO> list = goodsModelService.getList(param);
		if(list != null){
			return list;
		}
		return null;
	}
	
	private GoodsModelBO view(GoodsModelDO goodsModelPo){
		GoodsModelBO goodsModelVo = new GoodsModelBO(goodsModelPo);
		Integer goodsNum = 0;
		goodsNum = goodsFacade.countModelGoods(goodsModelVo.getGoodsModelId());
		goodsModelVo.setGoodsNum(goodsNum);
		return goodsModelVo;
	}
	
	@Override
	public List<GoodsModelBO> getGoodsModelList(Map<String, Object> param) {
		try {
			List<GoodsModelBO> voList = new ArrayList<GoodsModelBO>();
			List<GoodsModelDO> poList = goodsModelService.getList(param);
			if(poList != null && poList.size() > 0){
				for(GoodsModelDO gmPo : poList){
					voList.add(new GoodsModelBO(gmPo));
				}
			}
			return voList;
		} catch (Exception e) {
			return null;
		}
	}
}
