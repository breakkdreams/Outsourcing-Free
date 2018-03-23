package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsTypeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsTypeDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsTypeFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsTypeService;


@Service
public class GoodsTypeFacadeImpl implements GoodsTypeFacade {
	@Autowired
	private GoodsTypeService goodsTypeService;

	@Override
	public int insertGoodsTypePo(GoodsTypeDO goodsTypePo) {
		goodsTypePo.insertInit();
		return goodsTypeService.insert(goodsTypePo);
	}

	@Override
	public GoodsTypeDO getGoodsTypePoByPK(String goodsTypeId) {
		GoodsTypeDO goodsTypePo = goodsTypeService.get(Integer.parseInt(goodsTypeId));
		if (goodsTypePo != null) {
			return goodsTypePo;
		}
		return null;
	}

	@Override
	public GoodsTypeBO getGoodsTypeVoByPK(Integer bannerId) {
		GoodsTypeDO goodsTypePo = goodsTypeService.get(bannerId);
		if (goodsTypePo != null) {
			GoodsTypeBO goodsTypeVo = new GoodsTypeBO(goodsTypePo);
			return goodsTypeVo;
		}
		return null;
	}

	@Override
	public int updateGoodsTypePo(GoodsTypeDO goodsTypePo) {
		return goodsTypeService.update(goodsTypePo);
	}

	@Override
	public PageResult<GoodsTypeBO> getPageGoodsTypeVo(PageEntity pageEntity) {
		PageResult<GoodsTypeBO> pageResult = new PageResult<GoodsTypeBO>();
		List<GoodsTypeDO> list = goodsTypeService.getPagination(pageEntity);
		List<GoodsTypeBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (GoodsTypeDO goodsTypePo : list) {
				listVo.add(new GoodsTypeBO(goodsTypePo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(goodsTypeService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countGoodsType(Map<String, Object> param) {
		try {
			return goodsTypeService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<GoodsTypeDO> getGoodsTypePoList(Map<String, Object> param) {
		List<GoodsTypeDO> list = goodsTypeService.getList(param);
		if(list != null){
			return list;
		}
		return null;
	}
	
	
	@Override
	public List<GoodsTypeBO> getGoodsTypeList(Map<String, Object> param) {
		try {
			List<GoodsTypeBO> voList = new ArrayList<GoodsTypeBO>();
			List<GoodsTypeDO> poList = goodsTypeService.getList(param);
			if(poList != null && poList.size() > 0){
				for(GoodsTypeDO gmPo : poList){
					voList.add(new GoodsTypeBO(gmPo));
				}
			}
			return voList;
		} catch (Exception e) {
			return null;
		}
	}
}
