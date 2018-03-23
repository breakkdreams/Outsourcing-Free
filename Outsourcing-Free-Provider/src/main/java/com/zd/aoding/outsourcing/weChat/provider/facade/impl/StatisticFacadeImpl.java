package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.StatisticBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.StatisticDO;
import com.zd.aoding.outsourcing.weChat.api.facade.StatisticFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.StatisticService;



@Service
public class StatisticFacadeImpl implements StatisticFacade {
	@Autowired
	private StatisticService statisticService;

	@Override
	public int insertStatisticPo(StatisticDO statisticPo) {
		statisticPo.insertInit();
		return statisticService.insert(statisticPo);
	}

	@Override
	public StatisticDO getStatisticPoByPK(String statisticId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", statisticId);
		param.put("deleted", 0);
		StatisticDO statisticPo = statisticService.get(Integer.parseInt(statisticId));
		if (statisticPo != null) {
			return statisticPo;
		}
		return null;
	}

	@Override
	public int updateStatisticPo(StatisticDO statisticPo) {
		return statisticService.update(statisticPo);
	}

	@Override
	public PageResult<StatisticBO> getPageStatisticVo(PageEntity pageEntity) {
		PageResult<StatisticBO> pageResult = new PageResult<StatisticBO>();
		List<StatisticDO> list = statisticService.getPagination(pageEntity);
		List<StatisticBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (StatisticDO statisticPo : list) {
				listVo.add(new StatisticBO(statisticPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(statisticService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countStatistic(Map<String, Object> param) {
		try {
			return statisticService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public StatisticBO getStatisticVoByPK(Integer statisticId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", statisticId);
		param.put("deleted", 0);
		StatisticDO statisticPo = statisticService.get(statisticId);
		if (statisticPo != null) {
			StatisticBO statisticVo = new StatisticBO(statisticPo);
			return statisticVo;
		}
		return null;
	}

	@Override
	public StatisticBO getStatisticVoByMap(Map<String, Object> param) {
		List<StatisticDO> list = statisticService.getList(param);
		if(list!=null && list.size()>0){
			StatisticBO statisticVo = new StatisticBO(list.get(0));
			return statisticVo;
		}
		return null;
	}
}
