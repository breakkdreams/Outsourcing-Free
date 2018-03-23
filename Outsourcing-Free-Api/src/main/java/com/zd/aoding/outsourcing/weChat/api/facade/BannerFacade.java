package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BannerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BannerDO;


public interface BannerFacade {

	/**
	 * @Title: insertBannerPo 
	 * @Description: 添加banner
	 * @param bannerPo
	 * @return
	 * @return: int
	 */
	int insertBannerPo(BannerDO bannerPo);
	
	/**
	 * @Title: getBannerPoByPK 
	 * @Description: 根据主键查询banner
	 * @param bannerId
	 * @return
	 * @return: BannerPo
	 */
	BannerDO getBannerPoByPK(String bannerId);
	BannerBO getBannerVoByPK(Integer bannerId);
	
	/**
	 * @Title: updateBannerPo 
	 * @Description: 修改banner
	 * @param bannerPo
	 * @return
	 * @return: int
	 */
	int updateBannerPo(BannerDO bannerPo);
	
	/**
	 * @Title: getPageBannerVo 
	 * @Description: TODO
	 * @param pageEntity
	 * @return
	 * @return: PageResult<BannerVo>
	 */
	PageResult<BannerBO> getPageBannerVo(PageEntity pageEntity);
	
	/**
	 * @Title: countBanner 
	 * @Description: 根据条件统计
	 * @param param
	 * @return
	 * @return: int
	 */
	int countBanner(Map<String, Object> param);
}
