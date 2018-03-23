package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BannerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BannerDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BannerFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.BannerService;



@Service
public class BannerFacadeImpl implements BannerFacade {
	@Autowired
	private BannerService bannerService;

	@Override
	public int insertBannerPo(BannerDO bannerPo) {
		bannerPo.insertInit();
		return bannerService.insert(bannerPo);
	}

	@Override
	public BannerDO getBannerPoByPK(String bannerId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", bannerId);
		param.put("deleted", 0);
		BannerDO bannerPo = bannerService.get(Integer.parseInt(bannerId));
		if (bannerPo != null) {
			return bannerPo;
		}
		return null;
	}

	@Override
	public int updateBannerPo(BannerDO bannerPo) {
		return bannerService.update(bannerPo);
	}

	@Override
	public PageResult<BannerBO> getPageBannerVo(PageEntity pageEntity) {
		PageResult<BannerBO> pageResult = new PageResult<BannerBO>();
		List<BannerDO> list = bannerService.getPagination(pageEntity);
		List<BannerBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (BannerDO bannerPo : list) {
				listVo.add(new BannerBO(bannerPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(bannerService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countBanner(Map<String, Object> param) {
		try {
			return bannerService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public BannerBO getBannerVoByPK(Integer bannerId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", bannerId);
		param.put("deleted", 0);
		BannerDO bannerPo = bannerService.get(bannerId);
		if (bannerPo != null) {
			BannerBO bannerVo = new BannerBO(bannerPo);
			return bannerVo;
		}
		return null;
	}

}
