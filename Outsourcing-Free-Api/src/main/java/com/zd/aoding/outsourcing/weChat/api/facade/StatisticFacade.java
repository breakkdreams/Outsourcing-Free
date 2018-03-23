package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.StatisticBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.StatisticDO;


public interface StatisticFacade {

	/**
	 * 添加
	 * @param statisticPo
	 * @return
	 */
	int insertStatisticPo(StatisticDO statisticPo);
	
	/**
	 * @Title: getStatisticPoByPK 
	 * @Description: 根据主键查询statistic
	 * @param statisticId
	 * @return
	 * @return: StatisticPo
	 */
	StatisticDO getStatisticPoByPK(String statisticId);
	StatisticBO getStatisticVoByPK(Integer statisticId);
	StatisticBO getStatisticVoByMap(Map<String, Object> param);
	
	
	/**
	 * @Title: updateStatisticPo 
	 * @Description: 修改statistic
	 * @param statisticPo
	 * @return
	 * @return: int
	 */
	int updateStatisticPo(StatisticDO statisticPo);
	
	/**
	 * @Title: getPageStatisticVo 
	 * @Description: TODO
	 * @param pageEntity
	 * @return
	 * @return: PageResult<StatisticVo>
	 */
	PageResult<StatisticBO> getPageStatisticVo(PageEntity pageEntity);
	
	/**
	 * @Title: countStatistic 
	 * @Description: 根据条件统计
	 * @param param
	 * @return
	 * @return: int
	 */
	int countStatistic(Map<String, Object> param);
}
