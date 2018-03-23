package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsCommentBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsCommentDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsCommentFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.GoodsCommentService;


@Service
public class GoodsCommentFacadeImpl implements GoodsCommentFacade {
	@Autowired
	private GoodsCommentService goodsCommentService;

	@Override
	public int insertGoodsCommentPo(GoodsCommentDO goodsCommentPo) {
		goodsCommentPo.insertInit();
		return goodsCommentService.insert(goodsCommentPo);
	}

	@Override
	public GoodsCommentDO getGoodsCommentPoByPK(String goodsCommentId) {
		GoodsCommentDO goodsCommentPo = goodsCommentService.get(Integer.parseInt(goodsCommentId));
		if (goodsCommentPo != null) {
			return goodsCommentPo;
		}
		return null;
	}

	@Override
	public GoodsCommentBO getGoodsCommentVoByPK(Integer bannerId) {
		GoodsCommentDO goodsCommentPo = goodsCommentService.get(bannerId);
		if (goodsCommentPo != null) {
			GoodsCommentBO goodsCommentVo = new GoodsCommentBO(goodsCommentPo);
			return goodsCommentVo;
		}
		return null;
	}

	@Override
	public int updateGoodsCommentPo(GoodsCommentDO goodsCommentPo) {
		return goodsCommentService.update(goodsCommentPo);
	}

	@Override
	public PageResult<GoodsCommentBO> getPageGoodsCommentVo(PageEntity pageEntity) {
		PageResult<GoodsCommentBO> pageResult = new PageResult<GoodsCommentBO>();
		List<GoodsCommentDO> list = goodsCommentService.getPagination(pageEntity);
		List<GoodsCommentBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (GoodsCommentDO goodsCommentPo : list) {
				listVo.add(new GoodsCommentBO(goodsCommentPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(goodsCommentService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countGoodsComment(Map<String, Object> param) {
		try {
			return goodsCommentService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<GoodsCommentDO> getGoodsCommentPoList(Map<String, Object> param) {
		List<GoodsCommentDO> list = goodsCommentService.getList(param);
		if(list != null){
			return list;
		}
		return null;
	}
	
	@Override
	public List<GoodsCommentBO> getGoodsCommentList(Map<String, Object> param) {
		try {
			List<GoodsCommentBO> voList = new ArrayList<GoodsCommentBO>();
			List<GoodsCommentDO> poList = goodsCommentService.getList(param);
			if(poList != null && poList.size() > 0){
				for(GoodsCommentDO gmPo : poList){
					voList.add(new GoodsCommentBO(gmPo));
				}
			}
			return voList;
		} catch (Exception e) {
			return null;
		}
	}
}
