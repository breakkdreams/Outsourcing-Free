package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.DistributorGoodsRelationBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorGoodsRelationDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.facade.DistributorGoodsRelationFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.DistributorGoodsRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DistributorGoodsRelationFacadeImpl implements DistributorGoodsRelationFacade {
	@Autowired
	private DistributorGoodsRelationService distributorGoodsRelationService;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private GoodsOptionFacade goodsOptionFacade;

	@Override
	public int insertDistributorGoodsRelationDO(DistributorGoodsRelationDO distributorGoodsRelationDO) {
		distributorGoodsRelationDO.insertInit();
		return distributorGoodsRelationService.insert(distributorGoodsRelationDO);
	}

	@Override
	public DistributorGoodsRelationDO getDistributorGoodsRelationDOByPK(Integer distributorGoodsRelationId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", distributorGoodsRelationId);
		param.put("deleted", 0);
		DistributorGoodsRelationDO distributorGoodsRelationDO = distributorGoodsRelationService.get(distributorGoodsRelationId);
		if (distributorGoodsRelationDO != null) {
			return distributorGoodsRelationDO;
		}
		return null;
	}
	@Override
	public DistributorGoodsRelationBO getDistributorGoodsRelationBOByPK(Integer distributorGoodsRelationId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", distributorGoodsRelationId);
		param.put("deleted", 0);
		DistributorGoodsRelationDO distributorGoodsRelationDO = distributorGoodsRelationService.get(distributorGoodsRelationId);
		if (distributorGoodsRelationDO != null) {
			DistributorGoodsRelationBO distributorGoodsRelationVo = new DistributorGoodsRelationBO(distributorGoodsRelationDO);
			return distributorGoodsRelationVo;
		}
		return null;
	}

	@Override
	public int updateDistributorGoodsRelationDO(DistributorGoodsRelationDO distributorGoodsRelationDO) {
		return distributorGoodsRelationService.update(distributorGoodsRelationDO);
	}

	@Override
	public PageResult<DistributorGoodsRelationBO> getPageDistributorGoodsRelationBO(PageEntity pageEntity) {
		PageResult<DistributorGoodsRelationBO> pageResult = new PageResult<DistributorGoodsRelationBO>();
		List<DistributorGoodsRelationDO> list = distributorGoodsRelationService.getPagination(pageEntity);
		List<DistributorGoodsRelationBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (DistributorGoodsRelationDO distributorGoodsRelationPo : list) {
				listVo.add(view(distributorGoodsRelationPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(distributorGoodsRelationService.count(pageEntity.getParams()));
		return pageResult;
	}


	private DistributorGoodsRelationBO view(DistributorGoodsRelationDO distributorGoodsRelationPo){
		DistributorGoodsRelationBO distributorGoodsRelationBO = new DistributorGoodsRelationBO(distributorGoodsRelationPo);

		String goodsName = "";
		String optionName = "";

		GoodsDO goodsDO = goodsFacade.getGoodsPoByPK(distributorGoodsRelationPo.getGoodsId());
		if(goodsDO!=null){
			goodsName = goodsDO.getTitle();
		}
		if(distributorGoodsRelationPo.getHaveOption() == 1){
			GoodsOptionDO goodsOptionDO = goodsOptionFacade.getOptionPoByPK(distributorGoodsRelationPo.getOptionId());
			if(goodsOptionDO!=null){
				optionName = goodsOptionDO.getTitle();
			}
		}
		distributorGoodsRelationBO.setOptionName(optionName);
		distributorGoodsRelationBO.setGoodsName(goodsName);
		return distributorGoodsRelationBO;
	}

	@Override
	public int countDistributorGoodsRelation(Map<String, Object> param) {
		try {
			return distributorGoodsRelationService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<DistributorGoodsRelationDO> getList(Map<String, Object> param) {
			return distributorGoodsRelationService.getList(param);
	}
}
